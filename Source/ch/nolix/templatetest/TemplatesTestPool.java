//package declaration
package ch.nolix.templatetest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.templatetest.consoleclientlooktest.ConsoleClientLookTestPool;
import ch.nolix.templatetest.guilooktest.GUILooksTestPool;

//class
public final class TemplatesTestPool extends TestPool {
	
	//constructor
	public TemplatesTestPool() {
		super(new ConsoleClientLookTestPool(), new GUILooksTestPool());
	}
}
