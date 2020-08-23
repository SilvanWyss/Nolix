//package declaration
package ch.nolix.system.baseGUIClient;

//own imports
import ch.nolix.element.GUI.InvisibleGUI;
import ch.nolix.system.client.Session;

//class
public abstract class BaseBackGUIClientSession<BGUIC extends BaseBackGUIClient<BGUIC>> extends Session<BGUIC> {
	
	//attribute
	private final InvisibleGUI mGUI = new InvisibleGUI();
	
	//method
	protected final InvisibleGUI getRefGUI() {
		return mGUI;
	}
	
	//TODO: Rename initialize method to internalInitialize.
	//method
	protected final void initialize() {
		
		/*
		 * Important:
		 * Reset the GUI before let the parent Client configure it, otherwise the view area size of the GUI is reset.
		 */
		mGUI.reset();
				
		getParentClient().configureGUI(mGUI);
		
		mGUI
		.setTitle(getApplicationName())
		.setFrontEndReaderAndFrontEndWriter(
			new BaseBackGUIClientFrontEndReader(getParentClient()),
			new BaseBackGIUIClientFrontEndWriter(getParentClient())
		);
		
		initializeStage2();
		
		mGUI.recalculate();
	}
	
	//method
	protected final void initializeAfterCreation() {
		getParentClient().createCloseDependencyTo(mGUI);
	}
	
	//method declaration
	protected abstract void initializeStage2();
	
	//TODO: Rename updateCounterpart method to internalUpdateCounterpart.
	//method
	@Override
	protected final void updateCounterpart() {
		getParentClient().updateGUIOnCounterpart();
	}
}
