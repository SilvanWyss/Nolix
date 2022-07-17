//package declaration
package ch.nolix.systemapi.guiapi.controllookapi;

//own imports
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//interface
public interface IBaseBorderControlLook<BCL extends IBaseBorderControlLook<BCL>> {
	
	//method declaration
	boolean hasBottomBorderColor();
	
	//method declaration
	boolean hasBottomBorderThickness();
	
	//method declaration
	boolean hasBottomPadding();
	
	//method declaration
	boolean hasLeftBorderColor();
	
	//method declaration
	boolean hasLeftBorderThickness();
	
	//method declaration
	boolean hasLeftPadding();
	
	//method declaration
	boolean hasRightBorderColor();
	
	//method declaration
	boolean hasRightBorderThickness();
	
	//method declaration
	boolean hasRightPadding();
	
	//method declaration
	boolean hasTopBorderColor();
	
	//method declaration
	boolean hasTopBorderThickness();
	
	//method declaration
	boolean hasTopPadding();
	
	//method declaration
	void removeBorderColor();
	
	//method declaration
	void removeBorderThickness();
	
	//method declaration
	void removeBottomBorderColor();
	
	//method declaration
	void removeBottomBorderThickness();
	
	//method declaration
	void removeBottomPadding();
	
	//method declaration
	void removeLeftBorderColor();
	
	//method declaration
	void removeLeftBorderThickness();
	
	//method declaration
	void removeLeftPadding();
	
	//method declaration
	void removePadding();
	
	//method declaration
	void removeRightBorderColor();
	
	//method declaration
	void removeRightBorderThickness();
	
	//method declaration
	void removeRightPadding();
	
	//method declaration
	void removeTopBorderColor();
	
	//method declaration
	void removeTopBorderThickness();
	
	//method declaration
	void removeTopPadding();
	
	//method declaration
	BCL setBorderColorForState(ControlState state, IColor borderColor);
	
	//method declaration
	BCL setBorderThicknessForState(ControlState state, int borderThickness);
	
	//method declaration
	BCL setPaddingForState(ControlState state, int padding);
	
	//method declaration
	BCL setBottomBorderColorForState(ControlState state, IColor bottomBorderColor);
	
	//method declaration
	BCL setBottomBorderThicknessForState(ControlState state, int bottomBorderThickness);
	
	//method declaration
	BCL setBottomPaddingForState(ControlState state, int bottomPadding);
	
	//method declaration
	BCL setLeftBorderColorForState(ControlState state, IColor leftBorderColor);
	
	//method declaration
	BCL setLeftBorderThicknessForState(ControlState state, int leftBorderThickness);
	
	//method declaration
	BCL setLeftPaddingForState(ControlState state, int leftPadding);
	
	//method declaration
	BCL setRightBorderColorForState(ControlState state, IColor rightBorderColor);
	
	//method declaration
	BCL setRightBorderThicknessForState(ControlState state, int rightBorderThickness);
	
	//method declaration
	BCL setRightPaddingForState(ControlState state, int rightPadding);
	
	//method declaration
	BCL setTopBorderColorForState(ControlState state, IColor topBorderColor);
	
	//method declaration
	BCL setTopBorderThicknessForState(ControlState state, int topBorderThickness);
	
	//method declaration
	BCL setTopPaddingForState(ControlState state, int topPadding);
}
