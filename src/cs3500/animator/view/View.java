package cs3500.animator.view;

import cs3500.animator.controller.Features;

/**
 * Interface representing a View of a model that can be ran.
 */
public interface View {

  /**
   * Runs the view by displaying the Animation depending on what type of view it is.
   */
  void run();

  /**
   * Adds the features that the view could use to edit the animation.
   * @param features Features that the view could use to edit the animation in some way
   */
  void addFeatures(Features features);


}
