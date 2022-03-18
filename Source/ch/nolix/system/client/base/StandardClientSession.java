//package declaration
package ch.nolix.system.client.base;

//class
public abstract class StandardClientSession<AC> extends Session<StandardClient<AC>, AC> {
	
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
	protected final Class<?> internalGetRefClientClass() {
		return StandardClient.class;
	}
}
