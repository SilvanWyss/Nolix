//package declaration
package ch.nolix.system.GUIClient;

//own imports
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specification.Statement;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.GUI;
import ch.nolix.primitive.validator2.Validator;
import ch.nolix.system.client.Application;
import ch.nolix.system.client.Client;

//class
/**
 * A {@link FrontGUIClient} provides a {@link GUI}
 * and is controlled by a {@link BackGUIClient}.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 200
 */
public final class FrontGUIClient extends Client<FrontGUIClient> {
	
	//attribute
	private final GUI<?> GUI_;
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient}
	 * that will connect to the given application.
	 * 
	 * @param application
	 */
	public FrontGUIClient(Application<BackGUIClient> application) {
		
		//Calls other constructor.
		this(new Frame());
		
		internal_connectTo(application);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} with the given GUI
	 * that will connect to the given application.
	 * 
	 * @param GUI_
	 * @param application
	 * @throws NullArgumentExcepiton if the given GUI is null.
	 */
	public FrontGUIClient(
		final GUI<?> GUI_,
		final Application<BackGUIClient> application
	) {
		
		//Calls other constructor.
		this(GUI_);
		
		internal_connectTo(application);
	}
	
	//constructor
	/**
	 * Connects the current {@link FrontGUIClient}
	 * to the application with the given name
	 * on given port on the local machine
	 * 
	 * @param port
	 * @param name
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	public FrontGUIClient(
		final int port,
		final String name
	) {
		
		//Calls other constructor.
		this(new Frame());
		
		internal_connectTo(port, name);
	}
	
	//constructor
	/**
	 * Connects the current {@link FrontGUIClient}
	 * to the application with the given name
	 * on given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param name
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	public FrontGUIClient(
		final String ip,
		final int port,
		final String name
	) {
		
		//Calls other constructor.
		this(new Frame());
		
		internal_connectTo(ip, port, name);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} with the given GUI
	 * 
	 * @param GUI_
	 * @throws NullArgumentExcepiton if the given GUI is null.
	 */
	private FrontGUIClient(final GUI<?> GUI_) {
		
		Validator
		.suppose(GUI_)
		.thatIsOfType(GUI.class)
		.isNotNull();
		
		this.GUI_ = GUI_;
		GUI_.setController(new FrontGUIClientController(this));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public void run(final Statement command) {
		internal_runOnCounterpart(			
						
			BackGUIClient.ADD_OR_CHANGE_INTERACTION_ATTRIBUTES_OF_WIDGETS_OF_GUI_HEADER
			+ '('
			+ 
				GUI_.getInteractionAttributesOfWidgetsRecursively()
				.to(ias -> '(' + ias.toString() + ')')
			+ ')',
			
			SESSION_USER_RUN_METHOD_HEADER
			+ '('
			+ command
			+ ')',
			
			BackGUIClient.RESET_COUNTERPART_GUI_HEADER
		);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void internal_finishSessionInitialization() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void internal_run(final Statement command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case BackGUIClient.RESET_GUI_HEADER:
				resetGUI(command.getRefAttributes());
				break;
			case BackGUIClient.RESET_COUNTERPART_GUI_HEADER:
				resetCounterPartGUI(command.getRefAttributes());
				break;
			default:
				
				//Calls method of the base class.
				super.internal_run(command);
		}
	}
	
	//method
	/**
	 * Resets the GUI of the current {@link FrontGUIClient}
	 * with the given attributes.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	private void resetGUI(final Iterable<? extends Specification> attributes) {
		GUI_.reset(attributes);
		GUI_.updateFromConfiguration();
		GUI_.noteMouseMove();
	}
	
	//method
	/**
	 * Resets the GUI of the counterpart of the current {@link FrontGUIClient}
	 * with the given attributes.
	 * 
	 * @param attributes
	 */
	private void resetCounterPartGUI(final Iterable<StandardSpecification> attributes) {
		internal_runOnCounterpart(
			BackGUIClient.RESET_GUI_HEADER
			+ "("
			+ GUI_.getAttributes()
			+ ")"
		);
	}
}
