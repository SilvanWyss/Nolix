//package declaration
package ch.nolix.system.application.webapplication;

//own imports
import ch.nolix.system.application.basewebapplication.BaseWebClientSession;
import ch.nolix.system.webgui.main.WebGui;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public abstract class WebClientSession<AC> extends BaseWebClientSession<WebClient<AC>, AC> {
	
	//attribute
	private final IWebGui<?> webGui = new WebGui();
	
	//method
	public final IWebGui<?> getStoredGui() {
		return webGui;
	}
	
	//method
	public void updateControlOnCounterpart(final IControl<?, ?> control) {
		getStoredParentClient().internalUpdateControlOnCounterpart(control);
	}
	
	//method
	@Override
	protected final void fullInitialize() {
		
		getStoredGui()
		.setTitle(getApplicationName())
		.setFrontEndReaderAndFrontEndWriter(createFrontendReader(), createFrontendWriter());
		
		initialize();
	}
	
	//method declaration
	protected abstract void initialize();
	
	//method
	@Override
	protected final Class<?> getClientClass() {
		return WebClient.class;
	}
	
	//method
	protected final void refreshControl(final IControl<?, ?> control) {
		getStoredParentClient().internalUpdateControlOnCounterpart(control);
	}
	
	//method
	@Override
	protected final void updateCounterpartActually() {
		getStoredParentClient().internalUpdateCounterpartFromWebGui(getStoredGui());
	}
}
