//package declaration
package ch.nolix.system.client.base;

//class
public abstract class StandardClientSession extends Session<StandardClient> {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initializeForFirstTime() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final Class<StandardClient> internalGetRefClientClass() {
		return StandardClient.class;
	}
}
