//package declaration
package ch.nolix.system.GUIClient;

//own imports
import ch.nolix.element.GUI.GUI;
import ch.nolix.system.client.Session;

//abstract class
public abstract class BackGUISession
extends Session<BackGUIClient> {
	
	//attribute
	private final GUI<?> GUI_ = new BrowserGUI();
	
	//method
	public final GUI<?> getRefGUI() {
		return GUI_;
	}
}
