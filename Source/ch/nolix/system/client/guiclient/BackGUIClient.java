//package declaration
package ch.nolix.system.client.guiclient;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.net.endpoint3.EndPoint;
import ch.nolix.system.client.baseguiclient.BaseBackGUIClient;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 20
 */
public final class BackGUIClient extends BaseBackGUIClient<BackGUIClient> {
	
	//constructor
	/**
	 * Creates a new {@link BackGUIClient} with the given duplex controller.
	 * This constructor is public that it can be found by reflection.
	 * 
	 * @param endPoint
	 * @throws ArgumentIsNullException if the given duplex controller is null.
	 */
	public BackGUIClient(final EndPoint endPoint) {
		internalSetEndPoint(endPoint);
	}
}
