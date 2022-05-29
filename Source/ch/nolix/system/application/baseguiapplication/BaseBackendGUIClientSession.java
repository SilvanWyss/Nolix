//package declaration
package ch.nolix.system.application.baseguiapplication;

//own imports
import ch.nolix.system.application.main.Session;
import ch.nolix.system.gui.base.InvisibleGUI;
import ch.nolix.systemapi.guiapi.baseapi.IWidgetGUI;

//class
public abstract class BaseBackendGUIClientSession<
	BGUIC extends BaseBackendGUIClient<BGUIC, AC>,
	AC
> extends Session<BGUIC, AC> {
	
	//attribute
	private final InvisibleGUI mGUI = new InvisibleGUI();
	
	//method
	protected final void fullInitialize() {
		
		/*
		 * Important:
		 * Reset the GUI before let the parent Client configure it, otherwise the view area size of the GUI is reset.
		 */
		mGUI.reset();
				
		getParentClient().configureGUI(mGUI);
		
		mGUI
		.setTitle(getApplicationName())
		.setFrontEndReaderAndFrontEndWriter(
			new BaseBackendGUIClientFrontendReader(getParentClient()),
			new BaseBackendGIUIClientFrontendWriter(getParentClient())
		);
		
		initialize();
		
		mGUI.recalculate();
	}
	
	//method
	protected final IWidgetGUI<?> getRefGUI() {
		return mGUI;
	}
	
	//method declaration
	protected abstract void initialize();
	
	//method
	protected final void initializeForFirstTime() {
		getParentClient().createCloseDependencyTo(mGUI);
	}
	
	//method
	@Override
	protected final void updateCounterpart() {
		getParentClient().updateCounterpart();
	}
}
