//package declaration
package ch.nolix.element.inputdevice;

//class
public final class Mouse {
	
	//attributes
	private boolean leftMouseButtonIsPressed;
	private boolean rightMouseButtonIsPressed;
	private boolean mouseWheelIsPressed;
	
	//method
	public boolean leftMouseButtonIsPressed() {
		return leftMouseButtonIsPressed;
	}
	
	//method
	public boolean mouseWheelIsPressed() {
		return mouseWheelIsPressed;
	}
	
	//method
	public void pressLeftMouseButton() {
		leftMouseButtonIsPressed = true;
	}
	
	//method
	public void pressMouseWheel() {
		mouseWheelIsPressed = true;
	}
	
	//method
	public void pressRightMouseButton() {
		rightMouseButtonIsPressed = true;
	}
	
	//method
	public void releaseLeftMouseButton() {
		leftMouseButtonIsPressed = false;
	}
	
	//method
	public void releaseMouseWheel() {
		mouseWheelIsPressed = false;
	}
	
	//method
	public void releaseRightMouseButton() {
		rightMouseButtonIsPressed = false;
	}
	
	//method
	public boolean rightMouseButtonIsPressed() {
		return rightMouseButtonIsPressed;
	}
}
