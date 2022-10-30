//package declaration
package ch.nolix.systemapi.webguiapi.controlstyleapi;

//own imports
import ch.nolix.systemapi.structureapi.IRelativeOrAbsoluteInt;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//interface
public interface ISizeStyle<S extends ISizeStyle<S>> {
	
	//method declaration
	boolean definesHeightForState(ControlState state);
	
	//method declaration
	boolean definesWidthForState(ControlState state);
	
	//method declaration
	IRelativeOrAbsoluteInt getHeightForState(ControlState state);
	
	//method declaration
	IRelativeOrAbsoluteInt getWidthForState(ControlState state);
	
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
