//package declaration
package ch.nolix.common.controllerapi;

//own imports
import ch.nolix.common.chainednode.ChainedNode;
import ch.nolix.common.node.Node;

//interface
/**
 * @author Silvan Wyss
 * @date 2017-01-01
 * @lines 20
 */
public interface IDataProvider {
	
	//method declaration
	/**
	 * @param request
	 * @return the data the given request requests from the current {@link IDataProvider}.
	 */
	Node getData(ChainedNode request);
}
