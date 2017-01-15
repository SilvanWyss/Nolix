/*
 * file:	FrontStandardClient.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	50
 */

//package declaration
package ch.nolix.system.application;

//own import
import ch.nolix.common.specification.Specification;

//class
public class FrontStandardClient extends Client<FrontStandardClient> {
		
	//constructor
	public FrontStandardClient(
		final String ip,
		final int port,
		final String targetApplication
	) {
		
		//Calls constructor of the base class.
		super(ip, port, targetApplication);
	}
	
	//constructor
	public FrontStandardClient(
		final String ip,
		final int port,
		final String targetApplication,
		final Session<FrontStandardClient> session
	) {
		
		super(ip, port, targetApplication, session);
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
	protected void initialize(final Object object) {}

	//method
	protected void update(final Specification updateSpecification) {}
}
