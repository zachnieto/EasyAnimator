package cs3500.animator.controller;

/**
 * Interface Features that contains all the features needed for the EditorView.
 */
public interface Features {

  /**
   * Starts the animation by starting the timer of the animation and disables the start button.
   */
  void start();

  /**
   * Pauses the animation by stopping the timer of the animation.
   */
  void pause();

  /**
   * Resumes the animation by starting the timer of the animation.
   */
  void resume();

  /**
   * Restarts the tick of the animation to 0.
   */
  void restart();

  /**
   * Toggles the looping option of the animation.
   */
  void looping();

  /**
   * Sets the speed of the animation to the desired given speed.
   * @param ticksPerSecond int desired speed
   */
  void setSpeed(int ticksPerSecond);

  /**
   * Assigns the selected shape to a field so that the view knows what to edit.
   */
  void selectedShape();

  /**
   * Deletes the shape from the animation.
   * @param shape String name of the shape that needs to be deleted
   */
  void deleteShape(String shape);

  /**
   * Edits the keyframe of the animation given the shape that the keyframe is in.
   * @param name Name of the shape that the keyframe is in
   */
  void editKeyFrame(String name);

  /**
   * Applies the edits of the animation to the actual animation.
   */
  void applyEdits();

  /**
   * Creates a new shape that the animation is able to display.
   */
  void createShape();

  /**
   * Applies the new shape creation to the actual animation.
   */
  void applyCreation();

  /**
   * Assigns the keyframe that the user has selected to a field.
   */
  void selectKeyframe();

  /**
   * Deletes the keyframe of an animation shape.
   */
  void deleteKeyFrame();

  /**
   * Creates a new keyframe of an animation shape.
   */
  void createKeyFrame();

  /**
   * Opens the panels of options you have when trying to make a new keyframe.
   */
  void openKeyFrameCreator();

  /**
   * Records the selected Motion that will be modified.
   */
  void selectedMotion();

  /**
   * Sets the current tick to the given tick.
   *
   * @param tick tick to be shown
   */
  void setCurrentTick(int tick);

  /**
   * Deletes the selected layers.
   *
   */
  void deleteLayer();
}
