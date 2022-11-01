//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.controlproperty.GridType;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IExtendedControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//interface
public interface IGridContainerStyle<GCS extends IGridContainerStyle<GCS>> extends IExtendedControlStyle<GCS> {
	
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
	GCS setChildControlMarginForState(ControlState state, int childControlMargin);
	
	//method declaration
	GCS setGridColorForState(ControlState state, IColor gridColor);
	
	//method declaration
	GCS setGridThicknessForState(ControlState state, int gridThickness);
	
	//method declaration
	GCS setGridTypeForState(ControlState state, GridType gridType);
}
