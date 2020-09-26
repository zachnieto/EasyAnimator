import org.junit.Test;

import java.util.ArrayList;

import hw05.model.Motion;
import hw05.model.shapes.Rectangle;
import hw05.model.shapes.IShape;
import static org.junit.Assert.assertEquals;

/**
 * Testing class for {@link Rectangle}.
 */
public class RectangleTest {

  Motion m1;
  Motion m2;
  ArrayList<Motion> motions;
  IShape s1;

  private void initMotions() {
    m1 = new Motion(1, 200, 200,  50, 100,
        255, 0, 0, 0,10, 200, 200 ,
        50, 100, 255, 0, 0,0);
    m2 = new Motion(10,200, 200, 50, 100, 255,
        0, 0, 0,50, 200, 200 , 50, 100,
        255, 0, 0,0);

    motions = new ArrayList<>();
    motions.add(m1);
    motions.add(m2);

  }

  @Test
  public void testConstructor() {
    initMotions();
    s1 = new Rectangle(motions, 255, 0, 0, 200, 200, 50,
        100,0,0, "R");
    assertEquals("R", s1.getName());
  }

  @Test (expected = NullPointerException.class)
  public void testConstructorFail1() {
    s1 = new Rectangle(null, 255, 0, 0, 200, 200, 50,
        100,0,0, "C");
  }

  @Test (expected = NullPointerException.class)
  public void testConstructorFail3() {
    initMotions();
    s1 = new Rectangle(motions, 255, 0, 0, 200, 200, 50,
        100,0,0, null);
  }


}