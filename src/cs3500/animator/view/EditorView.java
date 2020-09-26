package cs3500.animator.view;

import cs3500.animator.controller.Features;
import hw05.model.Motion;
import hw05.model.shapes.Ellipse;
import hw05.model.shapes.IShape;
import hw05.model.shapes.Rectangle;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.Timer;


/**
 * Class EditorView that enables the user to edit the Visual animation's many features such as
 * starting/ending tick, physical properties, keyframes, and more.
 */
public class EditorView extends JPanel implements View {

  private final JSpinner editTick;
  private final VisualView visualView;
  private final JFrame f;
  private String shapeName;
  private final JButton start;
  private final JButton pause;
  private final JButton resume;
  private final JButton restart;
  private final JRadioButton loopingDisabled;
  private final JRadioButton loopingEnabled;
  private final JSlider slider;
  private final JSlider scrubber;
  private final JList<String> listOfStrings;
  private final JButton create;
  private final JButton delete;
  private final JButton edit;
  private final DefaultListModel<String> listOfShapesModel;
  private final DefaultListModel<String> listOfMotionsModel;
  private final JFrame editFrame;
  private final JPanel editPanel;
  private final JSpinner editX;
  private final JSpinner editY;
  private final JSpinner editWidth;
  private final JSpinner editHeight;
  private final JSpinner editRed;
  private final JSpinner editGreen;
  private final JSpinner editBlue;
  private final JButton editApply;
  private final JLabel editNameLabel;
  private final JTextField editName;
  private final JButton createCreate;
  private final JRadioButton rectangle;
  private final JRadioButton ellipse;
  private String shapeType;
  private JFrame editKeyFrameFrame;
  private final JPanel editKeyFramePanel;
  private final JList<String> keyFrameList;
  private final JButton editKeyFrameSelect;
  private final JButton editKeyFrameCreate;
  private final JButton editKeyFrameDelete;
  private final JButton createKeyFrameButton;
  private final JFrame createShapeFrame;
  private final JSpinner createX;
  private final JSpinner createY;
  private final JSpinner createWidth;
  private final JSpinner createHeight;
  private final JSpinner createRed;
  private final JSpinner createGreen;
  private final JSpinner createBlue;
  private final JSpinner layerSpinner;
  private String motionName;
  private final JButton applyLayer;
  private final JSpinner editLayerSpinner;
  private final JSpinner deleteLayerSpinner;
  private final JButton deleteLayer;
  private JSpinner editAngle;
  private JSpinner createAngle;
  private Timer timer;
  private boolean running;

