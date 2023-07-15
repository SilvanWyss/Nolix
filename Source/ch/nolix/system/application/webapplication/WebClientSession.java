//package declaration
package ch.nolix.system.application.webapplication;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.application.basewebapplication.BaseBackendWebClientSession;
import ch.nolix.system.webgui.main.WebGui;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public abstract class WebClientSession<AC> extends BaseBackendWebClientSession<WebClient<AC>, AC> {
	
	//attribute
	private final IWebGui<?> webGui = new WebGui();
	
	//optional attribute
	private IControl<?, ?> controlToUpdate;
	
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
	protected final void restrictNextCounterpartUpdateToControl(final IControl<?, ?> control) {
		
		GlobalValidator.assertThat(control).thatIsNamed(IControl.class).isNotNull();
		
		controlToUpdate = control;
	}
	
	//method
	@Override
	protected final void updateCounterpartActually() {
		if (restrictedNextUpdateToControl()) {
			
			getStoredParentClient().internalUpdateControlOnCounterpart(controlToUpdate);
			
			controlToUpdate = null;
		} else {
			getStoredParentClient().internalUpdateCounterpartFromWebGui(getStoredGui());
		}
	}
	
	//method
	private boolean restrictedNextUpdateToControl() {
		return (controlToUpdate != null);
	}
}
