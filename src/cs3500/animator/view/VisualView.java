package cs3500.animator.view;

import cs3500.animator.controller.Features;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import hw05.model.AnimatorModel;
import hw05.model.Motion;
import hw05.model.shapes.IShape;

/**
 * VisualView class that is able to show the animation in the model on a GUI platform with moving
 * graphics and shapes of color.
 */
public class VisualView extends JPanel implements View {

  private AnimatorModel model;
  private final JFrame f;
  private final JScrollPane scroll;
  private int tick;
  private final Timer timer;
  private ArrayList<IShape> copy;
  private int ticksPerSecond;
  private boolean looping;

  /**
   * Creates a new VisualView which shows the animation performed on a JPanel with moving graphics.
   *
   * @param model AnimatorModel that provides the VisualView with what to draw.
   */
  public VisualView(AnimatorModel model, int ticksPerSecond) {
    super();
    Objects.requireNonNull(model);
    this.f = new JFrame();
    this.tick = 0;
    this.ticksPerSecond = ticksPerSecond;
    this.model = model;
    copy = new ArrayList<>();
    this.setCopy();
    this.setPreferredSize(model.getCanvasSize());
    this.scroll = new JScrollPane(this);
    f.setLayout(new BorderLayout());
    f.setSize(model.getCanvasSize());
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    timer = new Timer(1000 / ticksPerSecond, this::timerAction);
    this.looping = false;
  }

  /**
   * Executes every 1000 / ticksPerSecond and initiates the next tick's picture.
   *
   * @param e The event of the timer triggering
   */
  private void timerAction(ActionEvent e) {
    this.refresh();
  }

  /**
   * Allows the animation to be run or viewed by allowing the animation to be visible.
   */
  public void run() {
    f.add(scroll, BorderLayout.CENTER);
    f.setVisible(true);
    timer.start();
  }

  @Override
  public void addFeatures(Features features) {
    throw new UnsupportedOperationException("This view does not support this feature");
  }

  /**
   * Paints the given shape on the screen.
   *
   * @param s the current shape
   * @param g the graphics to render onto
   */
  private void paintInTick(IShape s, Graphics2D g) {
    for (Motion m : s.getMotions()) {

      if (m.inTick(this.tick)) {
        AffineTransform old = g.getTransform();
        g.rotate(Math.toRadians(s.getAngle()), s.getXPos() + s.getWidth() / 2, s.getYPos()
            + s.getHeight() / 2);
        g.setColor(new Color((int) s.getRed(), (int) s.getGreen(), (int) s.getBlue()));
        g.draw(s.getShape());
        g.fill(s.getShape());
        g.setTransform(old);

      }
    }
  }

  /**
   * Paints the shapes of the animation onto the graphics which is displayed on the JPanel.
   *
   * @param g Graphics that the paint method updates with the various shapes and animations
   */
  public void paint(Graphics g) {

    super.paint(g);

    Graphics2D g2 = (Graphics2D) g;

    for (int currentLayer = 0; currentLayer <= model.getShapes().size(); currentLayer++) {
      for (int a = 0; a < model.getShapes().size(); a++) {
        IShape s = model.getShapes().get(a);
        if (s.getLayer() == currentLayer) {
          this.paintInTick(s, g2);
          s.update(this.tick);
        }
      }
    }



  }

  /**
   * Refreshes the screen.
   */
  private void refresh() {

    int max = 0;

    for (IShape s : model.getShapes()) {
      if (s.getMotions().size() != 0) {
        int temp = s.getMotions().get(s.getMotions().size() - 1).getLastTick();

        if (temp > max) {
          max = temp;
        }
      }
    }

    if (this.tick >= max) {
      if (looping) {
        this.resetTimer();
      } else {
        timer.stop();
      }
    }

    this.repaint();
    tick++;
  }

  /**
   * Starts the timer.
   */
  public void startTimer() {
    this.timer.start();
  }

  /**
   * Pauses the timer.
   */
  public void pauseTimer() {
    this.timer.stop();
  }

  /**
   * Resets the timer.
   */
  public void resetTimer() {
    tick = 0;

    for (IShape s : copy) {
      for (Motion m : s.getMotions()) {
        if (m.getStartTick() == m.getLastTick()) {
          s.setX((int) m.getStartX());
          s.setY((int) m.getStartY());
          s.setWidth((int) m.getStartWidth());
          s.setHeight((int) m.getStartHeight());
          s.setRed(m.getStartRed());
          s.setGreen(m.getStartGreen());
          s.setBlue(m.getStartBlue());
          s.setAngle(m.getStartAngle());
        }
      }
    }
  }

