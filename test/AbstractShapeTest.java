import org.junit.Test;

import java.util.ArrayList;

import hw05.model.Motion;
import hw05.model.shapes.Ellipse;
import hw05.model.shapes.IShape;
import hw05.model.shapes.Rectangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Testing class for AbstractShapes.
 */
public class AbstractShapeTest {

  Motion m1;
  Motion m2;
  ArrayList<Motion> motions;
  IShape s1;
  ArrayList<IShape> shapes;

  private void initShapes() {
    m1 = new Motion(1, 200, 200,  50, 100,
            255, 0, 0, 0,10, 200, 200 ,
            50, 100, 255, 0, 0,0);
    m2 = new Motion(10,200, 200, 50, 100, 255,
            0, 0, 0,50, 200, 200 , 50, 100,
            255, 0, 0,0);

    motions = new ArrayList<>();
    motions.add(m1);
    motions.add(m2);

    s1 = new Rectangle(motions, 255, 0, 0, 200, 200, 50, 100, 0,0,"s1");
    shapes = new ArrayList<>();
    shapes.add(s1);
  }

  @Test
  public void testToString() {
    initShapes();
    assertEquals("shape s1 Rectangle",s1.toString());
    IShape c = new Ellipse(motions, 0, 100, 255, 100,100,15,20,0,0, "C");
    assertEquals("shape C Ellipse",c.toString());
  }

  @Test
  public void testEquals() {
    initShapes();
    IShape s2 = new Rectangle(motions, 255, 0, 0, 200, 200,
                    50, 100, 0,0, "s1");
    IShape s3 = new Rectangle(new ArrayList<>(), 255, 0, 0,
            200, 200, 50, 100,0,0, "s1");
    //assertEquals(s1, s2);
    assertNotEquals(s1, s3);
    assertNotEquals(s2, s3);
  }

  @Test
  public void testHashCode() {
    initShapes();
    IShape s2 =
            new Rectangle(motions, 255, 0, 0, 200,
                    200, 50, 100, 0,0,"s1");
    IShape s3 = new Rectangle(new ArrayList<>(), 255, 0, 0,
            200, 200, 50, 100, 0,0,"s1");
    assertEquals(s1.hashCode(), s2.hashCode());
    assertNotEquals(s1.hashCode(), s3.hashCode());
    assertNotEquals(s2.hashCode(), s3.hashCode());
  }

  @Test
  public void testGetMotions() {
    initShapes();
    assertEquals(2,motions.size());
    assertEquals("  1 200 200 50 100 255  0  0   10 200 200 50 100 255  0  0",
            motions.get(0).toString());
    assertEquals(" 10 200 200 50 100 255  0  0   50 200 200 50 100 255  0  0",
            motions.get(1).toString());
  }

  @Test
  public void testAddMotions() {
    initShapes();
    assertEquals(2, s1.getMotions().size());
    s1.addMotion(m1);
    assertEquals(3, s1.getMotions().size());
    s1.addMotion(m2);
    assertEquals(4, s1.getMotions().size());
    assertEquals("motion s1  1 200 200 50 100 255  0  0   10 200 200 50 100 255  0  0\n"
                    + "motion s1 10 200 200 50 100 255  0  0   50 200 200 50 100 255  0  0\n"
                    + "motion s1  1 200 200 50 100 255  0  0   10 200 200 50 100 255  0  0\n"
                    + "motion s1 10 200 200 50 100 255  0  0   50 200 200 50 100 255  0  0\n",
            s1.motionDescription());
  }

  @Test
  public void testUpdate() {
    initShapes();
    m2 = new Motion(1, 200, 200, 50, 100,
            255, 0, 0, 0,10, 500 , 500,
        80, 50, 0, 255, 0,0);
    ArrayList<Motion> m = new ArrayList<>();
    m.add(m2);
    IShape s2 = new Rectangle(m, 255, 0, 0, 200,
            200, 50, 100,0,0, "copy");
    s1.update(2);
    //tests that the fields are same bc it is not moving or changing at this tick
    assertEquals(200, s1.getXPos(), .001);
    assertEquals(200, s1.getYPos(), .001);
    assertEquals(255, s1.getRed(), .001);
    assertEquals(0, s1.getGreen(), .001);
    assertEquals(0, s1.getBlue(), .001);
    assertEquals(100, s1.getHeight(), .001);
    assertEquals(50, s1.getWidth(), .001);
    //tests when all the changeable fields are changing. All these values should be updated
    s2.update(2);
    assertEquals(233.333, s2.getXPos(), .001);
    assertEquals(233.333, s2.getYPos(), .001);
    assertEquals(226, s2.getRed(), .001);
    assertEquals(28, s2.getGreen(), .001);
    assertEquals(0, s2.getBlue(), .001);
    assertEquals(94.444, s2.getHeight(), .001);
    assertEquals(53.333, s2.getWidth(), .001);

  }

  @Test
  public void testGetters() {
    initShapes();
    assertEquals(2, s1.getMotions().size());
    assertEquals(255, s1.getRed(), .001);
    assertEquals(0, s1.getGreen(), .001);
    assertEquals(0, s1.getBlue(), .001);
    assertEquals(100, s1.getHeight(), .001);
    assertEquals(50, s1.getWidth(), .001);
    assertEquals(200, s1.getXPos(), .001);
    assertEquals(200, s1.getYPos(), .001);
    assertEquals("s1", s1.getName());

  }

}