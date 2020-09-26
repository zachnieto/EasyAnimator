package hw05.model.shapes;

import java.awt.Shape;
import java.util.ArrayList;
import hw05.model.Motion;

/**
 * Represents a Rectangle {@link IShape}.
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructs a Rectangle with just the name.
   *
   * @param name name of the rectangle
   */
  public Rectangle(String name) {
    this(new ArrayList<>(), 0, 255,
            0, 0, 0, 1, 0, 0, 0, name);
  }

  public Rectangle(String name, int layer) {
    this(new ArrayList<>(), 0, 255,
            0, 0, 0, 1, 0, 0, layer, name);
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
  public Rectangle(ArrayList<Motion> motions, double red, double green,
                   double blue, int xPos, int yPos,
                   int width, int height, int angle, int layer, String name) {
    super(motions, red, green, blue, xPos, yPos, width, height, angle, layer, name);
  }


  @Override
  public Shape getShape() {
    return new java.awt.Rectangle((int) this.getXPos(), (int) this.getYPos(),
            (int) this.getWidth(), (int) this.getHeight());
  }

  @Override
  public String getSVGName() {
    return "rect";
  }

  @Override
  public String getXAttribute() {
    return "x";
  }

  @Override
  public String getYAttribute() {
    return "y";
  }

  @Override
  public String getWidthAttribute() {
    return "width";
  }

  @Override
  public String getHeightAttribute() {
    return "height";
  }
}
