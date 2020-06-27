//package declaration
package ch.nolix.system.GUIClient;

//own import
import ch.nolix.system.baseGUIClient.BaseBackGUIClientSession;

//class
public abstract class BackGUIClientSession extends BaseBackGUIClientSession<BackGUIClient> {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final Class<BackGUIClient> internalGetRefClientClass() {
		return BackGUIClient.class;
	}
}
