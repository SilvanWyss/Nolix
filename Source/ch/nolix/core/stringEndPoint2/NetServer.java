//package declaration
package ch.nolix.core.stringEndPoint2;

//class
/**
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 10
 */
public final class NetServer extends ch.nolix.core.endPoint2.NetServer<String> {

	//constructor
	public NetServer(final int port) {
		
		//Calls constructor of the base class.
		super(port, s -> s);
	}
}
