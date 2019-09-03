//package declaration
package ch.nolix.system.baseGUIClient;

import ch.nolix.common.node.Node;
//own imports
import ch.nolix.element.GUI.InvisibleLayerGUI;
import ch.nolix.system.client.Session;

//abstract class
public abstract class BaseBackGUIClientSession<BGUIC extends BaseBackGUIClient<BGUIC>> extends Session<BGUIC> {
	
	//attribute
	private final InvisibleLayerGUI mGUI = new InvisibleLayerGUI();
	
	//method
	/**
	 * Lets the current {@link BaseBackGUIClient} run the given command locally.
	 * 
	 * @param command
	 * @return the current {@link BaseBackGUIClient}.
	 */
	public  void runLocally(final String command) {
		internal_invokeSessionUserRunMethod(Node.fromString(command));
		getParentClient().updateGUIOnCounterpart();
	}
	
	//method
	protected final InvisibleLayerGUI internal_getRefGUI() {
		return mGUI;
	}
	
	//method
	@Override
	protected final void internal_cleanForInitialization() {
		mGUI.reset();
	}
}
