//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.event.KeyEvent;

//class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 70
 * @param <VG> - The type of a visible GUI.
 */
public abstract class VisibleGUI<VG extends VisibleGUI<VG>>
extends GUI<VG> {

	//method
	public final void noteLeftMouseButtonPress() {
		
		//Calls method of the base class.
		super.noteLeftMouseButtonPress();
		
		paint();
	}
	
	//method
	public final void noteLeftMouseButtonRelease() {
		
		//Calls mehtod of the base class.
		super.noteLeftMouseButtonRelease();
		
		paint();
	}
	
	//method
	public final void noteMouseMove() {
		
		//Calls method of the base class.
		super.noteMouseMove();
		
		paint();
	}
	
	//method
	public final void noteRightMouseButtonPress() {
		
		//Calls method of the base class.
		super.noteRightMouseButtonPress();
		
		paint();
	}
	
	//method
	public final void noteRightMouseButtonRelease() {
		
		//Calls method of the base class.
		super.noteRightMouseButtonRelease();
		
		paint();
	}
	
	//method
	public final void noteKeyTyping(final KeyEvent keyEvent) {
		
		//Calls method of the base class.
		super.noteKeyTyping(keyEvent);
		
		paint();
	}
	
	//abstract method
	/**
	 * Paints this visible GUI.
	 */
	protected abstract void paint();
}
