//package declaration
package ch.nolix.core.controllerAPI;

import ch.nolix.core.chainedNode.ChainedNode;
import ch.nolix.core.node.Node;

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
	public abstract Node getData(ChainedNode request);
	
	//default method
	/**
	 * @param request
	 * @return the data the given request requests from the current {@link IDataProvider}.
	 */
	public default Node getData(final String request) {
		return getData(ChainedNode.fromString(request));
	}
}
