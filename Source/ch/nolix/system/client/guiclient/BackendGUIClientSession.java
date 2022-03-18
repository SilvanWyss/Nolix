//package declaration
package ch.nolix.system.client.guiclient;

//own imports
import ch.nolix.system.client.baseguiclient.BaseBackGUIClientSession;

//class
public abstract class BackendGUIClientSession<AC> extends BaseBackGUIClientSession<BackendGUIClient<AC>, AC> {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final Class<?> internalGetRefClientClass() {
		return BackendGUIClient.class;
	}
}
