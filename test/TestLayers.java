import static org.junit.Assert.assertEquals;

import hw05.model.shapes.Ellipse;
import hw05.model.shapes.IShape;
import hw05.model.shapes.Rectangle;
import org.junit.Test;

/**
 * Tests the layers of a shape is being used and updated correctly.
 */
public class TestLayers {

  @Test
  public void testLayers() {
    IShape s = new Ellipse("asdf");
    assertEquals(0, s.getLayer());
    s.setLayer(1);
    assertEquals(1,s.getLayer());

    IShape s2 = new Rectangle("rect", 9);
    assertEquals(9, s2.getLayer());


  }



}
