//package declaration
package ch.nolix.system.client.base;

//class
public abstract class StandardClientSession extends Session<StandardClient> {
	
	//method
	public void run(final String name, final String... arguments) {
		internalInvokeSessionUserRunMethod(name, arguments);
	}
	
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
