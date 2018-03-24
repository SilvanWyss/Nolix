//package declaration
package ch.nolix.element.JMonkeyGUI;

//JMonkey imports
import com.jme3.input.MouseInput;
import com.jme3.input.RawInputListener;
import com.jme3.input.event.JoyAxisEvent;
import com.jme3.input.event.JoyButtonEvent;
import com.jme3.input.event.KeyInputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.input.event.TouchEvent;

import ch.nolix.primitive.validator2.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 100
 */
final class JMonkeyMainFrameRawInputListener
implements RawInputListener {

	//attribute
	private final JMonkeyMainFrame JMonkeyMainFrame_;
	
	//constructor
	/**
	 * Creates a new JMonkey main frame raw input listener
	 * for the given JMonkey main frame.
	 * 
	 * @param JMonkeyMainFrame_
	 * @throws NullArgumentException
	 * if the given JMonkey main frame is null.
	 */
	public JMonkeyMainFrameRawInputListener(
		final JMonkeyMainFrame JMonkeyMainFrame_
	) {
		
		//Checks if the given JMonkey main frame is not null.
		Validator
		.suppose(JMonkeyMainFrame_)
		.thatIsOfType(JMonkeyMainFrame.class)
		.isNotNull();
		
		//Sets the JMonkey main frame
		//of this JMonkey main frame raw input listener.
		this.JMonkeyMainFrame_ = JMonkeyMainFrame_;
	}

	//method
	public void beginInput() {}

	//method
	public void endInput() {}		

	//method
	public void onJoyAxisEvent(JoyAxisEvent joyAxisEvent) {}		

	//method
	public void onJoyButtonEvent(JoyButtonEvent joyButtonEvent) {}
		
	//method
	public void onKeyEvent(KeyInputEvent keyInputEvent) {
		JMonkeyMainFrame_.noteKeyPress(keyInputEvent);
	}

	//method
	public void onMouseButtonEvent(final MouseButtonEvent mouseButtonEvent) {
		
		//Enumerates the button index of the given mouse button event.
		switch (mouseButtonEvent.getButtonIndex()) {
			case MouseInput.BUTTON_LEFT:
				
				//Handles the case that the given mouse button event is a left mouse button press.
				if (mouseButtonEvent.isPressed()) {
					JMonkeyMainFrame_.noteLeftMouseButtonPress();
				}
				
				//Handles the case that the given mouse button event is a right mouse button release.
				if (mouseButtonEvent.isReleased()) {
					JMonkeyMainFrame_.noteLeftMouseButtonRelease();
				}
				
				break;
			case MouseInput.BUTTON_RIGHT:
				
				if (mouseButtonEvent.isPressed()) {
					
					JMonkeyMainFrame_.noteRightMouseButtonPress();
				}
				
				if (mouseButtonEvent.isReleased()) {
					JMonkeyMainFrame_.noteRightMouseButtonRelease();
				}
				
				break;
		}
	}

	//method
	public void onMouseMotionEvent(final MouseMotionEvent mouseMotionEvent) {}

	//method
	public void onTouchEvent(final TouchEvent touchEvent) {}
}
