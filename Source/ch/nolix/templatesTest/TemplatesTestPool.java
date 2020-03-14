//package declaration
package ch.nolix.templatesTest;

//own imports
import ch.nolix.common.baseTest.TestPool;
import ch.nolix.templatesTest.GUILooksTest.GUILooksTestPool;
import ch.nolix.templatesTest.consoleClientLooksTest.ConsoleClientLookTestPool;

//class
public final class TemplatesTestPool extends TestPool {
	
	//constructor
	public TemplatesTestPool() {
		super(new ConsoleClientLookTestPool(), new GUILooksTestPool());
	}
}