  /**
   * Creates a new EditorView that has the ability to edit the visual animation.
   *
   * @param view VisualView that the EditorView will edit or modify if necessary
   */
  public EditorView(VisualView view) {
    super();
    Objects.requireNonNull(view);
    this.visualView = view;
    this.f = new JFrame();
    f.setSize(view.getModel().getCanvasSize().width + 800,
        view.getModel().getCanvasSize().height + 300);
    new BoxLayout(this, BoxLayout.PAGE_AXIS);
    f.setLayout(new BorderLayout());

    JScrollPane mainScrollPane = new JScrollPane(this);
    //f.add(mainScrollPane,BorderLayout.CENTER);
    f.add(visualView, BorderLayout.CENTER);
    //adds the panel with all the editing buttons on the bottom
    f.add(this, BorderLayout.SOUTH);

    // Playback JComponents
    JPanel playbackPanel = new JPanel();
    playbackPanel.setBorder(BorderFactory.createTitledBorder("Playback:"));
    playbackPanel.setLayout(new FlowLayout());
    this.add(playbackPanel);

    int maxTick = 0;

    for (IShape s : this.visualView.getModel().getShapes()) {
      for (Motion m : s.getMotions()) {
        if (m.getLastTick() > maxTick) {
          maxTick = m.getLastTick();
        }
      }
    }

    scrubber = new JSlider(0, maxTick, 0);
    playbackPanel.add(scrubber);

    start = new JButton("Start");
    playbackPanel.add(start);

    pause = new JButton("Pause");
    playbackPanel.add(pause);

    resume = new JButton("Resume");
    playbackPanel.add(resume);

    restart = new JButton("Restart");
    playbackPanel.add(restart);

    //Looping JComponents
    JPanel radioPanel = new JPanel();
    this.add(radioPanel);
    radioPanel.setBorder(BorderFactory.createTitledBorder("Loop Settings:"));
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    ButtonGroup rGroup = new ButtonGroup();
    loopingDisabled = new JRadioButton("Looping Disabled");
    loopingDisabled.setSelected(true);
    loopingEnabled = new JRadioButton("Looping Enabled");
    loopingEnabled.setSelected(false);
    rGroup.add(loopingDisabled);
    rGroup.add(loopingEnabled);
    radioPanel.add(loopingDisabled);
    radioPanel.add(loopingEnabled);

    //Animation Speed Components
    JPanel speedPanel = new JPanel();
    this.add(speedPanel);
    speedPanel.setBorder(BorderFactory.createTitledBorder("Animation Ticks/Second Speed:"));
    speedPanel.setLayout(new BoxLayout(speedPanel, BoxLayout.PAGE_AXIS));
    slider = new JSlider(0, 100, 30);
    slider.setMinorTickSpacing(2);
    slider.setMajorTickSpacing(10);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
    speedPanel.add(slider);

    //Keyframe Components
    JPanel selectionListPanel = new JPanel();
    selectionListPanel.setBorder(BorderFactory.createTitledBorder("Edit Shapes:"));
    selectionListPanel.setLayout(new BoxLayout(selectionListPanel, BoxLayout.X_AXIS));
    this.add(selectionListPanel);

    JScrollPane shapeListScroll = new JScrollPane();

    listOfShapesModel = new DefaultListModel<>();
    this.resetListOfShapes();
    listOfStrings = new JList<>(listOfShapesModel);
    listOfStrings.setFixedCellWidth(120);
    //  listOfStrings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    //listOfStrings.setPreferredSize(new Dimension(100, 80));
    shapeListScroll.setViewportView(listOfStrings);
    selectionListPanel.add(shapeListScroll);

    JPanel shapeEditor = new JPanel();
    create = new JButton("Create");
    delete = new JButton("Delete");
    edit = new JButton("Edit");
    deleteLayerSpinner = new JSpinner();
    deleteLayer = new JButton("Delete Layer");
    shapeEditor.add(create);
    shapeEditor.add(delete);
    shapeEditor.add(edit);
    JPanel deleteLayerPanel = new JPanel();
    deleteLayerPanel.setLayout(new FlowLayout());
    deleteLayerPanel.add(deleteLayerSpinner);
    deleteLayerPanel.add(deleteLayer);
    shapeEditor.add(deleteLayerPanel);
    shapeEditor.setPreferredSize(new Dimension(160, 60));
    shapeEditor.setLayout(new GridLayout(4, 1, 0, 5));
    selectionListPanel.add(shapeEditor);

    //edit frame
    editFrame = new JFrame();
    editFrame.setSize(new Dimension(350, 300));
    editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    editPanel = new JPanel();

    editPanel.setLayout(new GridBagLayout());
    GridBagConstraints labelsC = new GridBagConstraints();
    GridBagConstraints textC = new GridBagConstraints();
    editFrame.add(editPanel);

    JLabel editXLabel = new JLabel("X Position");
    labelsC.gridx = 0;
    labelsC.gridy = 0;
    labelsC.insets = new Insets(10, 10, 10, 0);
    editPanel.add(editXLabel, labelsC);
    editX = new JSpinner();
    ((JSpinner.DefaultEditor) editX.getEditor()).getTextField().setColumns(3);
    textC.insets = new Insets(10, 10, 10, 10);
    textC.gridx = 1;
    textC.gridy = 0;
    editPanel.add(editX, textC);

    JLabel editYLabel = new JLabel("Y Position");
    labelsC.gridx = 2;
    labelsC.gridy = 0;
    editPanel.add(editYLabel, labelsC);
    editY = new JSpinner();
    ((JSpinner.DefaultEditor) editY.getEditor()).getTextField().setColumns(3);
    textC.gridx = 3;
    textC.gridy = 0;
    editPanel.add(editY, textC);

    JLabel editWidthLabel = new JLabel("Width");
    labelsC.gridx = 0;
    labelsC.gridy = 1;
    editPanel.add(editWidthLabel, labelsC);
    editWidth = new JSpinner();
    ((JSpinner.DefaultEditor) editWidth.getEditor()).getTextField().setColumns(3);
    textC.gridx = 1;
    textC.gridy = 1;
    editPanel.add(editWidth, textC);

    JLabel editHeightLabel = new JLabel("Height");
    labelsC.gridx = 2;
    labelsC.gridy = 1;
    editPanel.add(editHeightLabel, labelsC);
    editHeight = new JSpinner();
    ((JSpinner.DefaultEditor) editHeight.getEditor()).getTextField().setColumns(3);
    textC.gridx = 3;
    textC.gridy = 1;
    editPanel.add(editHeight, textC);

    JLabel editRedLabel = new JLabel("Red Value");
    labelsC.gridx = 0;
    labelsC.gridy = 2;
    editPanel.add(editRedLabel, labelsC);
    editRed = new JSpinner();
    ((JSpinner.DefaultEditor) editRed.getEditor()).getTextField().setColumns(3);
    textC.gridx = 1;
    textC.gridy = 2;
    editPanel.add(editRed, textC);

    JLabel editGreenLabel = new JLabel("Green Value");
    labelsC.gridx = 2;
    labelsC.gridy = 2;
    editPanel.add(editGreenLabel, labelsC);
    editGreen = new JSpinner();
    ((JSpinner.DefaultEditor) editGreen.getEditor()).getTextField().setColumns(3);
    textC.gridx = 3;
    textC.gridy = 2;
    editPanel.add(editGreen, textC);

    JLabel editBlueLabel = new JLabel("Blue Value");
    labelsC.gridx = 0;
    labelsC.gridy = 3;
    editPanel.add(editBlueLabel, labelsC);
    editBlue = new JSpinner();
    ((JSpinner.DefaultEditor) editBlue.getEditor()).getTextField().setColumns(3);
    textC.gridx = 1;
    textC.gridy = 3;
    editPanel.add(editBlue, textC);

    JLabel editTickLabel = new JLabel("Tick");
    labelsC.gridx = 2;
    labelsC.gridy = 3;
    editPanel.add(editTickLabel, labelsC);
    editTick = new JSpinner();
    ((JSpinner.DefaultEditor) editTick.getEditor()).getTextField().setColumns(3);
    textC.gridx = 3;
    textC.gridy = 3;
    editPanel.add(editTick, textC);

    JLabel editAngleLabel = new JLabel("Angle");
    labelsC.gridx = 1;
    labelsC.gridy = 4;
    editPanel.add(editAngleLabel, labelsC);
    editAngle = new JSpinner();
    ((JSpinner.DefaultEditor) editAngle.getEditor()).getTextField().setColumns(3);
    textC.gridx = 2;
    textC.gridy = 4;
    editPanel.add(editAngle, textC);

    //create shape panel
    editNameLabel = new JLabel("Name");
    labelsC.gridx = 0;
    labelsC.gridy = 4;
    editPanel.add(editNameLabel, labelsC);
    editName = new JTextField("", 6);
    textC.gridx = 1;
    textC.gridy = 4;
    editPanel.add(editName, textC);

    //create shape panel components
    rectangle = new JRadioButton("Rectangle");
    rectangle.setEnabled(true);
    ellipse = new JRadioButton("Ellipse");
    rectangle.setEnabled(true);
    ButtonGroup shapeGroup = new ButtonGroup();
    shapeGroup.add(rectangle);
    shapeGroup.add(ellipse);
    createX = new JSpinner();
    createY = new JSpinner();
    createWidth = new JSpinner();
    createHeight = new JSpinner();
    createRed = new JSpinner();
    createGreen = new JSpinner();
    createBlue = new JSpinner();
    layerSpinner = new JSpinner();

    ((JSpinner.DefaultEditor) createX.getEditor()).getTextField().setColumns(3);
    ((JSpinner.DefaultEditor) createY.getEditor()).getTextField().setColumns(3);
    ((JSpinner.DefaultEditor) createWidth.getEditor()).getTextField().setColumns(3);
    ((JSpinner.DefaultEditor) createHeight.getEditor()).getTextField().setColumns(3);
    ((JSpinner.DefaultEditor) createRed.getEditor()).getTextField().setColumns(3);
    ((JSpinner.DefaultEditor) createGreen.getEditor()).getTextField().setColumns(3);
    ((JSpinner.DefaultEditor) createBlue.getEditor()).getTextField().setColumns(3);
    ((JSpinner.DefaultEditor) layerSpinner.getEditor()).getTextField().setColumns(3);

    editApply = new JButton("Apply");
    createCreate = new JButton("Create");
    editKeyFrameSelect = new JButton("Select");
    editKeyFrameCreate = new JButton("Create");
    editKeyFrameDelete = new JButton("Delete");

    listOfMotionsModel = new DefaultListModel<>();
    this.resetListOfMotions();
    keyFrameList = new JList<>(listOfMotionsModel);

    //edit keyframe Panel
    editKeyFramePanel = new JPanel();
    JScrollPane selectKeyFrameScrollPane = new JScrollPane();
    selectKeyFrameScrollPane.setViewportView(keyFrameList);
    editKeyFramePanel.add(selectKeyFrameScrollPane);
    editKeyFramePanel.add(editKeyFrameCreate);
    editKeyFramePanel.add(editKeyFrameSelect);
    editKeyFramePanel.add(editKeyFrameDelete);
    editKeyFramePanel.add(new JLabel("Layer"));
    editLayerSpinner = new JSpinner();
    editKeyFramePanel.add(editLayerSpinner);
    applyLayer = new JButton("Apply");
    editKeyFramePanel.add(applyLayer);

    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 1;
    c.gridy = 5;
    c.gridwidth = 2;
    this.editPanel.add(this.createCreate, c);
    this.editPanel.add(this.createCreate);
    createKeyFrameButton = new JButton("Create KeyFrame");
    this.editPanel.add(this.createKeyFrameButton, c);

    createShapeFrame = new JFrame("Create Shape");
    createShapeFrame.setSize(new Dimension(200, 300));
    JPanel createShapePanel = new JPanel();
    createShapePanel.setLayout(new FlowLayout());
    createShapeFrame.add(createShapePanel);
    createShapePanel.add(editNameLabel);
    createShapePanel.add(editName);
    createShapePanel.add(rectangle);
    createShapePanel.add(ellipse);

    JPanel settings = new JPanel();
    settings.setLayout(new GridLayout(9, 2));

    settings.add(new JLabel("X"));
    settings.add(createX);
    settings.add(new JLabel("Y"));
    settings.add(createY);
    settings.add(new JLabel("Width"));
    settings.add(createWidth);
    settings.add(new JLabel("Height"));
    settings.add(createHeight);
    settings.add(new JLabel("Red"));
    settings.add(createRed);
    settings.add(new JLabel("Green"));
    settings.add(createGreen);
    settings.add(new JLabel("Blue"));
    settings.add(createBlue);
    settings.add(new JLabel("Layer"));
    settings.add(layerSpinner);
    createShapePanel.add(settings);
    createShapePanel.add(createCreate);
    settings.add(new JLabel("Angle"));
    createAngle = new JSpinner();
    settings.add(createAngle);

    timer = new javax.swing.Timer(1000 / visualView.getTicksPerSecond(), this::timerAction);
    running = false;
    this.run();

  }

