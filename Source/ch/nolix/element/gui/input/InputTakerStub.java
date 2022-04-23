//package declaration
package ch.nolix.element.gui.input;

//own imports
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.systemapi.guiapi.inputapi.IInputTaker;
import ch.nolix.systemapi.guiapi.inputapi.Key;

//class
public class InputTakerStub implements IInputTaker {
	
	//method
	@Override
	public void noteKeyDown(final Key key) {}
	
	//method
	@Override
	public void noteKeyRelease(final Key key) {}
	
	//method
	@Override
	public void noteKeyTyping(final Key key) {}
	
	//method
	@Override
	public void noteLeftMouseButtonClick() {}
	
	//method
	@Override
	public void noteLeftMouseButtonPress() {}
	
	//method
	@Override
	public void noteLeftMouseButtonRelease() {}
	
	//method
	@Override
	public void noteMouseMove(final int cursorXPositionOnViewArea, final int cursorYPositionOnViewArea) {}
	
	//method
	@Override
	public void noteMouseWheelClick() {}
	
	//method
	@Override
	public void noteMouseWheelPress() {}
	
	//method
	@Override
	public void noteMouseWheelRelease() {}
	
	//method
	@Override
	public void noteMouseWheelRotationStep(final RotationDirection rotationDirection) {}
	
	//method
	@Override
	public void noteRightMouseButtonClick() {}
	
	//method
	@Override
	public void noteRightMouseButtonPress() {}
	
	//method
	@Override
	public void noteRightMouseButtonRelease() {}
}
