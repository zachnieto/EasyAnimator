import org.junit.Test;

import hw05.model.Motion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Testing class for {@link Motion}.
 */
public class MotionTest {

  Motion m1;
  Motion m2;
  Motion m3;

  private void initMotion() {
    m1 = new Motion(1, 200, 200, 50, 100, 255,
            0, 0, 0,10,200, 200 , 50,
        100, 255, 0, 0,0);
    m2 = new Motion(1, 200, 200, 50, 1, 255,
            0, 0, 0,10, 300, 400, 100,
        18, 0, 0, 255,0);
    m3 = new Motion(1, 200, 200, 50, 1, 255,
            0, 0, 0,10, 300, 400, 100,
        18, 0, 0, 255,0);
  }

  @Test
  public void testHashCode() {
    initMotion();
    assertNotEquals(m1.hashCode(), m2.hashCode());
    assertNotEquals(m1.hashCode(), m3.hashCode());
    assertEquals(m3.hashCode(), m2.hashCode());
  }

  @Test
  public void testEquals() {
    initMotion();
    assertNotEquals(m1, m2);
    assertNotEquals(m1, m3);
    assertEquals(m2, m3);
  }

  @Test
  public void testConstructor() {
    m1 = new Motion(1, 200, 200, 50, 100, 255,
            0, 0, 0,10,200, 200 , 50, 100,
            255, 0, 0,0);
    assertTrue(m1.inTick(2));
  }

  @Test
  public void testInTick() {
    initMotion();
    assertTrue(m1.inTick(1));
    assertTrue(m1.inTick(5));
    assertTrue(m1.inTick(9));
    assertFalse(m1.inTick(10));
    assertTrue(m2.inTick(1));
    assertTrue(m2.inTick(5));
    assertTrue(m2.inTick(9));
    assertFalse(m2.inTick(10));
  }

  @Test
  public void testUpdateX() {
    initMotion();
    assertEquals(0, m1.updateX(), .001);
    assertEquals(0, m1.updateX(), .001);
    assertEquals(11.111, m2.updateX(), .001);
    assertEquals(11.111, m2.updateX(), .001);
  }

  @Test
  public void testUpdateY() {
    initMotion();
    assertEquals(0, m1.updateY(), .001);
    assertEquals(0, m1.updateY(), .001);
    assertEquals(22.222, m2.updateY(), .001);
    assertEquals(22.222, m2.updateY(), .001);
  }

  @Test
  public void testUpdateWidth() {
    initMotion();
    assertEquals(0, m1.updateWidth(), .001);
    assertEquals(0, m1.updateWidth(), .001);
    assertEquals(5.555, m2.updateWidth(), .001);
    assertEquals(5.555, m2.updateWidth(), .001);
  }

  @Test
  public void testUpdateHeight() {
    initMotion();
    assertEquals(0, m1.updateHeight(), .001);
    assertEquals(0, m1.updateHeight(), .001);
    assertEquals(1.888, m2.updateHeight(), .001);
    assertEquals(1.888, m2.updateHeight(), .001);
  }

  @Test
  public void testUpdateColor() {
    initMotion();
    assertEquals(0, m1.updateColor()[0], .001);
    assertEquals(0, m1.updateColor()[1], .001);
    assertEquals(0, m1.updateColor()[2], .001);
    assertEquals(-28.333, m2.updateColor()[0], .001);
    assertEquals(0, m2.updateColor()[1], .001);
    assertEquals(28.333, m2.updateColor()[2], .001);
  }

  @Test
  public void testToString() {
    initMotion();
    assertEquals("  1 200 200 50 100 255  0  0   10 200 200 50 100 255  0  0", m1.toString());
    assertEquals("  1 200 200 50  1 255  0  0   10 300 400 100 18  0  0 255", m2.toString());
  }

  @Test
  public void testGetters() {
    initMotion();
    assertEquals(1, m1.getStartTick());
    assertEquals(9, m1.getDuration());
    assertEquals(200, m1.getEndX(), .001);
    assertEquals(200, m1.getStartX(), .001);
    assertEquals(0, m1.getEndBlue(), .001);
    assertEquals(0, m1.getEndGreen(), .001);
    assertEquals(255, m1.getEndRed(), .001);
    assertEquals(200, m1.getEndY(), .001);
    assertEquals(0, m1.getStartBlue(), .001);
    assertEquals(0, m1.getStartGreen(), .001);
    assertEquals(255, m1.getStartRed(), .001);
    assertEquals(200, m1.getStartY(), .001);
    assertEquals(100, m1.getEndHeight(), .001);
    assertEquals(50, m1.getEndWidth(), .001);
    assertEquals(10, m1.getLastTick(), .001);
    assertEquals(100, m1.getStartHeight(), .001);
    assertEquals(50, m1.getStartWidth(), .001);
  }

}