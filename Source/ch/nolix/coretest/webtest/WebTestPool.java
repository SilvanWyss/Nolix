//package declaration
package ch.nolix.coretest.webtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.webtest.csstest.CssTestPool;

//class
public final class WebTestPool extends TestPool {
	
	//constructor
	public WebTestPool() {
		super(new CssTestPool());
	}
}
