//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.element.GUI.InvisibleLayerGUI;
import ch.nolix.element.GUI.LayerGUI;
import ch.nolix.system.client.Session;

//abstract class
public abstract class BackGUIClientoidSession<BGUIC extends BackGUIClientoid<BGUIC>> extends Session<BGUIC> {
	
	//attribute
	private LayerGUI<?> mGUI = new InvisibleLayerGUI();
	
	//package-visible method
	protected final LayerGUI<?> getRefGUI() {
		return mGUI;
	}
}
