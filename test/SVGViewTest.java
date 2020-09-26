import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.View;
import hw05.model.AnimatorModel;
import hw05.model.AnimatorModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for SVGView.
 */
public class SVGViewTest {

  @Test (expected = NullPointerException.class)
  public void testConstructor() {
    View v = new SVGView(null, new StringBuilder(), 30);
  }

  @Test (expected = NullPointerException.class)
  public void testConstructor2() {
    View v = new SVGView(new AnimatorModelImpl(), null, 30);
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
    View v = new SVGView(model, outLog, 30);
    v.run();
    String[] lines = outLog.toString().split("\n");
    assertEquals(26, lines.length);
    String lastMsg = String.join("\n",
            Arrays.copyOfRange(lines, lines.length - 3, lines.length));
    assertEquals("    <animate attributeType= \"xml\" begin= \"2400ms\" dur=\"600ms\" "
            + "visibility=\"visible\"  fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "</svg>", lastMsg);
  }

}