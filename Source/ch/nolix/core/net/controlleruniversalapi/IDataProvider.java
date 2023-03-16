//package declaration
package ch.nolix.core.net.controlleruniversalapi;

//own imports
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

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
	INode<?> getDataForRequest(IChainedNode request);
}
