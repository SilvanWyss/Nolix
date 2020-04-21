//package declaration
package ch.nolix.element.inputDevices;

//class
public final class Mouse {
	
	//attributes
	private boolean leftMouseButtonIsPressed = false;
	private boolean rightMouseButtonIsPressed = false;
	private boolean mouseWheelIsPressed = false;
	
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
