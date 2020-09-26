package hw05.model.shapes;

import java.awt.Shape;
import java.util.ArrayList;

import hw05.model.Motion;

/**
 * This interface represents a {@link Shape} object and all the properties that
 * the shape has.
 */
public interface IShape {

  /**
   * Removes a motion from the shape.
   * @param m Motion to be removed
   */
  void removeMotion(Motion m);

  /**
   * Removes a keyframe from the shape.
   * @param frame String name of keyframe to be removed
   */
  void removeKeyFrame(String frame);

  /**
   * Updates the {@link Shape}'s fields to represent the {@link Shape} 1 tick later.
   *
   * @param tick Current tick of the program
   */
  void update(int tick);

  /**
   * Adds the given motion to the list of {@link Shape}'s motions.
   *
   * @param m Motion to be added
   */
  void addMotion(Motion m);

  /**
   * Gets the description for the motion.
   *
   * @return The motion's description
   */
  String motionDescription();

  /**
   * Gets all the motions for the {@link Shape} in a list.
   *
   * @return the list of motions for the shape
   */
  ArrayList<Motion> getMotions();


  /**
   * Gets the x position of the {@link Shape}.
   *
   * @return the x position of the shape
   */
  double getXPos();

  /**
   * Gets the y position of the {@link Shape}.
   *
   * @return the y position of the shape
   */
  double getYPos();

  /**
   * Gets the red color of the {@link IShape}.
   *
   * @return the red color of the shape
   */
  double getRed();

  /**
   * Gets the green color of the {@link IShape}.
   *
   * @return the green color of the shape
   */
  double getGreen();

  /**
   * Gets the blue color of the {@link IShape}.
   *
   * @return the blue color of the shape
   */
  double getBlue();

  /**
   * Gets the width of the {@link Shape}.
   *
   * @return the width of the shape
   */
  double getWidth();

  /**
   * Gets the height of the {@link Shape}.
   *
   * @return the height of the shape
   */
  double getHeight();

  /**
   * Gets the name of the {@link Shape}.
   *
   * @return the name of the shape
   */
  String getName();

  /**
   * Gets the current angle.
   *
   * @return the angle
   */
  double getAngle();

  /**
   * Gets the java.awt version of the shape.
   *
   * @return the shape
   */
  Shape getShape();

  /**
   * Sets the x value to the given value.
   *
   * @param x the x value
   */
  void setX(int x);

  /**
   * Sets the y value to the given value.
   *
   * @param y the y value
   */
  void setY(int y);

  /**
   * Sets the width to the given value.
   *
   * @param w the width
   */
  void setWidth(int w);

  /**
   * Sets the height to the given value.
   *
   * @param h the height
   */
  void setHeight(int h);

  /**
   * Sets the red value.
   *
   * @param red red value
   */
  void setRed(double red);

  /**
   * Sets the green value.
   *
   * @param green green value
   */
  void setGreen(double green);

  /**
   * Sets the blue value.
   *
   * @param blue blue value
   */
  void setBlue(double blue);

  /**
   * Sets the angle.
   *
   * @param angle angle
   */
  void setAngle(double angle);

  /**
   * Returns the SVG name of the shape.
   * @return String SVG name of the shape
   */
  String getSVGName();

  /**
   * Returns the X attribute of the shape in SVG format.
   * @return String X attribute of the shape in SVG format
   */
  String getXAttribute();

  /**
   * Returns the Y attribute of the shape in SVG format.
   * @return String Y attribute of the shape in SVG format
   */
  String getYAttribute();

  /**
   * Returns the width attribute of the shape in SVG format.
   * @return String width attribute of the shape in SVG format
   */
  String getWidthAttribute();

  /**
   * Returns the height attribute of the shape in SVG format.
   * @return String height attribute of the shape in SVG format
   */
  String getHeightAttribute();

  /**
   * Returns the layer of the shape.
   * @return int layer of the shape
   */
  int getLayer();

  /**
   * Sets the layer of the shape.
   * @param l int layer to be set
   */
  void setLayer(int l);

  /**
   * Adds a keyframe to the shape.
   *
   * @param tick tick of keyframe
   * @param x x value at keyframe
   * @param y y value at keyframe
   * @param width width at keyframe
   * @param height height at keyframe
   * @param red red value at keyframe
   * @param green green value at keyframe
   * @param blue blue value at keyframe
   */
  void addKeyFrame(int tick, double x, double y, double width,
      double height, double red, double green, double blue, double angle);
}

