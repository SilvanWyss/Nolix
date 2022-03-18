//package declaration
package ch.nolix.system.client.guiclient;

//own imports
import ch.nolix.system.client.baseguiclient.BaseBackGUIClientSession;

//class
public abstract class BackGUIClientSession<AC> extends BaseBackGUIClientSession<BackGUIClient<AC>, AC> {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final Class<?> internalGetRefClientClass() {
		return BackGUIClient.class;
	}
}
