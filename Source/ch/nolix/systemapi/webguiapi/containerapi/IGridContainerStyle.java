//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.controlproperty.GridType;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IExtendedControlStyle;

//interface
public interface IGridContainerStyle extends IExtendedControlStyle<IGridContainerStyle> {
	
	//method declaration
	void removeCustomControlMargins();
	
	//method declaration
	void removeCustomGridColors();
	
	//method declaration
	void removeCustomGridThicknesses();
	
	//method declaration
	void removeCustomGridTypes();
	
	//method declaration
	IGridContainerStyle setControlMarginForState(ControlState state, int controlMargin);
	
	//method declaration
	IGridContainerStyle setGridColorForState(ControlState state, IColor gridColor);
	
	//method declaration
	IGridContainerStyle setGridThicknessForState(ControlState state, int gridThickness);
	
	//method declaration
	IGridContainerStyle setGridTypeForState(ControlState state, GridType gridType);
}
