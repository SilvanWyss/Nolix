//package declaration
package ch.nolix.system.GUIClient;

//own imports
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.GUI.InvisibleGUI;
import ch.nolix.system.client.Session;

//TODO
//abstract class
public abstract class BackGUISession
extends Session<BackGUIClient> {
	
	//attribute
	private final InvisibleGUI GUI = new InvisibleGUI();
	
	//method
	public final GUI<?> getRefGUI() {
		return GUI;
	}
}
