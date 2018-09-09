//package declaration
package ch.nolix.core.controllerAPI;

//own imports
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specification.Statement;

//interface
/**
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 30
 */
public interface IDataController {

	//abstract method
	/**
	 * @param request
	 * @return the data the given request requests from this data controller.
	 */
	public abstract StandardSpecification getData(Statement request);
	
	//default method
	/**
	 * @param request
	 * @return the data the given request requests from this data controller.
	 */
	public default StandardSpecification getData(final String request) {
		return getData(new Statement(request));
	}
}
