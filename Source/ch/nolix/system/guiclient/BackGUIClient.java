//package declaration
package ch.nolix.system.guiclient;

//own imports
import ch.nolix.common.endpoint5.EndPoint;
import ch.nolix.system.baseguiclient.BaseBackGUIClient;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
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
