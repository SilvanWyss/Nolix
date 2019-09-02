//package declaration
package ch.nolix.system.baseGUIClient;

//own imports
import ch.nolix.element.GUI.InvisibleLayerGUI;
import ch.nolix.system.client.Session;

//abstract class
public abstract class BaseBackGUIClientSession<BGUIC extends BaseBackGUIClient<BGUIC>> extends Session<BGUIC> {
	
	//attribute
	private final InvisibleLayerGUI mGUI = new InvisibleLayerGUI();
	
	//package-visible method
	protected final InvisibleLayerGUI getRefGUI() {
		return mGUI;
	}
	
	//method
	@Override
	protected final void cleanBeforeInitialize() {
		mGUI.reset();
	}
}
