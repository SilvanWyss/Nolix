//package declaration
package ch.nolix.system.GUIClientoid;

import ch.nolix.element.GUI.GUI;
import ch.nolix.system.client.Session;

//abstract class
public abstract class BackGUIClientoidSession<BGUIC extends BackGUIClientoid<BGUIC>>
extends Session<BGUIC> {
	
	//attribute
	private GUI<?> mGUI = new BackGUIClientoidApplicationGUI();
	
	//package-visible method
	protected final GUI<?> getRefGUI() {
		return mGUI;
	}
}
