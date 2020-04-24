//package declaration
package ch.nolix.templateTest;

//own imports
import ch.nolix.common.baseTest.TestPool;
import ch.nolix.templateTest.GUILookTest.GUILooksTestPool;
import ch.nolix.templateTest.consoleClientLookTest.ConsoleClientLookTestPool;

//class
public final class TemplatesTestPool extends TestPool {
	
	//constructor
	public TemplatesTestPool() {
		super(new ConsoleClientLookTestPool(), new GUILooksTestPool());
	}
}
