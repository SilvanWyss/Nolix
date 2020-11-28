//package declaration
package ch.nolix.system.guiclient;

import ch.nolix.system.baseguiclient.BaseBackGUIClientSession;

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
