import static org.junit.Assert.assertEquals;

import hw05.model.Motion;
import hw05.model.shapes.IShape;
import hw05.model.shapes.Rectangle;
import org.junit.Test;

/**
 * Tests that the rotation works as intended in both directions and that a shape is able to
 * implement it.
 */
public class TestRotation {


  @Test
  public void testUpdateRotation() {
    IShape s = new Rectangle("rect");
    Motion m = new Motion(0,0,0,0,0,0,0,
        0,0,20,0,0,0,0,0,0,
        0,20);
    s.addMotion(m);
    assertEquals(0,s.getAngle(),0.001);
    //check angle is expected in the middle
    for (int i = 0; i < 10 ;i++) {
      s.update(10);
    }
    assertEquals(10,s.getAngle(),0.001);
    //check angle is expected at the end
    for (int i = 0; i < 10 ;i++) {
      s.update(10);
    }
    assertEquals(20, s.getAngle(),0.001);
  }

  @Test
  public void testCounterClockwiseRotation() {
    IShape s = new Rectangle("rect");
    Motion m = new Motion(0,0,0,0,0,0,0,
        0,0,20,0,0,0,0,0,0,
        0,-20);
    s.addMotion(m);
    assertEquals(0,s.getAngle(),0.001);
    //check angle is expected in the middle
    for (int i = 0; i < 10 ;i++) {
      s.update(10);
    }
    assertEquals(-10,s.getAngle(),0.001);
    //check angle is expected at the end
    for (int i = 0; i < 10 ;i++) {
      s.update(10);
    }
    assertEquals(-20, s.getAngle(),0.001);
  }


}
