package cs3500.animator.view;

import cs3500.animator.controller.Features;
import hw05.model.AnimatorModel;
import hw05.model.Motion;
import hw05.model.shapes.IShape;
import java.io.IOException;
import java.util.Objects;

/**
 * SVG view that represents the animation in terms of SVG code in text format.
 */
public class SVGView implements View {

  AnimatorModel model;
  Appendable out;
  int ticksPerSecond;

  /**
   * Creates a new SVGView object that has the ability to change a model into SVG text code.
   * @param model AnimatorModel that the SVGView uses to make the SVG code
   * @param out Appendable that the code is outputted to
   * @param ticksPerSecond int ticks per second of the animation
   */
  public SVGView(AnimatorModel model, Appendable out, int ticksPerSecond) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(out);
    this.model = model;
    this.out = out;
    this.ticksPerSecond = ticksPerSecond;
  }



  @Override
  public void run() {
    try {
      out.append(this.getSVG());
    } catch (IOException e) {
      e.printStackTrace();
      throw new IllegalStateException("append failed");
    }
  }

  /**
   * Creates the SVG version of the model.
   * @return the model in SVG format.
   */
  private String getSVG() {
    StringBuilder builder = new StringBuilder();

    builder.append("<svg width=\"" + this.model.getCanvasSize().width + "\" height=\""
            + this.model.getCanvasSize().height + "\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n");

    for (IShape s : this.model.getShapes()) {

      builder.append(String.format("<%s id=\"%s\" %s=\"%f\"  %s=\"%f\" %s=\"%f\" "
              + "%s=\"%f\" " + "fill=\"rgb(%f,%f,%f)\"  >\n",
          s.getSVGName(), s.getName(), s.getXAttribute(), s.getXPos() - this.model.getXOffset(),
              s.getYAttribute(),
          s.getYPos() - this.model.getYOffset(), s.getWidthAttribute(), s.getWidth(),
              s.getHeightAttribute(),
          s.getHeight(),s.getRed(), s.getGreen(), s.getBlue()));

      for (Motion m : s.getMotions()) {

        //for the rotation
        if (Math.abs(m.getStartAngle() - m.getEndAngle()) >= 0.01) {
          builder.append(String.format("    <animateTransform attributeName= \"transform\" "
                  + "attributeType= \"xml\" type= \"rotate\" "
                          + " dur=\"%dms\" from=\"%f %f %f\" to=\"%f %f %f\"/>\n",
                  m.getDuration() * ticksPerSecond, m.getStartAngle(),
                  m.getStartX() + m.getStartWidth() / 2, m.getStartY() + m.getStartHeight() / 2,
                  m.getEndAngle(), m.getEndX() + m.getEndWidth() / 2,
                  m.getEndY() + m.getEndHeight() / 2));

        }



        //for the width of this motion
        if (Math.abs(m.getStartWidth() - m.getEndWidth()) >= 0.01) {
          builder.append(String.format("    <animate attributeType= \"xml\" begin= \"%dms\""
                  + " dur=\"%dms\" attributeName=\"%s\" from=\"%f\" to=\"%f\" fill=\"freeze\" />\n",
              m.getStartTick() * ticksPerSecond, m.getDuration() * ticksPerSecond,
              s.getWidthAttribute(), m.getStartX() - this.model.getXOffset(),
                  m.getEndX() - this.model.getXOffset()));

        }

        //for the height of this motion
        if (Math.abs(m.getStartHeight() - m.getEndHeight()) >= 0.01) {
          builder.append(String.format("    <animate attributeType= \"xml\" begin= \"%dms\""
                  + " dur=\"%dms\" attributeName=\"%s\" from=\"%f\" to=\"%f\" fill=\"freeze\" />\n",
              m.getStartTick() * ticksPerSecond, m.getDuration() * ticksPerSecond,
              s.getHeightAttribute(), m.getStartX() - this.model.getXOffset(),
                  m.getEndX() - this.model.getXOffset()));

        }

        //for the x of this motion
        if (Math.abs(m.getStartX() - m.getEndX()) >= 0.01 ) {
          builder.append(String.format("    <animate attributeType= \"xml\" begin= \"%dms\""
                  + " dur=\"%dms\" attributeName=\"%s\" from=\"%f\" to=\"%f\" fill=\"freeze\" />\n",
              m.getStartTick() * ticksPerSecond, m.getDuration() * ticksPerSecond,
              s.getXAttribute(), m.getStartX() - this.model.getXOffset(),
                  m.getEndX() - this.model.getXOffset()));
        }

        //for the y of this motion
        if (Math.abs(m.getStartY() - m.getEndY()) >= 0.01 ) {
          builder.append(String.format("    <animate attributeType= \"xml\" begin= \"%dms\""
                  + " dur=\"%dms\" attributeName=\"%s\" from=\"%f\" to=\"%f\" fill=\"freeze\" />\n",
              m.getStartTick() * ticksPerSecond, m.getDuration() * ticksPerSecond,
              s.getYAttribute(), m.getStartY() - this.model.getYOffset(),
                  m.getEndY() - this.model.getYOffset()));
        }

        //for the color of this motion
        if (m.getStartRed() != m.getEndRed() || m.getStartGreen() != m.getEndGreen()
            || m.getStartBlue() != m.getEndBlue()) {
          builder.append(String.format("    <animate attributeType= \"xml\" attributeName=\"fill\""
                  + " begin=\"%dms\" from=\"rgb(%d,%d,%d)\" to=\"rgb(%d,%d,%d)\" dur=\"%dms\""
                  + " fill=\"freeze\" />\n",
              m.getStartTick() * ticksPerSecond, (int) m.getStartRed(), (int) m.getStartGreen(),
              (int) m.getStartBlue(), (int) m.getEndRed(), (int) m.getEndGreen(),
              (int) m.getEndBlue(), m.getDuration() * ticksPerSecond));
        }

        //for visibility
        if (m.getStartTick() != m.getLastTick()) {
          builder.append(String.format("    <animate attributeType= \"xml\" begin= \"%dms\""
                  + " dur=\"%dms\" visibility=\"visible\"  fill=\"freeze\" />\n",
              m.getStartTick() * ticksPerSecond, m.getDuration() * ticksPerSecond));

        }

      }
      builder.append(String.format("</%s>\n", s.getSVGName()));
    }

    builder.append("</svg>");


    return builder.toString();
  }

  @Override
  public void addFeatures(Features features) {
    throw new UnsupportedOperationException("This view does not support this feature");
  }



}
