//package declaration
package ch.nolix.system.baseGUIClient;

//own imports
import ch.nolix.element.GUI.InvisibleLayerGUI;
import ch.nolix.system.client.Session;

//class
public abstract class BaseBackGUIClientSession<BGUIC extends BaseBackGUIClient<BGUIC>> extends Session<BGUIC> {
	
	//attribute
	private final InvisibleLayerGUI mGUI = new InvisibleLayerGUI();
	
	//method
	protected final InvisibleLayerGUI getRefGUI() {
		return mGUI;
	}
	
	//method
	protected final void initialize() {
		
		mGUI.setTitle(getParentClient().getApplicationName());
		
		initializeStage2();
	}
	
	//method declaration
	protected abstract void initializeStage2();
	
	//method
	@Override
	protected final void internalCleanForInitialization() {
		mGUI.reset();
	}
	
	//method
	@Override
	protected final void updateCounterpart() {
		
		if (getParentClient().getSessionStackSize() > 1) {
			final var secondTopSession = (BaseBackGUIClientSession<BGUIC>)getParentClient().getRefSecondTopSession();
			mGUI.noteResizeFrom(secondTopSession.getRefGUI());
		}
		
		getParentClient().updateGUIOnCounterpart();
	}
}
