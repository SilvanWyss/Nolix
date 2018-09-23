//package declaration
package ch.nolix.system.GUIClient;

import ch.nolix.core.endPoint5.EndPoint;
import ch.nolix.system.GUIClientoid.BackGUIClientoid;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
public final class BackGUIClient extends BackGUIClientoid<BackGUIClient> {
	
	//constructor
	/**
	 * Creates a new {@link BackGUIClient} with the given duplex controller.
	 * This constructor is public that it can be found by reflection.
	 * 
	 * @param endPoint
	 * @throws NullArgumentException if the given duplex controller is not an instance.
	 */
	public BackGUIClient(final EndPoint endPoint) {
		internal_setDuplexController(endPoint);
	}
}
