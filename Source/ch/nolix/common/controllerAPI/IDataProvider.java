//package declaration
package ch.nolix.common.controllerAPI;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.node.Node;

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
	public abstract Node getData(ChainedNode request);
}
