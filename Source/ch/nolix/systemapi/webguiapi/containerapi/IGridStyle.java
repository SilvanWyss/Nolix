//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

//own imports
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.structureproperty.GridType;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//interface
public interface IGridStyle extends IControlStyle<IGridStyle> {

  //method declaration
  int getChildControlMarginWhenHasState(ControlState state);

  //method declaration
  IColor getGridColorWhenHasState(ControlState state);

  //method declaration
  int getGridThicknessWhenHasState(ControlState state);

  //method declaration
  GridType getGridTypeWhenHasState(ControlState state);

  //method declaration
  void removeCustomChildControlMargins();

  //method declaration
  void removeCustomGridColors();

  //method declaration
  void removeCustomGridThicknesses();

  //method declaration
  void removeCustomGridTypes();

  //method declaration
  IGridStyle setChildControlMarginForState(ControlState state, int childControlMargin);

  //method declaration
  IGridStyle setGridColorForState(ControlState state, IColor gridColor);

  //method declaration
  IGridStyle setGridThicknessForState(ControlState state, int gridThickness);

  //method declaration
  IGridStyle setGridTypeForState(ControlState state, GridType gridType);
}
