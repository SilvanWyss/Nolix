//package declaration
package ch.nolix.core.net.controlleruniversalapi;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;

//interface
/**
 * @author Silvan Wyss
 * @date 2017-01-01
 */
public interface IDataProvider {
	
	//method declaration
	/**
	 * @param request
	 * @return the data the given request requests from the current {@link IDataProvider}.
	 */
	Node getDataForRequest(ChainedNode request);
}
