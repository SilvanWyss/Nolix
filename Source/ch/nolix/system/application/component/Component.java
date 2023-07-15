//package declaration
package ch.nolix.system.application.component;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programcontrolapi.triggerapi.Refreshable;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.container.SingleContainer;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class Component<C extends Controller<AC>, AC> implements Refreshable {
	
	//attribute
	private final SingleContainer rootControl = new SingleContainer();
	
	//attribute
	private final C controller;
	
	//constructor
	protected Component(final C controller) {
		
		GlobalValidator.assertThat(controller).thatIsNamed(Controller.class).isNotNull();
		
		this.controller = controller;
	}
	
	//method
	public final IControl<?, ?> getOriControl() {
		
		refresh();
		
		return rootControl;
	}
	
	//method
	@Override
	public final void refresh() {
		
		final var control = getControlAssembler().createControl(controller);
		
		rootControl.setControl(control);
		
		getOriSession().updateControlOnCounterpart(rootControl);
	}
	
	//method declaration
	protected abstract IControlAssembler<C, AC> getControlAssembler();
	
	//method
	private final WebClientSession<AC> getOriSession() {
		return controller.getOriSession();
	}
}
