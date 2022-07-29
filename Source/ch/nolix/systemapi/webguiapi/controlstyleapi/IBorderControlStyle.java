//package declaration
package ch.nolix.systemapi.webguiapi.controlstyleapi;

//own imports
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//interface
public interface IBorderControlStyle<BCS extends IBorderControlStyle<BCS>> {
	
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
	BCS setBorderColorForState(ControlState state, IColor borderColor);
	
	//method declaration
	BCS setBorderThicknessForState(ControlState state, int borderThickness);
	
	//method declaration
	BCS setPaddingForState(ControlState state, int padding);
	
	//method declaration
	BCS setBottomBorderColorForState(ControlState state, IColor bottomBorderColor);
	
	//method declaration
	BCS setBottomBorderThicknessForState(ControlState state, int bottomBorderThickness);
	
	//method declaration
	BCS setBottomPaddingForState(ControlState state, int bottomPadding);
	
	//method declaration
	BCS setLeftBorderColorForState(ControlState state, IColor leftBorderColor);
	
	//method declaration
	BCS setLeftBorderThicknessForState(ControlState state, int leftBorderThickness);
	
	//method declaration
	BCS setLeftPaddingForState(ControlState state, int leftPadding);
	
	//method declaration
	BCS setRightBorderColorForState(ControlState state, IColor rightBorderColor);
	
	//method declaration
	BCS setRightBorderThicknessForState(ControlState state, int rightBorderThickness);
	
	//method declaration
	BCS setRightPaddingForState(ControlState state, int rightPadding);
	
	//method declaration
	BCS setTopBorderColorForState(ControlState state, IColor topBorderColor);
	
	//method declaration
	BCS setTopBorderThicknessForState(ControlState state, int topBorderThickness);
	
	//method declaration
	BCS setTopPaddingForState(ControlState state, int topPadding);
}
