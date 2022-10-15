//package declaration
package ch.nolix.system.application.webapplication;

//own imports
import ch.nolix.system.application.basewebapplication.BaseBackendWebClientSession;
import ch.nolix.system.webgui.main.WebGUI;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGUI;

//class
public abstract class BackendWebClientSession<AC> extends BaseBackendWebClientSession<BackendWebClient<AC>, AC> {
	
	//attribute
	private final IWebGUI<?> webGUI = new WebGUI();
	
	//method
	public final IWebGUI<?> getRefGUI() {
		return webGUI;
	}
	
	//method
	@Override
	protected final void fullInitialize() {
		
		getRefGUI()
		.setTitle(getApplicationName())
		.setFrontEndReaderAndFrontEndWriter(createFrontendReader(), createFrontendWriter());
		
		initialize();
	}
	
	//method declaration
	protected abstract void initialize();
	
	//TODO: Delete this method.
	//method
	@Override
	protected final void initializeForFirstTime() {
		//Does nothing.
	}
	
	//method
	@Override
	protected final Class<?> internalGetRefClientClass() {
		return BackendWebClient.class;
	}
	
	//method
	@Override
	protected final void updateCounterpart() {
		
		getRefGUI().applyStyleIfHasStyle();
		
		getParentClient().internalUpdateCounterpartFromWebGUI(getRefGUI());
	}
}
