//package declaration
package ch.nolix.system.application.webapplication;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.application.basewebapplication.BaseBackendWebClientSession;
import ch.nolix.system.webgui.main.WebGUI;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGUI;

//class
public abstract class BackendWebClientSession<AC> extends BaseBackendWebClientSession<BackendWebClient<AC>, AC> {
	
	//attribute
	private final IWebGUI<?> webGUI = new WebGUI();
	
	//optional attribute
	private IControl<?, ?> controlToUpdate;
	
	//method
	public final IWebGUI<?> getOriGUI() {
		return webGUI;
	}
	
	//method
	@Override
	protected final void fullInitialize() {
		
		getOriGUI()
		.setTitle(getApplicationName())
		.setFrontEndReaderAndFrontEndWriter(createFrontendReader(), createFrontendWriter());
		
		initialize();
	}
	
	//method declaration
	protected abstract void initialize();
	
	//method
	@Override
	protected final Class<?> getClientClass() {
		return BackendWebClient.class;
	}
	
	//method
	protected final void restrictNextCounterpartUpdateToControl(final IControl<?, ?> control) {
		
		GlobalValidator.assertThat(control).thatIsNamed(IControl.class).isNotNull();
		
		controlToUpdate = control;
	}
	
	//method
	@Override
	protected final void updateCounterpart() {
		if (restrictedNextUpdateToControl()) {
			
			getOriParentClient().internalUpdateControlOnCounterpart(controlToUpdate);
			
			controlToUpdate = null;
		} else {
			getOriParentClient().internalUpdateCounterpartFromWebGUI(getOriGUI());
		}
	}
	
	//method
	private boolean restrictedNextUpdateToControl() {
		return (controlToUpdate != null);
	}
}
