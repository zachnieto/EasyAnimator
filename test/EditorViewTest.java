import cs3500.animator.view.EditorView;
import org.junit.Test;

/**
 * Test for EditorView.
 */
public class EditorViewTest {

  @Test (expected = NullPointerException.class)
  public void testInvalidConstructors() {
    EditorView ev = new EditorView(null);
  }


}
