import cs3500.animator.controller.Features;
import cs3500.animator.view.EditorView;
import cs3500.animator.view.VisualView;

/**
 * Class EditorView mock that is just used for testing.
 */
public class EditorMock extends EditorView {

  public String test = "";

  public EditorMock(VisualView view) {
    super(view);
    test += "0 ";
  }

  @Override
  public void run() {
    test += "1 ";
  }

  @Override
  public void startTimer() {
    test += "2 ";
  }

  @Override
  public void disableStart() {
    test += "3 ";
  }

  @Override
  public void pauseTimer() {
    test += "4 ";
  }

  @Override
  public void restart() {
    test += "5 ";
  }

  @Override
  public void toggleLooping() {
    test += "6 ";
  }

  @Override
  public void setSpeed(int ticksPerSecond) {
    test += "7 ";
  }

  @Override
  public void selectedShape() {
    test += "8 ";
  }

  @Override
  public void selectedMotion() {
    test += "9 ";
  }

  @Override
  public void deleteShape(String shape) {
    test += "10 ";
  }

  @Override
  public void selectKeyframe() {
    test += "11 ";
  }

  @Override
  public void openKeyFrameCreator() {
    test += "12 ";
  }

  @Override
  public void createKeyFrame() {
    test += "13 ";
  }

  @Override
  public void deleteKeyFrame() {
    test += "14 ";
  }

  @Override
  public void editKeyFrame(String shapeName) {
    test += "15 ";
  }

  @Override
  public void createShape() {
    test += "16 ";
  }

  @Override
  public void applyEdits() {
    test += "17 ";
  }

  @Override
  public void applyCreation() {
    test += "18 ";
  }


  @Override
  public void addFeatures(Features features) {
    test += "19 ";
  }

  @Override
  public void setCurrentTick(int tick) {
    test += "20 ";
  }

  @Override
  public void deleteLayer() {
    test += "21 ";
  }



}