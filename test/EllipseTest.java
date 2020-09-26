import org.junit.Test;

import java.util.ArrayList;

import hw05.model.Motion;
import hw05.model.shapes.Ellipse;
import hw05.model.shapes.IShape;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for {@link Ellipse}.
 */
public class EllipseTest {


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
    s1 = new Ellipse(motions, 255, 0, 0, 200, 200, 50,
        100,0,0, "C");
    assertEquals("C", s1.getName());
  }

  @Test (expected = NullPointerException.class)
  public void testConstructorFail1() {
    initMotions();
    s1 = new Ellipse(null, 255, 0, 0, 200, 200, 50,
        100,0,0, "C");
  }


  @Test (expected = NullPointerException.class)
  public void testConstructorFail3() {
    initMotions();
    s1 = new Ellipse(motions, 255, 0, 0, 200, 200, 50,
        100,0,0, null);
  }

}