  private void timerAction(ActionEvent actionEvent) {
    if (running) {
      this.scrubber.setValue(this.visualView.getTick());
      this.startTimer();
    }
  }


  @Override
  public void run() {
    timer.start();
    f.setVisible(true);
  }


  /**
   * Starts the timer so that the Visual Animation will be able to run again.
   */
  public void startTimer() {
    running = true;
    visualView.startTimer();
  }

  /**
   * Disables the start button.
   */
  public void disableStart() {
    start.setEnabled(false);
  }

  /**
   * Pauses the timer so that the animation stops.
   */
  public void pauseTimer() {
    running = false;
    visualView.pauseTimer();
  }

  /**
   * Restarts the tick of the animation to 0. Any changes made to the animation will be left alone.
   */
  public void restart() {
    visualView.resetTimer();
    this.startTimer();
  }

  /**
   * Toggles the option of having the animation loop after it ends.
   */
  public void toggleLooping() {
    visualView.toggleLooping();
  }

  /**
   * Sets the speed of the animation given the speed desired.
   *
   * @param ticksPerSecond int speed that the animation should be ran at
   */
  public void setSpeed(int ticksPerSecond) {
    visualView.setSpeed(ticksPerSecond);
  }

  /**
   * Sets the shapeName to the selected shape.
   */
  public void selectedShape() {
    this.shapeName = this.listOfStrings.getSelectedValue();
  }

