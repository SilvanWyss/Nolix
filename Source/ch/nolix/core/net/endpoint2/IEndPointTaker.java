//package declaration
package ch.nolix.core.net.endpoint2;

//own imports
import ch.nolix.core.attributeapi.mandatoryattributeapi.Named;

//interface
/**
 * A {@link IEndPointTaker} takes {@link EndPoint}s.
 */
public interface IEndPointTaker extends Named {
	
	//method declaration
	/**
	 * Lets the current {@link IEndPointTaker} take the given {@link EndPoint}.
	 * 
	 * @param endPoint
	 */
	void takeEndPoint(EndPoint endPoint);
}
