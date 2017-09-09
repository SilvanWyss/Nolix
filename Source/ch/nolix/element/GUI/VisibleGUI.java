//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.event.KeyEvent;

//abstract class
/**
 * A visible GUI is a GUI that is visible on the screen.
 * 
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 90
 * @param <VGUI> The type of a visible GUI.
 */
public abstract class VisibleGUI<VGUI extends VisibleGUI<VGUI>> extends GUI<VGUI> {

	//method
	/**
	 * Lets this visible GUI note a left mouse button press.
	 */
	public final void noteLeftMouseButtonPress() {
		
		//Calls method of the base class.
		super.noteLeftMouseButtonPress();
		
		//repaint();
	}
	
	//method
	/**
	 * Lets this visible GUI note a left mouse button release.
	 */
	public final void noteLeftMouseButtonRelease() {
		
		//Calls method of the base class.
		super.noteLeftMouseButtonRelease();
		
		//repaint();
	}
	
	//method
	/**
	 * Lets this visible GUI note a mouse move.
	 */
	public final void noteMouseMove() {
		
		//Calls method of the base class.
		super.noteMouseMove();
		
		//repaint();
	}
	
	//method
	/**
	 * Lets this visible GUI note a right mouse button press.
	 */
	public final void noteRightMouseButtonPress() {
		
		//Calls method of the base class.
		super.noteRightMouseButtonPress();
		
		//repaint();
	}
	
	//method
	/**
	 * Lets this visible GUI note a right mouse button release.
	 */
	public final void noteRightMouseButtonRelease() {
		
		//Calls method of the base class.
		super.noteRightMouseButtonRelease();
		
		//repaint();
	}
	
	//method
	/**
	 * Lets this visible GUI note a key typing.
	 * 
	 * @param keyEvent
	 */
	public final void noteKeyTyping(final KeyEvent keyEvent) {
		
		//Calls method of the base class.
		super.noteKeyTyping(keyEvent);
		
		//repaint();
	}
}
