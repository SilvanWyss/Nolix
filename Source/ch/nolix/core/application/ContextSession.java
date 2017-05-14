//package declaration
package ch.nolix.core.application;

//own import
import ch.nolix.core.validator2.Validator;

//class
/**
 * A context session is a session with a context.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 40
 * @param <CL> - The type of a client of a session.
 */
public abstract class ContextSession<CL extends Client<CL>, CO>
extends Session<CL> {
	
	//attribute
	private final CO context;
	
	//constructor
	/**
	 * Creates new context session with the given context.
	 * 
	 * @param context
	 * @throws NullArgumentException if the given context is null.
	 */
	public ContextSession(CO context) {
		
		//Checks if the given context is not null.
		Validator.supposeThat(context).thatIsNamed("context").isNotNull();

		//Sets the context of this context session.
		this.context = context;
	}
	
	//method
	/**
	 * @return the context of this context session.
	 */
	public final CO getRefContext() {
		return context;
	}
}
