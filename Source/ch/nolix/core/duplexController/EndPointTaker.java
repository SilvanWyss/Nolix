//package declaration
package ch.nolix.core.duplexController;

//own imports
import ch.nolix.core.endPoint3.EndPoint;
import ch.nolix.core.endPoint3.IEndPointTaker;
import ch.nolix.core.endPoint3.NetEndPoint;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 60
 */
final class EndPointTaker implements IEndPointTaker {

	//attribute
	private final NetServer netServer;
	
	//constructor
	/**
	 * Creates new end point taker with the given duplex contorller taker.
	 * 
	 * @param duplexControllerTaker
	 * @throws NullArgumentException if the given duplex controller taker is null.
	 */
	public EndPointTaker(NetServer netServer) {
		this.netServer = netServer;
	}
	
	//method
	/**
	 * @return the name of this end point taker.
	 */
	public String getName() {
		//TODO
		return null;
	}
	
	//method
	/**
	 * Lets this end point taker take the given end point.
	 * 
	 * @param endPoint
	 * @throws NullArgumentException if the given end point is null.
	 * @throws InvalidArgumentException if the given end point is no NetEndPoint.
	 */
	public void takeEndPoint(final EndPoint endPoint) {		
		netServer.takeDuplexController(new NetDuplexController((NetEndPoint)endPoint));
	}
}
