//package declaration
package ch.nolix.system.client;

//class
public abstract class StandardClientSession extends Session<StandardClient> {
	
	//method
	public void run(final String name, final String... arguments) {
		internal_invokeSessionUserRunMethod(name, arguments);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final Class<StandardClient> internalGetRefClientClass() {
		return StandardClient.class;
	}
}
