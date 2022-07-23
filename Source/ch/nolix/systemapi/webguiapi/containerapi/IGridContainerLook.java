//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.controlproperty.GridType;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;
import ch.nolix.systemapi.webguiapi.controllookapi.IExtendedControlLook;

//interface
public interface IGridContainerLook extends IExtendedControlLook<IGridContainerLook> {
	
	//method declaration
	void removeCustomControlMargins();
	
	//method declaration
	void removeCustomGridColors();
	
	//method declaration
	void removeCustomGridThicknesses();
	
	//method declaration
	void removeCustomGridTypes();
	
	//method declaration
	IGridContainerLook setControlMarginForState(ControlState state, int controlMargin);
	
	//method declaration
	IGridContainerLook setGridColorForState(ControlState state, IColor gridColor);
	
	//method declaration
	IGridContainerLook setGridThicknessForState(ControlState state, int gridThickness);
	
	//method declaration
	IGridContainerLook setGridTypeForState(ControlState state, GridType gridType);
}
