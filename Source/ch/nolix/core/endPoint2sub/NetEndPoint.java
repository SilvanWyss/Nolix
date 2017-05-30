//package declaration
package ch.nolix.core.endPoint2sub;

//class
/**
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 20
 */
public final class NetEndPoint extends ch.nolix.core.endPoint2.NetEndPoint<String> {

	//constructor
	public NetEndPoint(final String ip, final int port) {
		
		//Calls constructor of the base class.
		super(ip, port, s -> s);
	}
	
	//constructor
	public NetEndPoint(final String ip, final int port, final String target) {
		
		//Calls constructor of the base class.
		super(ip, port, target, s -> s);
	}
}
