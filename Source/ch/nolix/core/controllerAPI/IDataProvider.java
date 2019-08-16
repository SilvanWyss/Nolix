//package declaration
package ch.nolix.core.controllerAPI;

import ch.nolix.core.node.Node;
import ch.nolix.core.statement.Statement;

//interface
/**
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 30
 */
public interface IDataProvider {
	
	//abstract method
	/**
	 * @param request
	 * @return the data the given request requests from the current {@link IDataProvider}.
	 */
	public abstract Node getData(Statement request);
	
	//default method
	/**
	 * @param request
	 * @return the data the given request requests from the current {@link IDataProvider}.
	 */
	public default Node getData(final String request) {
		return getData(Statement.fromString(request));
	}
}
