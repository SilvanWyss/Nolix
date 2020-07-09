//package declaration
package ch.nolix.system.consoleClient;

//own imports
import ch.nolix.common.endPoint5.EndPoint;
import ch.nolix.system.baseGUIClient.BaseBackGUIClient;

//class
/**
 * @author Silvan Wyss
 * @month 2017-03
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
