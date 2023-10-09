//package declaration
package ch.nolix.system.application.component;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.container.SingleContainer;
import ch.nolix.systemapi.applicationapi.componentapi.IComponent;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class Component<
	C extends Controller<AC>,
	AC
>
implements IComponent {
	
	//attribute
	private final SingleContainer rootControl = new SingleContainer();
	
	//attribute
	private final C controller;
	
	//constructor
	protected Component(final C controller) {
		
		GlobalValidator.assertThat(controller).thatIsNamed(Controller.class).isNotNull();
		
		this.controller = controller;
		
		rootControl.linkTo(this);
		
		fillUpRootControl();
		
		doRegistrations(controller);
	}
	
	//method
	@Override
	public final IControl<?, ?> getStoredControl() {
		return rootControl;
	}
	
	//method
	@Override
	public final boolean isAlive() {
		return getStoredSession().isAlive();
	}
	
	//method
	@Override
	public final void refresh() {
		
		fillUpRootControl();
		
		//TODO: Lets a Component update the web client with the required CSS.
		//getStoredSession().updateControlOnCounterpart(rootControl);
	}
	
	//method declaration
	protected abstract IControl<?, ?> createControl(C controller);
	
	//method declaration
	protected abstract void doRegistrations(C controller);
	
	//method
	private void fillUpRootControl() {
		
		final var control = createControl(controller);
		
		rootControl.setControl(control);
	}
	
	//method
	private final WebClientSession<AC> getStoredSession() {
		return controller.getStoredSession();
	}
}
