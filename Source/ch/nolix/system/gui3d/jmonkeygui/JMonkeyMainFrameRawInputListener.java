//package declaration
package ch.nolix.system.gui3d.jmonkeygui;

//JMonkey imports
import com.jme3.input.MouseInput;
import com.jme3.input.RawInputListener;
import com.jme3.input.event.JoyAxisEvent;
import com.jme3.input.event.JoyButtonEvent;
import com.jme3.input.event.KeyInputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.input.event.TouchEvent;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
/**
 * @author Silvan Wyss
 * @date 2017-11-17
 */
final class JMonkeyMainFrameRawInputListener implements RawInputListener {
	
	//static attribute
	private static final KeyMapper keyMapper = new KeyMapper();
	
	//attribute
	private final JMonkeyMainFrame mJMonkeyMainFrame;
	
	//constructor
	/**
	 * Creates a new JMonkey main frame raw input listener
	 * for the given JMonkey main frame.
	 * 
	 * @param pJMonkeyMainFrame
	 * @throws ArgumentIsNullException if the given JpJMonkeyMainFrame is null.
	 */
	public JMonkeyMainFrameRawInputListener(final JMonkeyMainFrame pJMonkeyMainFrame) {
		
		//Asserts that the given pJMonkeyMainFrame is not null.
		GlobalValidator.assertThat(pJMonkeyMainFrame).isOfType(JMonkeyMainFrame.class);
		
		//Sets the mJMonkeyMainFrame of the current JMonkeyMainFrameRawInputListener.
		this.mJMonkeyMainFrame = pJMonkeyMainFrame;
	}
	
	//method
	@Override
	public void beginInput() {
		//Does nothing.
	}
	
	//method
	@Override
	public void endInput() {
		//Does nothing.
	}
	
	//method
	@Override
	public void onJoyAxisEvent(JoyAxisEvent joyAxisEvent) {
		//Does nothing.
	}
	
	//method
	@Override
	public void onJoyButtonEvent(JoyButtonEvent joyButtonEvent) {
		//Does nothing.
	}
	
	//method
	@Override
	public void onKeyEvent(KeyInputEvent keyInputEvent) {
		
		final var key = keyMapper.getKeyFrom(keyInputEvent);
		
		if (keyInputEvent.isPressed()) {
			mJMonkeyMainFrame.noteKeyPress(key);
		} else if (keyInputEvent.isReleased()) {
			mJMonkeyMainFrame.noteKeyRelease(key);
		}
	}
	
	//method
	@Override
	public void onMouseButtonEvent(final MouseButtonEvent mouseButtonEvent) {
		
		//Enumerates the button index of the given mouseButtonEvent.
		switch (mouseButtonEvent.getButtonIndex()) {
			case MouseInput.BUTTON_LEFT:
				
				//Handles the case that the given mouseButtonEvent is a left mouse button press.
				if (mouseButtonEvent.isPressed()) {
					mJMonkeyMainFrame.noteLeftMouseButtonPress();
				}
				
				//Handles the case that the given mouseButtonEvent is a right mouse button release.
				if (mouseButtonEvent.isReleased()) {
					mJMonkeyMainFrame.noteLeftMouseButtonRelease();
				}
				
				break;
			case MouseInput.BUTTON_RIGHT:
				
				//Handles the case that the given mouseButtonEvent is a right mouse button press.
				if (mouseButtonEvent.isPressed()) {
					
					mJMonkeyMainFrame.noteRightMouseButtonPress();
				}
				
				//Handles the case that the given mouseButtonEvent is a right mouse button release.
				if (mouseButtonEvent.isReleased()) {
					mJMonkeyMainFrame.noteRightMouseButtonRelease();
				}
				
				break;
			default:
		}
	}
	
	//method
	@Override
	public void onMouseMotionEvent(final MouseMotionEvent mouseMotionEvent) {
		//Does nothing.
	}
	
	//method
	@Override
	public void onTouchEvent(final TouchEvent touchEvent) {
		//Does nothing.
	}
}
