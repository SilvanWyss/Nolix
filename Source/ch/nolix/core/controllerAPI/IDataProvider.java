//package declaration
package ch.nolix.core.controllerAPI;

//own imports
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.Statement;

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
	public abstract DocumentNode getData(Statement request);
	
	//default method
	/**
	 * @param request
	 * @return the data the given request requests from the current {@link IDataProvider}.
	 */
	public default DocumentNode getData(final String request) {
		return getData(new Statement(request));
	}
}