  /**
   * Sets the motionName to the selected motion.
   */
  public void selectedMotion() {
    this.motionName = this.keyFrameList.getSelectedValue();
  }

  /**
   * Deletes the given shape.
   *
   * @param shape shape to be deleted
   */
  public void deleteShape(String shape) {

    DefaultListModel model = (DefaultListModel) listOfStrings.getModel();
    int index = listOfStrings.getSelectedIndex();
    if (index != -1) {
      model.remove(index);
      this.visualView.deleteShape(shape);
    }

  }

  /**
   * Opens the keyframe selection window.
   */
  public void selectKeyframe() {
    if (shapeName != null) {
      createKeyFrameButton.setVisible(false);
      editKeyFrameCreate.setVisible(true);
      editKeyFrameFrame = new JFrame("Select KeyFrame");
      editKeyFrameFrame.setSize(new Dimension(275, 250));
      editKeyFrameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      editKeyFrameFrame.add(editKeyFramePanel);
      editKeyFrameFrame.setLocation(edit.getLocationOnScreen().x, edit.getLocationOnScreen().y);
      this.resetListOfMotions();
      editKeyFrameFrame.setVisible(true);
    }
  }

  /**
   * Opens the key frame creator.
   */
  public void openKeyFrameCreator() {
    this.selectedShape();
    layerSpinner.setValue(visualView.getShape(shapeName).getLayer());
    layerSpinner.setVisible(true);
    editFrame.setVisible(true);
    this.createCreate.setVisible(false);
    this.editNameLabel.setVisible(false);
    this.editName.setVisible(false);
    this.createKeyFrameButton.setVisible(true);
  }

