//package declaration
package ch.nolix.system.GUIClient;

//own imports
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.GUI.InvisibleGUI;
import ch.nolix.system.client.ContextSession;

//TODO
//abstract class
public abstract class BackGUISession<C>
extends ContextSession<BackGUIClient, C> {
	
	//TODO
	public BackGUISession(C context) {
		super(context);
	}

	//attribute
	private final InvisibleGUI GUI = new InvisibleGUI();
	
	//method
	public final GUI<?> getRefGUI() {
		return GUI;
	}
}
