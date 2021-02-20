//package declaration
package ch.nolix.system.client.guiclient;

import ch.nolix.system.client.baseguiclient.BaseBackGUIClientSession;

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
