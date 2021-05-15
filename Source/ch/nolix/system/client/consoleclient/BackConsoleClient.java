//package declaration
package ch.nolix.system.client.consoleclient;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.net.endpoint3.EndPoint;
import ch.nolix.system.client.baseguiclient.BaseBackGUIClient;

//class
/**
 * @author Silvan Wyss
 * @date 2017-03-02
 * @lines 20
 */
public final class BackConsoleClient extends BaseBackGUIClient<BackConsoleClient> {
	
	//constructor
	/**
	 * Creates a new {@link BackConsoleClient} with the given endPoint.
	 * 
	 * @param endPoint
	 * @throws ArgumentIsNullException if the given endPoint is null.
	 */
	public BackConsoleClient(final EndPoint endPoint) {
		internalSetEndPoint(endPoint);
	}
}
