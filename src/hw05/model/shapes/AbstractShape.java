package hw05.model.shapes;


import java.util.ArrayList;
import java.util.Objects;
import hw05.model.Motion;

/**
 * Abstract class for all common functionalities of Shapes.
 */
abstract class AbstractShape implements IShape {

  private ArrayList<Motion> motions;
  private double red;
  private double green;
  private double blue;
  private double xPos;
  private double yPos;
  private double width;
  private double height;
  private double angle;
  private int layer;
  protected String name;

  /**
   * Constructs an {@link AbstractShape} using the given parameters.
   *
   * @param motions the list of motions
   * @param red     red value of the shape
   * @param green   green value of the shape
   * @param blue    blue value of the shape
   * @param xPos    the x position of the shape
   * @param yPos    the y position of the shape
   * @param width   the width of the shape
   * @param height  the height of the shape
   * @param name    the name of the shape
   */
  public AbstractShape(ArrayList<Motion> motions, double red, double green, double blue, int xPos,
      int yPos, int width, int height, int angle, int layer, String name)
      throws IllegalArgumentException {

    Objects.requireNonNull(motions, "motions cant be null");
    Objects.requireNonNull(name, "name cant be null");

    this.motions = motions;
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.xPos = xPos;
    this.yPos = yPos;
    this.width = width;
    this.height = height;
    this.angle = angle;
    this.layer = layer;
    this.name = name;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof IShape)) {
      return false;
    }

    return ((IShape) that).motionDescription().equals(this.motionDescription());
  }

  @Override
  public int hashCode() {
    return this.motionDescription().hashCode();
  }

  @Override
  public String toString() {
    return "shape " + this.name + " " + this.getClass().getSimpleName();
  }

  @Override
  public ArrayList<Motion> getMotions() {
    return new ArrayList<>(this.motions);
  }

  @Override
  public void update(int tick) {

    for (Motion m : motions) {
      if (m.inTick(tick)) {
        this.xPos += m.updateX();
        this.yPos += m.updateY();
        this.width += m.updateWidth();
        this.height += m.updateHeight();
        this.angle += m.updateAngle();

        this.red = Math.min(255, Math.max(0, (int) (m.updateColor()[0] + this.red)));
        this.green = Math.min(255, Math.max(0, (int) (m.updateColor()[1] + this.green)));
        this.blue = Math.min(255, Math.max(0, (int) (m.updateColor()[2] + this.blue)));
      }
    }

  }

  @Override
  public void addMotion(Motion m) {
    this.motions.add(m);
  }

  @Override
  public String motionDescription() {

    StringBuilder sb = new StringBuilder();

    for (Motion m : this.motions) {
      sb.append("motion ").append(this.name).append(m.toString()).append("\n");
    }

    return sb.toString();
  }

  @Override
  public double getXPos() {
    return xPos;
  }

  @Override
  public double getYPos() {
    return yPos;
  }

  @Override
  public double getWidth() {
    return width;
  }

  @Override
  public double getHeight() {
    return height;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public double getRed() {
    return red;
  }

  @Override
  public double getGreen() {
    return green;
  }

  @Override
  public double getBlue() {
    return blue;
  }

  @Override
  public double getAngle() {
    return angle;
  }

  @Override
  public int getLayer() {
    return this.layer;
  }

  @Override
  public void setLayer(int l) {
    this.layer = l;
  }

  @Override
  public void setX(int x) {
    this.xPos = x;
  }

  @Override
  public void setY(int y) {
    this.yPos = y;
  }

  @Override
  public void setWidth(int width) {
    this.width = width;
  }

  @Override
  public void setHeight(int height) {
    this.height = height;
  }

  @Override
  public void setRed(double red) {
    this.red = red;
  }

  @Override
  public void setGreen(double green) {
    this.green = green;
  }

  @Override
  public void setBlue(double blue) {
    this.blue = blue;
  }

  @Override
  public void setAngle(double angle) {
    this.angle = angle;
  }

  @Override
  public void removeKeyFrame(String frame) {

    Motion startMotion = null;
    Motion endMotion = null;

    for (Motion m : this.getMotions()) {
      if (m.startKeyFrameDesc().equals(frame)) {
        startMotion = m;
      }
      if (m.endKeyFrameDesc().equals(frame)) {
        endMotion = m;
      }
    }

    if (startMotion == null) {
      this.removeMotion(endMotion);
    } else if (endMotion == null) {
      this.removeMotion(startMotion);
    } else {
      endMotion.setEndTick(startMotion.getLastTick());
      endMotion.setEndX(startMotion.getEndX());
      endMotion.setEndY(startMotion.getEndY());
      endMotion.setEndWidth(startMotion.getEndWidth());
      endMotion.setEndHeight(startMotion.getEndHeight());
      endMotion.setEndRed(startMotion.getEndRed());
      endMotion.setEndGreen(startMotion.getEndGreen());
      endMotion.setEndBlue(startMotion.getEndBlue());
      this.removeMotion(startMotion);
      this.removeMotion(startMotion);
    }
    for (Motion m : this.getMotions()) {
      System.out.println(m.toString());
    }

  }

  @Override
  public void removeMotion(Motion m) {
    for (Motion motion : this.getMotions()) {
      if (m.equals(motion)) {
        this.motions.remove(m);
        break;
      }
    }
  }

  @Override
  public void addKeyFrame(int tick, double x, double y, double width, double height,
      double red, double green, double blue, double angle) {

    if (this.getMotions().size() == 0) {
      Motion newMotion = new Motion(tick, x, y, width,
          height, red, green, blue, angle, tick, x, y, width,
          height, red, green, blue, angle);

      this.addMotion(newMotion);
    } else {
      int max = -1;
      Motion endMotion = null;
      for (Motion m : this.getMotions()) {
        //if in the middle of a motion
        if (m.getStartTick() <= tick && m.getLastTick() >= tick) {
          Motion newMotion = new Motion(tick, x, y, width,
              height, red, green, blue, angle,
              m.getLastTick(), m.getEndX(), m.getEndY(), m.getEndWidth(), m.getEndHeight(),
              m.getEndRed(), m.getEndGreen(), m.getEndBlue(), m.getEndAngle());
          m.setEndTick(tick);
          m.setEndX(x);
          m.setEndY(y);
          m.setEndWidth(width);
          m.setEndHeight(height);
          m.setEndRed(red);
          m.setEndGreen(green);
          m.setEndBlue(blue);
          m.setEndAngle(angle);
          this.addMotion(newMotion);
          return;
        }

        int temp = m.getLastTick();
        if (temp > max) {
          max = temp;
          endMotion = m;
        }
      }
      // if last motion
      if (tick > max && endMotion != null) {
        this.addMotion(new Motion(endMotion.getLastTick(), endMotion.getEndX(),
            endMotion.getEndY(), endMotion.getEndWidth(), endMotion.getEndHeight(),
            endMotion.getEndRed(), endMotion.getEndGreen(), endMotion.getEndBlue(),
            endMotion.getEndAngle(), tick, x, y, width, height, red, green, blue, angle));
      }


    }


  }


}

