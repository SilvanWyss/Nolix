//package declaration
package ch.nolix.system.client;

//abstract class
public abstract class StandardClientSession extends Session<StandardClient> {
	
	//method
	public void run(final String name, final String... arguments) {
		internal_invokeSessionUserRunMethod(name, arguments);
	}
}