  /**
   * Creates a key frame using the current shape.
   */
  public void createKeyFrame() {

    IShape shape = this.visualView.getShape(shapeName);
    shape.addKeyFrame((Integer) this.editTick.getValue(),
        (Integer) this.editX.getValue(),
        (Integer) this.editY.getValue(),
        (Integer) this.editWidth.getValue(),
        (Integer) this.editHeight.getValue(),
        (Integer) this.editRed.getValue(),
        (Integer) this.editGreen.getValue(),
        (Integer) this.editBlue.getValue(),
        (Integer) this.editAngle.getValue());
    this.editFrame.setVisible(false);
    createKeyFrameButton.setVisible(false);
    this.resetListOfMotions();
  }

  /**
   * Deletes the selected keyframe.
   */
  public void deleteKeyFrame() {

    IShape shape = this.visualView.getShape(shapeName);

    shape.removeKeyFrame(this.keyFrameList.getSelectedValue());

    this.resetListOfMotions();
  }

  /**
   * Edits the selected keyframe.
   *
   * @param shapeName name of shape being edited
   */
  public void editKeyFrame(String shapeName) {
    if (keyFrameList.getSelectedValue() != null) {
      this.editKeyFrameFrame.setVisible(false);
      this.editApply.setVisible(true);
      this.createCreate.setVisible(false);
      editFrame.setLocation(edit.getLocationOnScreen().x, edit.getLocationOnScreen().y);

      IShape shape = this.visualView.getShape(shapeName);

      int x = 0;
      int y = 0;
      int width = 0;
      int height = 0;
      int red = 0;
      int green = 0;
      int blue = 0;
      int tick = 0;

      for (Motion m : shape.getMotions()) {
        if (m.startKeyFrameDesc().equals(this.keyFrameList.getSelectedValue())) {
          x = (int) m.getStartX();
          y = (int) m.getStartY();
          width = (int) m.getStartWidth();
          height = (int) m.getStartHeight();
          red = (int) m.getStartRed();
          green = (int) m.getStartGreen();
          blue = (int) m.getStartBlue();
          tick = m.getStartTick();
        } else if (m.endKeyFrameDesc().equals(this.keyFrameList.getSelectedValue())) {
          x = (int) m.getEndX();
          y = (int) m.getEndY();
          width = (int) m.getEndWidth();
          height = (int) m.getEndHeight();
          red = (int) m.getEndRed();
          green = (int) m.getEndGreen();
          blue = (int) m.getEndBlue();
          tick = m.getLastTick();
        }
      }

      GridBagConstraints c = new GridBagConstraints();
      c.gridx = 1;
      c.gridy = 5;
      c.gridwidth = 2;
      this.editPanel.add(editApply, c);

      ((JSpinner.DefaultEditor) this.editX.getEditor())
          .getTextField().setText((Integer.toString(x)));
      ((JSpinner.DefaultEditor) this.editY.getEditor())
          .getTextField().setText((Integer.toString(y)));
      ((JSpinner.DefaultEditor) this.editWidth.getEditor())
          .getTextField().setText((Integer.toString(width)));
      ((JSpinner.DefaultEditor) this.editHeight.getEditor())
          .getTextField().setText((Integer.toString(height)));
      ((JSpinner.DefaultEditor) this.editRed.getEditor())
          .getTextField().setText((Integer.toString((red))));
      ((JSpinner.DefaultEditor) this.editGreen.getEditor())
          .getTextField().setText((Integer.toString(green)));
      ((JSpinner.DefaultEditor) this.editBlue.getEditor())
          .getTextField().setText((Integer.toString(blue)));
      ((JSpinner.DefaultEditor) this.editTick.getEditor())
          .getTextField().setText((Integer.toString(tick)));
      this.editName.setText(shape.getName());
      editFrame.setVisible(true);
    }

  }

