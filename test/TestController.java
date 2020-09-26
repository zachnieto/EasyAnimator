import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.Controller;
import cs3500.animator.view.VisualView;
import hw05.model.AnimatorModelImpl;
import org.junit.Test;

/**
 * Tests the Controller with a mock to check the functions are called correctly.
 */
public class TestController {

  Controller controller;
  EditorMock mock;

  private void init() {
    controller = new Controller();
    mock = new EditorMock(new VisualView(new AnimatorModelImpl(), 1));
    controller.setView(mock);
  }

  @Test
  public void testStart() {
    init();
    controller.start();
    assertEquals("0 19 2 3 ", mock.test);
  }

  @Test
  public void testPause() {
    init();
    controller.pause();
    assertEquals("0 19 4 ", mock.test);
  }

  @Test
  public void testResume() {
    init();
    controller.resume();
    assertEquals("0 19 2 ", mock.test);
  }

  @Test
  public void testRestart() {
    init();
    controller.restart();
    assertEquals("0 19 5 ", mock.test);
  }

  @Test
  public void testLooping() {
    init();
    controller.looping();
    assertEquals("0 19 6 ", mock.test);
  }

  @Test
  public void testSetSpeed() {
    init();
    controller.setSpeed(231);
    assertEquals("0 19 7 ", mock.test);
  }

  @Test
  public void testSelectedShape() {
    init();
    controller.selectedShape();
    assertEquals("0 19 8 ", mock.test);
  }

  @Test
  public void testDeleteShape() {
    init();
    controller.deleteShape("");
    assertEquals("0 19 10 ", mock.test);
  }

  @Test
  public void testEditKeyFrame() {
    init();
    controller.editKeyFrame("");
    assertEquals("0 19 15 ", mock.test);
  }

  @Test
  public void testApplyEdits() {
    init();
    controller.applyEdits();
    assertEquals("0 19 17 ", mock.test);
  }

  @Test
  public void testCreateShape() {
    init();
    controller.createShape();
    assertEquals("0 19 16 ", mock.test);
  }

  @Test
  public void testApplyCreation() {
    init();
    controller.applyCreation();
    assertEquals("0 19 18 ", mock.test);
  }

  @Test
  public void testSelectKeyFrame() {
    init();
    controller.selectKeyframe();
    assertEquals("0 19 11 ", mock.test);
  }

  @Test
  public void testDeleteKeyFrame() {
    init();
    controller.deleteKeyFrame();
    assertEquals("0 19 14 ", mock.test);
  }

  @Test
  public void testCreateKeyFrame() {
    init();
    controller.createKeyFrame();
    assertEquals("0 19 13 ", mock.test);
  }

  @Test
  public void testOpenKeyFrameEditor() {
    init();
    controller.openKeyFrameCreator();
    assertEquals("0 19 12 ", mock.test);
  }

  @Test
  public void testSelectedMotion() {
    init();
    controller.selectedMotion();
    assertEquals("0 19 9 ", mock.test);
  }

  @Test
  public void testSetCurrentTick() {
    init();
    controller.setCurrentTick(1);
    assertEquals("0 19 20 ", mock.test);
  }

  @Test
  public void testDeleteLayer() {
    init();
    controller.deleteLayer();
    assertEquals("0 19 21 ", mock.test);
  }
}
