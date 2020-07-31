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
	
	//TODO: Rename initialize method to internalInitialize.
	//method
	protected final void initialize() {
		
		getParentClient().createCloseDependencyTo(mGUI);
		getParentClient().configureGUI(mGUI);
		
		mGUI
		.reset()
		.setTitle(getApplicationName())
		.setFrontEndReaderAndFrontEndWriter(
			new BaseBackGUIClientFrontEndReader(getParentClient()),
			new BaseBackGIUIClientFrontEndWriter(getParentClient())
		);
		
		initializeStage2();
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
