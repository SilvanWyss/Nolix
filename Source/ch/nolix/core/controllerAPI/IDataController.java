//package declaration
package ch.nolix.core.controllerAPI;

import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.Statement;

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
	public abstract DocumentNode getData(Statement request);
	
	//default method
	/**
	 * @param request
	 * @return the data the given request requests from this data controller.
	 */
	public default DocumentNode getData(final String request) {
		return getData(new Statement(request));
	}
}
