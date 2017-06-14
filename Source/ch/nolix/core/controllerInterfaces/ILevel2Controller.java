//package declaration
package ch.nolix.core.controllerInterfaces;

//own imports
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.Statement;

//interface
/**
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 30
 */
public interface ILevel2Controller extends ILevel1Controller {

	//abstract method
	/**
	 * @param request
	 * @return the data the given request requests from this level 2 controller.
	 */
	public abstract Specification getData(Statement request);
	
	//default method
	/**
	 * @param request
	 * @return the data the given request requests from this level 2 controller.
	 */
	public default Specification getData(final String request) {
		return getData(new Statement(request));
	}
}
