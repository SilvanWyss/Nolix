//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

//own imports
import ch.nolix.system.gui.widgetgui.GridType;
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.controllookapi.IExtendedControlLook;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//interface
public interface IGridContainerLook extends IExtendedControlLook<IGridContainerLook> {
	
	//method declaration
	int getControlMargin();
	
	//method declaration
	IColor getGridColor();
	
	//method declaration
	int getGridThickness();
	
	//method declaration
	GridType getGridType();
	
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
