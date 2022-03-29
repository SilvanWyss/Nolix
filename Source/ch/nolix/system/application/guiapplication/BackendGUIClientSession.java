//package declaration
package ch.nolix.system.application.guiapplication;

import ch.nolix.system.application.baseguiapplication.BaseBackendGUIClientSession;

//class
public abstract class BackendGUIClientSession<AC> extends BaseBackendGUIClientSession<BackendGUIClient<AC>, AC> {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final Class<?> internalGetRefClientClass() {
		return BackendGUIClient.class;
	}
}
