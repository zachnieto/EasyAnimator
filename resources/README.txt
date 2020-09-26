HW06

- Removed java.awt.Color from all classes and added values for red, green, and blue
    values instead.
- Added startRed, startGreen, startBlue, endRed, endGreen, and endBlue to the Motion class.
- Added getters for all fields in the Motion class, along with getDuration.
- Added getRed, getGreen, and getBlue in the IShape interface.
- Added private final int canvasX, private final int canvasY,
    private final int canvasWidth, private final int canvasHeight to the AnimatorModelImpl.
- Added fields for canvasX, canvasY, canvasWidth, and canvasHeight to the
    AnimatorModelImpl constructor.
- Added method getCanvasSize() to the AnimatorModel interface.
- Added a builder in the AnimatorModelImpl.
- Added setRed, setGreen, and setBlue, getSVGName, getXAttribute
    and getYAttribute to the IShape interface.




HW07

- Added startTimer, PauseTimer, resetTimer, toggleLooping, setSpeed, getModel, deleteShape, getShape , setCopy, addFeatures, applyEdits in the VisualView Class
- Added boolean looping, ArrayList<IShape> copy in VisualView
- Added interface Features 
- Added UnssuportedOperationException in SVGView, TextView, VisualView
- Added addKeyFrame, removeKeyFrame, removeMotion  in IShape
- Added startKeyFrameDesc, endKeyFrameDesc , and setters for every field in Motion

The EditorView had a composite call to the VisualView and built editing features ontop of this with gui listeners that modified the model.