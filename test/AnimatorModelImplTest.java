import org.junit.Test;


import java.util.ArrayList;

import hw05.model.AnimatorModel;
import hw05.model.AnimatorModelImpl;
import hw05.model.Motion;
import hw05.model.shapes.Rectangle;
import hw05.model.shapes.IShape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Testing class for {@link AnimatorModelImpl}.
 */
public class AnimatorModelImplTest {

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
        0, 0, 0,50, 200, 200 , 50,
        100, 255, 0, 0,0);

    motions = new ArrayList<>();
    motions.add(m1);
    motions.add(m2);

    s1 = new Rectangle(motions, 255, 0, 0, 200, 200, 50,
        100, 0,0,"s1");
    shapes = new ArrayList<>();
    shapes.add(s1);
  }

  @Test
  public void testHashCode() {
    initShapes();
    AnimatorModel ani = new AnimatorModelImpl(shapes);
    AnimatorModel ani2 = new AnimatorModelImpl(shapes);
    AnimatorModel ani3 = new AnimatorModelImpl(new ArrayList<>());
    assertEquals(ani.hashCode(), ani2.hashCode());
    assertNotEquals(ani.hashCode(), ani3.hashCode());
    assertNotEquals(ani2.hashCode(), ani3.hashCode());
  }

  @Test
  public void testEquals() {
    initShapes();
    AnimatorModel ani = new AnimatorModelImpl(shapes);
    AnimatorModel ani2 = new AnimatorModelImpl(shapes);
    AnimatorModel ani3 = new AnimatorModelImpl(new ArrayList<>());
    assertEquals(ani, ani2);
    assertNotEquals(ani, ani3);
    assertNotEquals(ani2, ani3);
  }

  @Test
  public void testAnimate() {
    m2 = new Motion(0, 200, 200,  50, 100,
            255, 0, 0, 0,10, 500, 500 ,
            80, 50, 0, 255, 0,0);
    ArrayList<Motion> m = new ArrayList<>();
    m.add(m2);
    IShape s2 = new Rectangle(m, 255, 0, 0, 200,
            200, 50, 100,0,0, "copy");
    ArrayList<IShape> list = new ArrayList<>();
    list.add(s2);

    AnimatorModel ani = new AnimatorModelImpl(list);
    ani.animate();
    assertEquals(53, ani.getShapes().get(0).getWidth(), .001);
    assertEquals(95, ani.getShapes().get(0).getHeight(), .001);
    assertEquals(230, ani.getShapes().get(0).getXPos(), .001);
    assertEquals(230, ani.getShapes().get(0).getYPos(), .001);
    assertEquals(229.0, ani.getShapes().get(0).getRed(), .001);
    assertEquals(25, ani.getShapes().get(0).getGreen(), .001);
    assertEquals(0, ani.getShapes().get(0).getBlue(), .001);
  }



  @Test
  public void testMotionDescription() {
    initShapes();
    assertEquals("motion s1  1 200 200 50 100 255  0  0   10 200 200 50 100 255  0  0\n"
                    + "motion s1 10 200 200 50 100 255  0  0   50 200 200 50 100 255  0  0\n",
            s1.motionDescription());
    s1.addMotion(m2);
    assertEquals("motion s1  1 200 200 50 100 255  0  0   10 200 200 50 100 255  0  0\n"
                    + "motion s1 10 200 200 50 100 255  0  0   50 200 200 50 100 255  0  0\n"
                    + "motion s1 10 200 200 50 100 255  0  0   50 200 200 50 100 255  0  0\n",
            s1.motionDescription());
  }

  @Test
  public void testValidAnimatorModelImplConstructor() {
    initShapes();
    AnimatorModel animator = new AnimatorModelImpl();
    AnimatorModel animator2 = new AnimatorModelImpl(shapes);
    assertEquals("canvas 0 0 500 500\n", animator.getDescription());
    assertEquals("canvas 0 0 500 500\nshape s1 Rectangle\n"
            + "motion s1  1 200 200 50 100 255  0  0   10 200 200 50 100 255  0  0\n"
            + "motion s1 10 200 200 50 100 255  0  0   50 200 200 50 100 255  0  0\n",
            animator2.getDescription());
    initShapes();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAnimatorModelImplConstructor() {
    AnimatorModel animator2 = new AnimatorModelImpl(null);
  }

  @Test
  public void testAddShape() {
    initShapes();
    AnimatorModel ani = new AnimatorModelImpl();
    assertEquals("canvas 0 0 500 500\n", ani.getDescription());
    ani.addShape(s1);
    assertEquals("canvas 0 0 500 500\nshape s1 Rectangle\n"
            + "motion s1  1 200 200 50 100 255  0  0   10 200 200 50 100 255  0  0\n"
            + "motion s1 10 200 200 50 100 255  0  0   50 200 200 50 100 255  0  0\n",
            ani.getDescription());
    //add another shape
    ani.addShape(s1);
    assertEquals("canvas 0 0 500 500\nshape s1 Rectangle\n"
            + "motion s1  1 200 200 50 100 255  0  0   10 200 200 50 100 255  0  0\n"
            + "motion s1 10 200 200 50 100 255  0  0   50 200 200 50 100 255  0  0\n"
            + "shape s1 Rectangle\n"
            + "motion s1  1 200 200 50 100 255  0  0   10 200 200 50 100 255  0  0\n"
            + "motion s1 10 200 200 50 100 255  0  0   50 200 200 50 100 255  0  0\n",
            ani.getDescription());
  }

  @Test
  public void testDescription() {
    initShapes();
    AnimatorModel m = new AnimatorModelImpl();
    assertEquals("canvas 0 0 500 500\n", m.getDescription());
    m.addShape(s1);
    assertEquals("canvas 0 0 500 500\nshape s1 Rectangle\n"
                    + "motion s1  1 200 200 50 100 255  0  0   10 200 200 50 100 255  0  0\n"
                    + "motion s1 10 200 200 50 100 255  0  0   50 200 200 50 100 255  0  0\n",
            m.getDescription());
  }



}