  /**
   * Opens the shape creator.
   */
  public void createShape() {
    editFrame.setVisible(false);

    this.createCreate.setVisible(true);
    this.editNameLabel.setVisible(true);
    this.editName.setVisible(true);
    this.createKeyFrameButton.setVisible(false);
    this.editName.setText("");
    this.createShapeFrame.setVisible(true);

  }

  /**
   * Applies the edits on the shape.
   */
  public void applyEdits() {
    this.visualView.applyEdits((Integer) this.editX.getValue(), (Integer) this.editY.getValue(),
        (Integer) this.editWidth.getValue(), (Integer) this.editHeight.getValue(),
        (Integer) this.editRed.getValue(), (Integer) this.editGreen.getValue(),
        (Integer) this.editBlue.getValue(), this.shapeName, this.motionName,
        (Integer) this.layerSpinner.getValue());
    this.editFrame.setVisible(false);
    this.editApply.setVisible(false);
  }

  /**
   * Creates a new shape based on the given arguments.
   */
  public void applyCreation() {
    IShape shape = null;
    int red = Integer.parseInt(this.createRed.getValue().toString());
    int green = Integer.parseInt(this.createGreen.getValue().toString());
    int blue = Integer.parseInt(this.createBlue.getValue().toString());
    int x = Integer.parseInt(this.createX.getValue().toString());
    int y = Integer.parseInt(this.createY.getValue().toString());
    int w = Integer.parseInt(this.createWidth.getValue().toString());
    int h = Integer.parseInt(this.createHeight.getValue().toString());
    int layer = Integer.parseInt(this.layerSpinner.getValue().toString());
    int angle = Integer.parseInt(this.createAngle.getValue().toString());
    if (shapeType.equals("Rect")) {
      shape = new Rectangle(new ArrayList<Motion>(),
          red, green, blue, x, y, w, h, angle, layer, this.editName.getText());
      shape.addKeyFrame(0, x, y, w, h, red, green, blue, angle);
    } else if (shapeType.equals("Ell")) {
      shape = new Ellipse(new ArrayList<Motion>(),
          red, green, blue, x, y, w, h, angle, layer, this.editName.getText());
      shape.addKeyFrame(0, x, y, w, h, red, green, blue, angle);
    }

    this.visualView.getModel().addShape(shape);
    this.visualView.addToCopy(shape);
    this.resetListOfShapes();
    this.createShapeFrame.setVisible(false);
    this.createCreate.setVisible(false);
  }


