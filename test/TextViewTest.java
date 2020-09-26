import hw05.model.AnimatorModelImpl.Builder;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.TextView;
import cs3500.animator.view.View;
import hw05.model.AnimatorModel;
import hw05.model.AnimatorModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for TextView.
 */
public class TextViewTest {

  @Test (expected = NullPointerException.class)
  public void testConstructor() {
    View v = new TextView(null, new StringBuilder());
  }

  @Test (expected = NullPointerException.class)
  public void testConstructor2() {
    View v = new TextView(new AnimatorModelImpl(), null);
  }

  @Test
  public void testRun() {
    StringBuilder outLog = new StringBuilder();
    AnimationReader reader = new AnimationReader();
    Readable r = null;
    try {
      r = new FileReader("smalldemo.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    AnimatorModel model = reader.parseFile(r, new AnimatorModelImpl.Builder());
    View v = new TextView(model, outLog);
    v.run();
    assertEquals("canvas 200 70 360 360\n"
            + "shape R Rectangle\n"
            + "motion R  1 200 200 50 100 255  0  0   10 200 200 50 100 255  0  0\n"
            + "motion R 10 200 200 50 100 255  0  0   50 300 300 50 100 255  0  0\n"
            + "motion R 50 300 300 50 100 255  0  0   51 300 300 50 100 255  0  0\n"
            + "motion R 51 300 300 50 100 255  0  0   70 300 300 25 100 255  0  0\n"
            + "motion R 70 300 300 25 100 255  0  0   100 200 200 25 100 255  0  0\n"
            + "shape C Ellipse\n"
            + "motion C  6 440 70 120 60  0  0 255   20 440 70 120 60  0  0 255\n"
            + "motion C 20 440 70 120 60  0  0 255   50 440 250 120 60  0  0 255\n"
            + "motion C 50 440 250 120 60  0  0 255   70 440 370 120 60  0 170 85\n"
            + "motion C 70 440 370 120 60  0 170 85   80 440 370 120 60  0 255  0\n"
            + "motion C 80 440 370 120 60  0 255  0   100 440 370 120 60  0 255  0\n",
            outLog.toString());

  }

  @Test
  public void testRun2() {
    StringBuilder outLog = new StringBuilder();
    AnimationReader reader = new AnimationReader();
    Readable r = null;
    try {
      r = new FileReader("big-bang-big-crunch.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    AnimatorModel model = reader.parseFile(r, new AnimatorModelImpl.Builder());
    View v = new TextView(model, outLog);
    v.run();
    String[] lines = outLog.toString().split("\n");
    assertEquals(34906, lines.length);
  }

  AnimatorModel m;

  /**
   * Initializes a builder and create an AnimatorModelImpl.
   */
  private void init() {
    Builder builder = new Builder();
    m = builder.declareShape("r", "rectangle",0)
            .addMotion("r",1,0,0,50,50,255,255,255,0,10,
            70,70,25,100,0,0,0,0)
        .setBounds(0,0,100,100).build();

  }

  @Test
  public void testRun3() {
    init();
    StringBuilder log = new StringBuilder();
    TextView textView = new TextView(m,log);
    textView.run();
    assertEquals("canvas 0 0 100 100\n"
        + "shape r Rectangle\n"
        + "motion r  1  0  0 50 50 255 255 255   10 70 70 25 100  0  0  0\n",log.toString());
  }



}