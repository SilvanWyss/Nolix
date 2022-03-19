//package declaration
package ch.nolix.system.application.baseguiclient;

//own imports
import ch.nolix.element.gui.base.IWidgetGUI;
import ch.nolix.element.gui.base.InvisibleGUI;
import ch.nolix.system.application.main.Session;

//class
public abstract class BaseBackendGUIClientSession<
	BGUIC extends BaseBackendGUIClient<BGUIC, AC>,
	AC
> extends Session<BGUIC, AC> {
	
	//attribute
	private final InvisibleGUI mGUI = new InvisibleGUI();
	
	//method
	protected final IWidgetGUI<?> getRefGUI() {
		return mGUI;
	}
	
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
			new BaseBackendGUIClientFrontendReader(getParentClient()),
			new BaseBackendGIUIClientFrontendWriter(getParentClient())
		);
		
		initializeBaseBackGUIClientSession();
		
		mGUI.recalculate();
	}
	
	//method
	protected final void initializeForFirstTime() {
		getParentClient().createCloseDependencyTo(mGUI);
	}
	
	//method declaration
	protected abstract void initializeBaseBackGUIClientSession();
	
	//method
	@Override
	protected final void updateCounterpart() {
		getParentClient().updateCounterpart();
	}
}
