package cs3500.animator;

import cs3500.animator.controller.Controller;
import cs3500.animator.view.EditorView;

import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

import cs3500.animator.util.AnimationReader;
import hw05.model.AnimatorModel;
import hw05.model.AnimatorModelImpl;
import cs3500.animator.view.View;
import cs3500.animator.view.VisualView;

/**
 * Excellence class that contains the main method to run the different types of Views.
 */
public class Excellence {

  /**
   * Main method that runs the views based on the model and information it is provided with.
   *
   * @param args String[] that tells the main which view to run
   */
  public static void main(String[] args) {

    String fileName = "";
    String viewName = "";
    String outPutLoc = "System.out";
    int ticksPerSec = 1;

    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-in":
          try {
            fileName = args[i + 1];
          } catch (IndexOutOfBoundsException e) {
            showError("Missing input filename");
          }
          i++;
          break;
        case "-view":
          try {
            viewName = args[i + 1];
          } catch (IndexOutOfBoundsException e) {
            showError("Missing view type");
          }
          i++;
          break;
        case "-out":
          try {
            outPutLoc = args[i + 1];
          } catch (IndexOutOfBoundsException e) {
            showError("Missing output location");
          }
          i++;
          break;
        case "-speed":
          try {
            ticksPerSec = Integer.parseInt(args[i + 1]);
          } catch (IndexOutOfBoundsException e) {
            showError("Missing ticks per second");
          }
          i++;
          break;
        default:
          showError("Command not recognized");

          break;
      }
    }

    if (viewName.equals("") || fileName.equals("")) {
      showError("Must provide view type and file name");
    } else if (!(viewName.equals("text") || viewName.equals("visual") || viewName.equals("svg")
        || viewName.equals("edit"))) {
      showError("View type not recognized");
    }

    Readable r = null;

    try {
      r = new FileReader(fileName);
    } catch (FileNotFoundException f) {
      showError("Input file not found");
      f.printStackTrace();
    }

    AnimatorModel m = AnimationReader.parseFile(r, new AnimatorModelImpl.Builder());

    View view;
    BufferedWriter bw = null;

    try {
      bw = new BufferedWriter(new FileWriter(outPutLoc, true));
    } catch (IOException e) {
      e.printStackTrace();
    }

    // clear the file
    try {
      FileWriter fw = new FileWriter(outPutLoc);
      fw.write("");
      fw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    switch (viewName) {
      case "text":
        if (outPutLoc.equals("System.out")) {
          view = new TextView(m, System.out);
        } else {
          view = new TextView(m, bw);
        }
        break;
      case "visual":
        view = new VisualView(m, ticksPerSec);
        break;
      case "svg":
        if (outPutLoc.equals("System.out")) {
          view = new SVGView(m, System.out, ticksPerSec);
        } else {
          view = new SVGView(m, bw, ticksPerSec);
        }
        break;
      case "edit":
        view = new EditorView(new VisualView(m,ticksPerSec));
        Controller controller = new Controller();
        controller.setView((EditorView) view);
        break;
      default:
        view = new TextView(m, System.out);
        showError("Provided view type is invalid");
        break;
    }

    view.run();

    if (bw != null) {
      try {
        bw.flush();
        bw.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  private static void showError(String msg) {
    JFrame frame = new JFrame();
    JOptionPane.showMessageDialog(frame,
            msg,
            "Command Line Input Error",
            JOptionPane.ERROR_MESSAGE);
    System.exit(0);
  }


}
