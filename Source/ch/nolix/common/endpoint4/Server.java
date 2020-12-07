//package declaration
package ch.nolix.common.endpoint4;

//own imports
import ch.nolix.common.closeableelement.CloseController;
import ch.nolix.common.closeableelement.ICloseableElement;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;

//class
/**
 * A server manages end point taker.
 * A server is abortable.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 80
 */
public class Server<M, R> implements ICloseableElement {
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//multi-attribute
	private final LinkedList<IEndPointTaker<M, R>> endPointTaker = new LinkedList<>();
	
	//method
	/**
	 * Adds the given end point taker to this server.
	 * 
	 * @param endPointTaker
	 * @throws InvalidArgumentException
	 * if this server contains an end point taker with the same name as the given end point taker.
	 */
	public void addEndPointTaker(final IEndPointTaker<M, R> endPointTaker) {
		
		if (containsEndPointTaker(endPointTaker.getName())) {
			throw new InvalidArgumentException(
				this,
				"contains an end point taker with the same name as the given end point taker"
			);
		}
		
		this.endPointTaker.addAtEnd(endPointTaker);
	}
	
	//method
	/**
	 * @param name
	 * @return true if this server contains an end point taker with the given name.
	 */
	public final boolean containsEndPointTaker(final String name) {
		return endPointTaker.contains(ept -> ept.hasName(name));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	/**
	 * Removes the end point taker with the given name from this server.
	 * 
	 * @param name
	 * @throws InvalidArgumentException if this server does not contain an end point taker with the given name.
	 */
	public void removeEndPointTaker(final String name) {
		endPointTaker.removeFirst(ept -> ept.hasName(name));
	}
	
	//method
	/**
	 * Lets this server take the given end point.
	 * 
	 * @param endPoint
	 */
	public final void takeEndPoint(final EndPoint<M, R> endPoint) {
		endPointTaker
		.getRefFirst(ept -> ept.hasName(endPoint.getTarget()))
		.takeEndPoint(endPoint);
	}
}
