package hw05.model;

import java.awt.Dimension;
import java.util.ArrayList;

import cs3500.animator.util.AnimationBuilder;
import hw05.model.shapes.Ellipse;
import hw05.model.shapes.IShape;
import hw05.model.shapes.Rectangle;

/**
 * An implementation of a {@link AnimatorModel} which allows for the drawing
 * of shapes.
 */
public final class AnimatorModelImpl implements AnimatorModel {

  private ArrayList<IShape> shapes;
  private int tick;
  private final int canvasX;
  private final int canvasY;
  private final int canvasWidth;
  private final int canvasHeight;

  /**
   * Constructs an {@link AnimatorModelImpl} with no shapes.
   */
  public AnimatorModelImpl() {
    this(new ArrayList<>(),
            0, 0, 500, 500);
  }

  /**
   * Constructs an {@link AnimatorModelImpl} with the given shapes.
   *
   * @param shapes the shapes for the model
   */
  public AnimatorModelImpl(ArrayList<IShape> shapes) {
    this(shapes,
            0, 0, 500, 500);
  }

  /**
   * Constructs an {@link AnimatorModelImpl} with the given {@link IShape}s.
   *
   * @param shapes the list of shapes
   * @throws IllegalArgumentException if the list is null
   */
  public AnimatorModelImpl(ArrayList<IShape> shapes, int canvasX, int canvasY,
                           int canvasWidth, int canvasHeight) throws IllegalArgumentException {
    if (shapes == null) {
      throw new IllegalArgumentException("Shapes cannot be null");
    }
    this.canvasX = canvasX;
    this.canvasY = canvasY;
    this.canvasWidth = canvasWidth;
    this.canvasHeight = canvasHeight;
    this.shapes = shapes;
    tick = 0;
  }

  @Override
  public Dimension getCanvasSize() {
    return new Dimension(this.canvasWidth, this.canvasHeight);
  }

  @Override
  public int getXOffset() {
    return canvasX;
  }

  @Override
  public int getYOffset() {
    return canvasY;
  }

  @Override
  public int hashCode() {
    return this.getDescription().hashCode();
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof AnimatorModel)) {
      return false;
    }

    return ((AnimatorModel) that).getDescription().equals(this.getDescription());
  }

  @Override
  public String toString() {
    return this.getDescription();
  }

  @Override
  public void addShape(IShape s) {
    this.shapes.add(s);
  }

  @Override
  public void animate() {
    for (IShape s : shapes) {
      s.update(this.tick);
    }
    tick++;
  }

  @Override
  public String getDescription() {

    StringBuilder display = new StringBuilder();

    display.append(String.format("canvas %d %d %d %d\n", this.canvasX, this.canvasY,
            this.canvasWidth, this.canvasHeight));

    for (IShape s : shapes) {
      display.append(s.toString()).append("\n");
      display.append(s.motionDescription());
    }

    return display.toString();
  }

  @Override
  public ArrayList<IShape> getShapes() {
    return this.shapes;
  }

  /**
   * A builder for an AnimatorModelImpl.
   */
  public static final class Builder implements
          cs3500.animator.util.AnimationBuilder<AnimatorModel> {

    ArrayList<IShape> builderShapes;

    int canvasX;
    int canvasY;
    int canvasWidth;
    int canvasHeight;

    /**
     * Constructs a builder and initializes to default values.
     */
    public Builder() {
      this.builderShapes = new ArrayList<>();
      this.canvasX = 0;
      this.canvasY = 0;
      this.canvasWidth = 500;
      this.canvasHeight = 500;
    }

    @Override
    public AnimatorModel build() {
      return new AnimatorModelImpl(builderShapes, this.canvasX, this.canvasY,
              this.canvasWidth, this.canvasHeight);
    }

    @Override
    public AnimationBuilder<AnimatorModel> setBounds(int x, int y, int width, int height) {
      this.canvasX = x;
      this.canvasY = y;
      this.canvasWidth = width;
      this.canvasHeight = height;
      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> declareShape(String name, String type, int layer) {

      switch (type) {
        case "rectangle":
          this.builderShapes.add(new Rectangle(name, layer));
          break;
        case "ellipse":
          this.builderShapes.add(new Ellipse(name, layer));
          break;
        default:
          break;
      }


      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> addMotion(String name, int t1, int x1,
                                                     int y1, int w1, int h1, int r1,
                                                     int g1, int b1, int a1, int t2, int x2,
                                                     int y2, int w2, int h2, int r2,
                                                     int g2, int b2, int a2) {

      for (IShape s : this.builderShapes) {
        if (s.getName().equals(name)) {

          s.addMotion(new Motion(t1, x1, y1, w1, h1, r1, g1, b1, a1, t2, x2, y2,
                  w2, h2, r2, g2, b2, a2));

          if (t1 == t2) {
            s.setX(x1);
            s.setY(y1);
            s.setWidth(w1);
            s.setHeight(h1);
            s.setRed(r1);
            s.setGreen(g1);
            s.setBlue(b1);
          }

        }
      }

      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> addKeyframe(String name, int t, int x, int y, int w,
                                                       int h, int r, int g, int b, int a) {
      this.addMotion(name, t, x, y, w, h, r, g, b, a, t, x, y, w, h, r, g, b, a);
      return this;
    }
  }


}
