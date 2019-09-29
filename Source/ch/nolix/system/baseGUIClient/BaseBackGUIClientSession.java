//package declaration
package ch.nolix.system.baseGUIClient;

//own imports
import ch.nolix.common.node.Node;
import ch.nolix.element.GUI.InvisibleLayerGUI;
import ch.nolix.system.client.Session;

//abstract class
public abstract class BaseBackGUIClientSession<BGUIC extends BaseBackGUIClient<BGUIC>> extends Session<BGUIC> {
	
	//attribute
	private final InvisibleLayerGUI mGUI = new InvisibleLayerGUI();
	
	//method
	public final void invokeRunMethodLocally(final String command) {
		internal_invokeSessionUserRunMethod(Node.fromString(command));
		getParentClient().updateGUIOnCounterpart();
	}
	
	//method
	protected final InvisibleLayerGUI getRefGUI() {
		return mGUI;
	}
	
	//method
	@Override
	protected final void internal_cleanForInitialization() {
		mGUI.reset();
	}
	
	//method
	@Override
	protected final void updateCounterpart() {
		
		if (getParentClient().getSessionStackSize() > 1) {
			final var secondTopSession = (BaseBackGUIClientSession<BGUIC>)getParentClient().getRefSecondTopSession();
			final var lGUI = secondTopSession.getRefGUI();
			mGUI.noteResize(lGUI.getWidth(), lGUI.getHeight());
		}
		
		mGUI.refresh();
		getParentClient().updateGUIOnCounterpart();
	}
}
