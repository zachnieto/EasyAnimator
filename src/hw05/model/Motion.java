package hw05.model;


/**
 * Represents a Motion of a shape.
 */
public class Motion {

  private int startTick;
  private double startX;
  private double startY;
  private double startWidth;
  private double startHeight;
  private double startRed;
  private double startGreen;
  private double startBlue;
  private double startAngle;
  private int endTick;
  private double endX;
  private double endY;
  private double endWidth;
  private double endHeight;
  private double endRed;
  private double endGreen;
  private double endBlue;
  private double endAngle;

  /**
   * Constructs a Motion using the start parameters and end parameters.
   *
   * @param startTick   tick at start
   * @param startX      x position at start
   * @param startY      y position at start
   * @param startWidth  width at start
   * @param startHeight height at start
   * @param startRed    red value at start
   * @param startGreen  green value at start
   * @param startBlue   blue value at start
   * @param endTick     tick at end
   * @param endX        x position at end
   * @param endY        y position at end
   * @param endWidth    width at end
   * @param endHeight   height at end
   * @param endRed      red value at end
   * @param endGreen    green value at end
   * @param endBlue     blue value at end
   */
  public Motion(int startTick, double startX, double startY, double startWidth, double startHeight,
      double startRed, double startGreen, double startBlue, double startAngle, int endTick,
      double endX, double endY, double endWidth, double endHeight, double endRed, double endGreen,
      double endBlue, double endAngle) {

    this.startTick = startTick;
    this.startX = startX;
    this.startY = startY;
    this.startWidth = startWidth;
    this.startHeight = startHeight;
    this.startRed = startRed;
    this.startGreen = startGreen;
    this.startBlue = startBlue;
    this.startAngle = startAngle;
    this.endTick = endTick;
    this.endX = endX;
    this.endY = endY;
    this.endWidth = endWidth;
    this.endHeight = endHeight;
    this.endRed = endRed;
    this.endGreen = endGreen;
    this.endBlue = endBlue;
    this.endAngle = endAngle;
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof Motion)) {
      return false;
    }

    return that.toString().equals(this.toString());
  }

  @Override
  public String toString() {
    return String.format(" %2d %2d %2d %2d %2d %2d %2d %2d   %2d %2d %2d %2d %2d %2d %2d %2d",
        this.startTick, (int) this.startX, (int) this.startY, (int) this.startWidth,
        (int) this.startHeight, (int) this.startRed, (int) this.startGreen,
        (int) this.startBlue, this.endTick, (int) this.endX, (int) this.endY,
        (int) this.endWidth, (int) this.endHeight, (int) this.endRed,
        (int) this.endGreen, (int) this.endBlue);
  }

  /**
   * Determines if the given tick is between the start and end ticks.
   *
   * @param tick current tick
   * @return if the given tick is in between the start and end ticks
   */
  public boolean inTick(int tick) {
    return this.startTick <= tick && this.endTick > tick;
  }

  /**
   * Updates the x position for the next tick.
   *
   * @return the change in x position
   */
  public double updateX() {
    return (this.endX - this.startX) / (this.endTick - this.startTick);
  }

  /**
   * Updates the y position for the next tick.
   *
   * @return the change in y position
   */
  public double updateY() {
    return (this.endY - this.startY) / (this.endTick - this.startTick);
  }

  /**
   * Updates the width for the next tick.
   *
   * @return the change in width
   */
  public double updateWidth() {
    return (this.endWidth - this.startWidth) / (this.endTick - this.startTick);
  }

  /**
   * Updates the height for the next tick.
   *
   * @return the change in height
   */
  public double updateHeight() {
    return (this.endHeight - this.startHeight) / (this.endTick - this.startTick);
  }

  /**
   * Updates the red, green, and blue values for the next tick.
   *
   * @return array of length 3 with the red, green, and blue values for the next tick
   */
  public double[] updateColor() {

    double red = (this.endRed - this.startRed)
        / (this.endTick - this.startTick);
    double green = (this.endGreen - this.startGreen)
        / (this.endTick - this.startTick);
    double blue = (this.endBlue - this.startBlue)
        / (this.endTick - this.startTick);


    return new double[]{red, green, blue};
  }

  public double updateAngle() {
    return (this.endAngle - this.startAngle) / (this.endTick - this.startTick);
  }

  /**
   * Returns the last tick this motion has.
   *
   * @return int of the last tick
   */
  public int getLastTick() {
    return this.endTick;
  }

  /**
   * Returns the start tick of this motion.
   *
   * @return int of the starting tick
   */
  public int getStartTick() {
    return this.startTick;
  }

  /**
   * Returns the duration of the motion in ticks.
   *
   * @return int of the duration of the motion in ticks
   */
  public int getDuration() {
    return this.endTick - this.startTick;
  }


  /**
   * Returns the starting X.
   *
   * @return starting X
   */
  public double getStartX() {
    return this.startX;
  }

  /**
   * Returns the starting Y.
   *
   * @return starting Y
   */
  public double getEndX() {
    return this.endX;
  }

  /**
   * Returns the ending X.
   *
   * @return ending X
   */
  public double getStartY() {
    return this.startY;
  }

  /**
   * Returns the ending Y.
   *
   * @return double ending Y.
   */
  public double getEndY() {
    return this.endY;
  }

  /**
   * Returns the startRed value.
   *
   * @return startRed
   */
  public double getStartRed() {
    return this.startRed;
  }

  /**
   * Returns the endRed value.
   *
   * @return endRed
   */
  public double getEndRed() {
    return this.endRed;
  }

  /**
   * Returns the startBlue value.
   *
   * @return startBlue
   */
  public double getStartBlue() {
    return this.startBlue;
  }

  /**
   * Returns the endBlue value.
   *
   * @return endBlue
   */
  public double getEndBlue() {
    return this.endBlue;
  }

  /**
   * Returns the startGreen value.
   *
   * @return startGreen
   */
  public double getStartGreen() {
    return this.startGreen;
  }

  /**
   * Returns the startAngle.
   *
   * @return start angle
   */
  public double getStartAngle() {
    return this.startAngle;
  }

  /**
   * Returns the endGreen value.
   *
   * @return endGreen
   */
  public double getEndGreen() {
    return this.endGreen;
  }

  /**
   * Returns the startWidth value.
   *
   * @return startWidth
   */
  public double getStartWidth() {
    return this.startWidth;
  }

  /**
   * Returns the startHeight value.
   *
   * @return startHeight
   */
  public double getStartHeight() {
    return this.startHeight;
  }

  /**
   * Returns the endWidth value.
   *
   * @return endWidth
   */
  public double getEndWidth() {
    return this.endWidth;
  }

  /**
   * Returns the endHeight value.
   *
   * @return endHeight
   */
  public double getEndHeight() {
    return this.endHeight;
  }

  /**
   * Returns the end angle.
   *
   * @return endAngle
   */
  public double getEndAngle() {
    return this.endAngle;
  }

  /**
   * Returns the staring keyframe description.
   * @return String starting keyframe description
   */
  public String startKeyFrameDesc() {
    return String.format("%3d %3d %3d %3d %3d %3d %3d %3d",
        this.startTick, (int) this.startX, (int) this.startY, (int) this.startWidth,
        (int) this.startHeight, (int) this.startRed, (int) this.startGreen,
        (int) this.startBlue);
  }

  /**
   * Returns the ending keyframe description.
   * @return String ending keyframe description
   */
  public String endKeyFrameDesc() {
    return String.format("%3d %3d %3d %3d %3d %3d %3d %3d",
        this.endTick, (int) this.endX, (int) this.endY,
        (int) this.endWidth, (int) this.endHeight, (int) this.endRed,
        (int) this.endGreen, (int) this.endBlue);
  }

  /**
   * Sets the starting x.
   *
   * @param x x at start
   */
  public void setStartX(double x) {
    this.startX = x;
  }

  /**
   * Sets the starting y.
   *
   * @param y y at start
   */
  public void setStartY(double y) {
    this.startY = y;
  }

  /**
   * Sets the starting width.
   *
   * @param w width at start
   */
  public void setStartWidth(double w) {
    this.startWidth = w;
  }

  /**
   * Sets the starting height.
   *
   * @param h height at start
   */
  public void setStartHeight(double h) {
    this.startHeight = h;
  }

  /**
   * Sets the starting red value.
   *
   * @param r red value at start
   */
  public void setStartRed(double r) {
    this.startRed = r;
  }

  /**
   * Sets the starting green value.
   *
   * @param g green value at start
   */
  public void setStartGreen(double g) {
    this.startGreen = g;
  }

  /**
   * Sets the starting blue value.
   *
   * @param b blue value at start
   */
  public void setStartBlue(double b) {
    this.startBlue = b;
  }

  /**
   * Sets the starting angle.
   *
   * @param a angle
   */
  public void setStartAngle(double a) {
    this.startAngle = a;
  }

  /**
   * Sets the ending angle.
   *
   * @param a angle
   */
  public void setEndAngle(double a) {
    this.endAngle = a;
  }

  /**
   * Sets the ending x.
   *
   * @param x x at end
   */
  public void setEndX(double x) {
    this.endX = x;
  }

  /**
   * Sets the ending y.
   *
   * @param y y at end
   */
  public void setEndY(double y) {
    this.endY = y;
  }

  /**
   * Sets the ending width.
   *
   * @param w width at end
   */
  public void setEndWidth(double w) {
    this.endWidth = w;
  }

  /**
   * Sets the ending height.
   *
   * @param h height at end
   */
  public void setEndHeight(double h) {
    this.endHeight = h;
  }

  /**
   * Sets the ending red value.
   *
   * @param r red value at end
   */
  public void setEndRed(double r) {
    this.endRed = r;
  }

  /**
   * Sets the ending green value.
   *
   * @param g green value at end
   */
  public void setEndGreen(double g) {
    this.endGreen = g;
  }

  /**
   * Sets the ending blue value.
   *
   * @param b blue value at end
   */
  public void setEndBlue(double b) {
    this.endBlue = b;
  }

  /**
   * Sets the ending tick.
   *
   * @param t tick at end
   */
  public void setEndTick(int t) {
    this.endTick = t;
  }



}
