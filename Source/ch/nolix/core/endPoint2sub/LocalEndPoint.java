//package declaration
package ch.nolix.core.endPoint2sub;

//class
/**
* @author Silvan Wyss
* @month 2017-05
* @lines 10
*/
public class LocalEndPoint extends ch.nolix.core.endPoint2.LocalEndPoint<String> {

	//constructor
	public LocalEndPoint(final Server server, final String target) {
		
		//Calls constructor of the base class.
		super(server, target);
	}
}
