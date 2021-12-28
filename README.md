## Overview
Time-tracker application (called TTrack) lets users record the time they spend on a set of projects, create new projects to track, and see summaries of their time records. For this assignment, I was in charge of all production steps from designing (based on a given user persona and including ideation, sketching and evaluating) to implementation.

![Algorithm schema](./screenshot.png)

## Functionalities
* Add a new project: Choose the 'Add New Task' tab,  fill in the information and click 'Add' button.
* Single Selection: single-click on a ship
* Multiple Selection:
  * Using Control button: Control-click on a ship will add/remove the ship from the current selection set
  * Using Rubberband:
    * Press the left-mouse button on the background and drag: all ships enclosed by the rubber-band outline are selected
    * Same action as above but also hold the Control button: previously selected ships are deselected and while unselected ships are added to the selection set.
* Moving: Drag the ship (if there are more than 1 ship currently in selection set, drag one of them will move every ship in the set)
* Grouping:
  * Grouping: If the selection set has more than 1 ship, pressing the G key would put all the selected ships into one group. Ship group has a bounding box.
  * Ungrouping: pressing the U key would ungroup the selected group
* Cut/Copy/Paste: use Ctrl-X/Ctrl-C/Ctrl-V for cutting/copying/pasting ships/ship groups.
* Rotation: use the slider at the top of the canvas to rotate the selection set.
## Technologies
* Intelliji
* Java
* JavaFX
