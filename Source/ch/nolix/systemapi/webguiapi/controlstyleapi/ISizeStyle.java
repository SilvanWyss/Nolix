//package declaration
package ch.nolix.systemapi.webguiapi.controlstyleapi;

import ch.nolix.systemapi.elementapi.relativevalueapi.IAbsoluteOrRelativeInt;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//interface
public interface ISizeStyle<S extends ISizeStyle<S>> {

  //method declaration
  boolean definesHeightForState(ControlState state);

  //method declaration
  boolean definesWidthForState(ControlState state);

  //method declaration
  IAbsoluteOrRelativeInt getHeightForState(ControlState state);

  //method declaration
  IAbsoluteOrRelativeInt getWidthForState(ControlState state);

  //method declaration
  void removeCustomHeights();

  //method declaration
  void removeCustomWidths();

  //method declaration
  S setHeightForState(ControlState state, int height);

  //method declaration
  S setHeightInPercentOfViewAreaForState(ControlState state, double heightInPercentOfViewAreaHeight);

  //method declaration
  S setWidthForState(ControlState state, int width);

  //method declaration
  S setWidthInPercentOfViewAreaWidthForState(ControlState state, double widthInPercentOfViewAreaWidth);
}
