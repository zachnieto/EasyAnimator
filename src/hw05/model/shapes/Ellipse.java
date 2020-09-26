package hw05.model.shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import hw05.model.Motion;

/**
 * Represents an Ellipse {@link IShape}.
 */
public class Ellipse extends AbstractShape {


  /**
   * Constructs an Ellipse with just the name.
   *
   * @param name name for the ellipse
   */
  public Ellipse(String name) {
    this(new ArrayList<>(), 255, 0, 0, 0, 0,
            0, 0, 0, 0, name);
  }

  public Ellipse(String name, int layer) {
    this(new ArrayList<>(), 255, 0, 0, 0, 0,
            0, 0, 0, layer, name);
  }

  /**
   * Constructs an {@link Ellipse} using the {@link AbstractShape} parent constructor.
   *
   * @param motions the list of motions
   * @param red red value of the shape
   * @param green green value of the shape
   * @param blue blue value of the shape
   * @param xPos the x position of the shape
   * @param yPos the y position of the shape
   * @param width the width of the shape
   * @param height the height of the shape
   * @param name the name of the shape
   */
  public Ellipse(ArrayList<Motion> motions, double red, double green, double blue, int xPos,
                 int yPos, int width, int height, int angle, int layer, String name) {
    super(motions, red, green, blue, xPos, yPos, width, height, angle, layer, name);
  }

  @Override
  public Shape getShape() {
    return new Ellipse2D.Double(this.getXPos(), this.getYPos(), this.getWidth(), this.getHeight());
  }

  @Override
  public String getSVGName() {
    return "ellipse";
  }

  @Override
  public String getXAttribute() {
    return "cx";
  }

  @Override
  public String getYAttribute() {
    return "cy";
  }

  @Override
  public String getWidthAttribute() {
    return "rx";
  }

  @Override
  public String getHeightAttribute() {
    return "ry";
  }
}
