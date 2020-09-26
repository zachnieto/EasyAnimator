package cs3500.animator.controller;

import cs3500.animator.view.EditorView;

/**
 * Represents a controller for an EditorView and AnimatorModel.
 */
public class Controller implements Features {

  private EditorView view;

  /**
   * Sets the view for the controller to use.
   *
   * @param v the view
   */
  public void setView(EditorView v) {
    view = v;
    //provide view with all the callbacks
    view.addFeatures(this);
  }


  @Override
  public void start() {
    view.startTimer();
    view.disableStart();
  }

  @Override
  public void pause() {
    view.pauseTimer();
  }

  @Override
  public void resume() {
    view.startTimer();
  }

  @Override
  public void restart() {
    view.restart();
  }

  @Override
  public void looping() {
    view.toggleLooping();
  }

  @Override
  public void setSpeed(int ticksPerSecond) {
    view.setSpeed(ticksPerSecond);
  }

  @Override
  public void selectedShape() {
    view.selectedShape();
  }

  @Override
  public void deleteShape(String shape) {
    view.deleteShape(shape);
  }

  @Override
  public void editKeyFrame(String name) {
    view.editKeyFrame(name);
  }

  @Override
  public void applyEdits() {
    view.applyEdits();
  }

  @Override
  public void createShape() {
    view.createShape();
  }

  @Override
  public void applyCreation() {
    view.applyCreation();
  }

  @Override
  public void selectKeyframe() {
    view.selectKeyframe();
  }

  @Override
  public void deleteKeyFrame() {
    view.deleteKeyFrame();
  }

  @Override
  public void createKeyFrame() {
    view.createKeyFrame();
  }

  @Override
  public void openKeyFrameCreator() {
    view.openKeyFrameCreator();
  }

  @Override
  public void selectedMotion() {
    view.selectedMotion();
  }

  @Override
  public void setCurrentTick(int tick) {
    view.setCurrentTick(tick);
  }

  @Override
  public void deleteLayer() {
    view.deleteLayer();
  }


}
