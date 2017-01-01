/*
 * file:	StandardClient.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	50
 */

//package declaration
package ch.nolix.system.application;

//own imports
import ch.nolix.common.duplexController.DuplexController;
import ch.nolix.common.specification.Specification;

//class
/**
 * A standard client is a client that provides standard functionalities.
 */
public final class StandardClient extends Client<StandardClient> {
	
	//constructor
	/**
	 * Creates new standard client that will connect to the given target application.
	 * 
	 * @param targetApplication
	 */
	public StandardClient(Application<StandardClient> targetApplication) {

		//Calls constructor of the base class.
		super(targetApplication);
	}
	

	
	//constructor
	/**
	 * Creates new standard client with the given duplex controller.
	 * 
	 * @param duplexController
	 */
	public StandardClient(DuplexController duplexController) {
				
		//Calls constructor of the base class.
		super(duplexController);
	}
	
	//method
	/**
	 * Appends the given command to this standard client.
	 * 
	 * @param command
	 */
	public void appendCommand(final String command) {
		getRefDuplexController().appendCommand(command);
	}
	
	//method
	/**
	 * @param request
	 * @return the data the given request requests from the origin machine of this standard client
	 * @throws Exception if the given request is not valid or requests for no specification
	 */
	public Specification getDataFromOriginMachine(String request) {
		
		final Object data = getRefDuplexController().getData(request);
		
		//Checks the receveived data.
		if (!(data instanceof Specification)) {
			throw new RuntimeException("Request '" + request + "' requests for a " + data.getClass().getSimpleName() + " instead for a specification.");
		}
		
		return (Specification)data;
	}

	//method
	protected Specification createUpdateSpecification() {
		return new Specification();
	}

	//method
	/**
	 * Updates this standard client using the given update specification.
	 * 
	 * @param updateSpecification
	 */
	protected void update(final Specification updateSpecification) {}

	//method
	/**
	 * Initializes this standard client.
	 * 
	 * @param object
	 */
	protected void initialize(final Object object) {}
}