  @Override
  public void addFeatures(Features features) {
    start.addActionListener(evt -> features.start());
    pause.addActionListener(evt -> features.pause());
    resume.addActionListener(evt -> features.resume());
    restart.addActionListener(evt -> features.restart());
    loopingDisabled.addActionListener(evt -> features.looping());
    loopingEnabled.addActionListener(evt -> features.looping());
    listOfStrings.addListSelectionListener(evt -> features.selectedShape());
    delete.addActionListener(evt -> features.deleteShape(listOfStrings.getSelectedValue()));
    edit.addActionListener(evt -> features.selectKeyframe());
    editKeyFrameSelect
        .addActionListener(evt -> features.editKeyFrame(listOfStrings.getSelectedValue()));
    editKeyFrameCreate.addActionListener(evt -> features.openKeyFrameCreator());
    editKeyFrameDelete.addActionListener(evt -> features.deleteKeyFrame());
    editApply.addActionListener(evt -> features.applyEdits());
    create.addActionListener(evt -> features.createShape());
    rectangle.addActionListener(evt -> shapeType = "Rect");
    ellipse.addActionListener(evt -> shapeType = "Ell");
    createCreate.addActionListener(evt -> features.applyCreation());
    createKeyFrameButton.addActionListener(evt -> features.createKeyFrame());
    keyFrameList.addListSelectionListener(evt -> features.selectedMotion());
    applyLayer.addActionListener(evt -> visualView.getShape(shapeName)
        .setLayer((Integer) editLayerSpinner.getValue()));
    deleteLayer.addActionListener(evt -> features.deleteLayer());

    scrubber.addChangeListener(ce -> {
      int tick = scrubber.getValue();
      this.pauseTimer();
      features.setCurrentTick(tick);
    });
    slider.addChangeListener(ce -> {
      int tps = slider.getValue();
      features.setSpeed(tps);
    });

  }

  /**
   * Resets the list of shapes.
   */
  private void resetListOfShapes() {
    for (IShape s : visualView.getModel().getShapes()) {
      if (!listOfShapesModel.contains(s.getName())) {
        listOfShapesModel.addElement(s.getName());
      }
    }
  }

  /**
   * Resets the list of keyframes.
   */
  private void resetListOfMotions() {

    IShape shape;

    listOfMotionsModel.clear();
    if (shapeName != null) {
      shape = this.visualView.getShape(shapeName);
      for (Motion s : shape.getMotions()) {
        listOfMotionsModel.addElement(s.endKeyFrameDesc());
      }
    }
  }


  /**
   * Sets the current tick of the animation and shows that tick state.
   * @param tick int tick that is the current tick
   */
  public void setCurrentTick(int tick) {
    this.visualView.showTick(tick);
  }

  /**
   * Deletes the layer of the chosen value.
   */
  public void deleteLayer() {
    int layerToDelete = (int) deleteLayerSpinner.getValue();
    this.visualView.deleteLayer(layerToDelete);
    listOfShapesModel.clear();
    for (IShape s : visualView.getModel().getShapes()) {
      if (s.getLayer() != layerToDelete) {
        listOfShapesModel.addElement(s.getName());
      }
    }
  }

}