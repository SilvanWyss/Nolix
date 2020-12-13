//package declaration
package ch.nolix.common.controllerapi;

//own imports
import ch.nolix.common.chainednode.ChainedNode;
import ch.nolix.common.node.BaseNode;

//interface
/**
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 20
 */
public interface IDataProvider {
	
	//method declaration
	/**
	 * @param request
	 * @return the data the given request requests from the current {@link IDataProvider}.
	 */
	BaseNode getData(ChainedNode request);
}