  /**
   * Sets the boolean looping to true or false based on the preexisting value so loopin is toggled.
   */
  public void toggleLooping() {
    this.looping = !this.looping;
  }

  /**
   * Sets the speed of the animation based on the given int.
   *
   * @param ticksPerSecond int speed of the animation it should be ran on
   */
  public void setSpeed(int ticksPerSecond) {
    this.ticksPerSecond = ticksPerSecond;
    if (ticksPerSecond == 0) {
      this.pauseTimer();
    } else {
      this.startTimer();
      this.timer.setDelay(1000 / this.ticksPerSecond);
    }

  }

  /**
   * Returns the AnimatorModel that is associated with this VisualView.
   *
   * @return AnimatorModel that this VisualView is using
   */
  public AnimatorModel getModel() {
    return this.model;
  }

  /**
   * Deletes the shape from the model.
   *
   * @param shape String name of the shape that needs to be deleted
   */
  public void deleteShape(String shape) {
    int index = -1;

    for (int i = 0; i < this.model.getShapes().size(); i++) {
      IShape s = this.model.getShapes().get(i);
      if (s.getName().equals(shape)) {
        index = i;
      }
    }
    if (index != -1) {
      this.model.getShapes().remove(index);
    }
  }

  /**
   * Returns the shape given the name of the shape that is in the model.
   *
   * @param shape String name of the wanted shape
   * @return IShape shape that is wanted
   */
  public IShape getShape(String shape) {
    for (IShape s : this.model.getShapes()) {
      if (s.getName().equals(shape)) {
        return s;
      }
    }
    throw new IllegalArgumentException("Shape not found");

  }

  /**
   * Adds the shapes of the current model to the copy of the model.
   */
  public void setCopy() {
    copy.addAll(this.model.getShapes());
  }

  /**
   * Adds the shape to the copy.
   * @param s IShape to be added to copy
   */
  public void addToCopy(IShape s) {
    copy.add(s);
  }

  /**
   * Applies the edits given to the existing keyframes of the animation.
   *
   * @param x      int new X
   * @param y      int new Y
   * @param width  int new width
   * @param height int new height
   * @param red    int new red color
   * @param green  int new green color
   * @param blue   int new blue color
   * @param shape  int new shape color
   * @param motion int new motion
   */
  public void applyEdits(int x, int y, int width, int height, int red, int green, int blue,
                         String shape, String motion, int layer) {
    IShape s = this.getShape(shape);
    for (int i = 0; i < s.getMotions().size(); i++) {
      s.setLayer(layer);
      if (s.getMotions().get(i).startKeyFrameDesc().equals(motion)) {
        s.getMotions().get(i).setStartX(x);
        s.getMotions().get(i).setStartY(y);
        s.getMotions().get(i).setStartWidth(width);
        s.getMotions().get(i).setStartHeight(height);
        s.getMotions().get(i).setStartRed(red);
        s.getMotions().get(i).setStartGreen(green);
        s.getMotions().get(i).setStartBlue(blue);
      }
      if (s.getMotions().get(i).endKeyFrameDesc().equals(motion)) {
        s.getMotions().get(i).setEndX(x);
        s.getMotions().get(i).setEndY(y);
        s.getMotions().get(i).setEndWidth(width);
        s.getMotions().get(i).setEndHeight(height);
        s.getMotions().get(i).setEndRed(red);
        s.getMotions().get(i).setEndGreen(green);
        s.getMotions().get(i).setEndBlue(blue);
      }
    }
  }


  /**
   * SHows the state of the given tick.
   * @param t int tick that shows the current state
   */
  public void showTick(int t) {
    this.resetTimer();

    for (; this.tick < t; this.tick++) {
      for (IShape s : this.getModel().getShapes()) {
        s.update(this.tick);
      }
    }

    this.repaint();

  }


  /**
   * Deletes all shapes with the given layer.
   * @param layer int layer that will be deleted
   */
  public void deleteLayer(int layer) {

    List<IShape> toDelete = new ArrayList<>();

    for (IShape s : this.getModel().getShapes()) {
      if (s.getLayer() == layer) {
        toDelete.add(s);
      }
    }

    for (IShape s : toDelete) {
      this.model.getShapes().remove(s);
    }

  }

  public int getTick() {
    return this.tick;
  }

  public int getTicksPerSecond() {
    return this.ticksPerSecond;
  }
}
