package hw05.model;

import java.awt.Dimension;
import java.util.ArrayList;

import hw05.model.shapes.IShape;

/**
 * This interface represents a {@link AnimatorModel} and allows for
 * the creation of animations.
 */
public interface AnimatorModel {


  /**
   * Returns the size of the canvas.
   *
   * @return the canvas size
   */
  Dimension getCanvasSize();

  /**
   * Returns the x offset.
   * @return the x offset
   */
  int getXOffset();

  /**
   * Returns the y offset.
   * @return the y offset
   */
  int getYOffset();


  /**
   * Draws all the {@link IShape}s to the screen.
   */
  void animate();

  /**
   * Gets a text description of all the {@link IShape}s and {@link Motion}s.
   *
   * @return the description of the animation
   */
  String getDescription();

  /**
   * Adds the given {@link IShape} to the list of shapes.
   *
   * @param s shape to be added to the list
   */
  void addShape(IShape s);

  /**
   * Gets the list of {@link IShape}s.
   *
   * @return the list of shapes
   */
  ArrayList<IShape> getShapes();


}
