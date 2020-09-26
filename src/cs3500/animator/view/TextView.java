package cs3500.animator.view;

import cs3500.animator.controller.Features;
import java.io.IOException;
import java.util.Objects;

import hw05.model.AnimatorModel;

/**
 * TextView model that outputs the model's shapes and the corresponding animations in a text format.
 */
public class TextView implements View {

  AnimatorModel model;
  Appendable out;

  /**
   * Creates a new TextView class of type View using the AnimatorModel and a output stream.
   *
   * @param model The animation model that the class takes in
   * @param out  Appendable that contains the output of the animation in text format
   */
  public TextView(AnimatorModel model, Appendable out) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(out);
    this.model = model;
    this.out = out;
  }


  @Override
  public void run() {
    try {
      out.append(model.getDescription());
    } catch (IOException e) {
      throw new IllegalStateException("append failed");
    }
  }

  @Override
  public void addFeatures(Features features) {
    throw new UnsupportedOperationException("This view does not support this feature");
  }

}
