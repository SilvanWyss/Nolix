//package declaration
package ch.nolix.systemapi.guiapi.controllookapi;

//own imports
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//interface
public interface IBorderControlLook<BCL extends IBorderControlLook<BCL>> {
	
	//method declaration
	IColor getBottomBorderColor();
	
	//method declaration
	int getBottomBorderThickness();
	
	//method declaration
	int getBottomPadding();
	
	//method declaration
	IColor getLeftBorderColor();
	
	//method declaration
	int getLeftBorderThickness();
	
	//method declaration
	int getLeftPadding();
	
	//method declaration
	IColor getRightBorderColor();
	
	//method declaration
	int getRightBorderThickness();
	
	//method declaration
	int getRightPadding();
	
	//method declaration
	IColor getTopBorderColor();
	
	//method declaration
	int getTopBorderThickness();
	
	//method declaration
	int getTopPadding();
	
	//method declaration
	void removeCustomBorderColors();
	
	//method declaration
	void removeCustomBorderThicknesses();
	
	//method declaration
	void removeCustomBottomBorderColors();
	
	//method declaration
	void removeCustomBottomBorderThicknesses();
	
	//method declaration
	void removeCustomBottomPaddings();
	
	//method declaration
	void removeCustomLeftBorderColors();
	
	//method declaration
	void removeCustomLeftBorderThicknesses();
	
	//method declaration
	void removeCustomLeftPaddings();
	
	//method declaration
	void removeCustomPaddings();
	
	//method declaration
	void removeCustomRightBorderColors();
	
	//method declaration
	void removeCustomRightBorderThicknesses();
	
	//method declaration
	void removeCustomRightPaddings();
	
	//method declaration
	void removeCustomTopBorderColors();
	
	//method declaration
	void removeCustomTopBorderThicknesses();
	
	//method declaration
	void removeCustomTopPaddings();
	
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
