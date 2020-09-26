1 Scrubber
   .1  Added scrubber in the EditorView
   .2  Added the method showCurrentTick to the scrubber and the features interface implemented in the
       Controller
   .3  Added the method showTick to the VisualView that shows the state of the animation on that
       tick
   .4  Pauses the timer when the scrubber is interacted in the EditorView

2 Rotation
   .1  Modified the AnimationReader to have more field names so it could take in rotation values
   .2  Modified the file so the AnimationReader works as normal if not rotation values are given,
       and also takes in rotation values to the animation if given
   .3  test.txt (configuration 2.3) is given the output of the rotation animation
   .4  toh-3.txt (configuration 2.4) is given the output of the rotation and multiple changes at
       once
   .5  test.svg is the output of test.txt which is the svg version of the rotation code

3 Layers
   .1  The input file can have initial angles and ending angles in AnimationReader for the
       readShape and declareShape in AnimatorModelImpl's builder
   .2  In AnimationReader, the readShape can take in a layer for the shape
   .3  If no layers number is found it defaults to 0 in readShape
   .4  3.5.3 configuration has correct rendering with layers
   .5  3.5.1 is the buildings.txt
       3.5.2 is the two non-empty layers animation in layers2.txt
       3.5.3 is the three non-empty layers animation in layers.txt
   .6  EditorView added buttons for editing the layer of an shape and a delete button for deleting
       all the shapes of a given layer as well as creating a shape with a given layer


