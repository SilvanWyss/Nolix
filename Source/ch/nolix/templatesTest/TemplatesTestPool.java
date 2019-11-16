//package declaration
package ch.nolix.templatesTest;

//own imports
import ch.nolix.common.baseTest.TestPool;
import ch.nolix.templatesTest.GUILooksTest.GUILooksTestPool;

//class
public final class TemplatesTestPool extends TestPool {
	
	//constructor
	public TemplatesTestPool() {
		addTestPool(new GUILooksTestPool());
	}
}
