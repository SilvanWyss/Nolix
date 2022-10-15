//package declaration
package ch.nolix.systemapi.guiapi.canvasuniversalapi;

//own imports
import ch.nolix.systemapi.structureapi.IRelativeOrAbsoluteInt;

//interface
public interface Dimensionable<D extends Dimensionable<D>> {
	
	//method declaration
	IRelativeOrAbsoluteInt getMaxHeight();
	
	//method declaration
	IRelativeOrAbsoluteInt getMaxWidth();
	
	//method declaration
	boolean hasMaxHeight();
	
	//method declaration
	boolean hasMaxWidth();
	
	//method declaration
	void removeMaxHeight();
	
	//method declaration
	void removeMaxWidth();
	
	//method declaration
	D setMaxHeight(int maxHeight);
	
	//method declaration
	D setMaxHeightInPercentOfViewAreaHeight(double maxHeightInPercentOfViewAreaHeight);
	
	//method declaration
	D setMaxWidth(int maxWidth);
	
	//method declaration
	D setMaxWidthInPercentOfViewAreaWidth(double maxWidthInPercentOfViewAreaWidth);
}
