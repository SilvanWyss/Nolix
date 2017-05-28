//package declaration
package ch.nolix.core.endPoint2;

//own import
import ch.nolix.core.validator2.Validator;

//class
/**
 * A local end point can send messages to an other local end point.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 100
 */
public class LocalEndPoint<M> extends EndPoint<M> {
	
	//attributes
	private final LocalEndPoint<M> counterpart;
	
	//constructor
	/**
	 * Creates new local end point that will connect to the given target.
	 * 
	 * @param target
	 */
	public LocalEndPoint(final IEndPointTaker<M> target	) {
		
		//Calls consturctor of the base class.
		super(true);
		
		//Creates the counterpart of this local end point.
		counterpart = new LocalEndPoint<M>(this);
		
		target.takeEndPoint(counterpart);
	}
	
	//constructor
	/**
	 * Creates new local end point that will connect to the given target on the given server.
	 * 
	 * @param server
	 * @param target
	 * @throws NullArgumentException if the given target is null.
	 * @throws EmptyArgumentException if the given target is empty.
	 */
	public LocalEndPoint(final Server<M> server, final String target) {
		
		//Calls constructor of the base class.
		super(true);
		
		//Checks if the given target is not null or empty.
		Validator.supposeThat(target).thatIsNamed("target").isNotEmpty();
		
		//Sets the target of this local end point.
		setTarget(target);
		
		//Creates the counter part of this local end point.
		counterpart = new LocalEndPoint<M>(this);
		
		server.takeEndPoint(counterpart);
	}
	
	//constructor
	/**
	 * Creates new local end point.
	 */
	private LocalEndPoint(LocalEndPoint<M> localEndPoint) {
		
		//Calls constructor of the base class.
		super(false);
		
		setTarget(localEndPoint.getTarget());
		
		this.counterpart = localEndPoint;
	}
	
	//method
	/**
	 * @return true if this local end point is a net end point.
	 */
	public boolean isNetEndPoint() {
		return false;
	}

	//method
	/**
	 * Lets this local send the given message.
	 * 
	 * @throws NullArgumentException if the given message is null.
	 * @throws InvalidArgumentException if this local end point is aborted.
	 */
	public void send(final M message) {
		
		//Checks if the given message is not null.
		Validator.supposeThat(message).thatIsNamed("message").isNotNull();
		
		//Checks if this local end point is not aborted.
		throwExceptionIfAborted();
		
		counterpart.receive(message);	
	}
}
