//package declaration
package ch.nolix.systemtest.elementtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.elementtest.multistateconfigurationtest.MultiStateConfigurationTestPool;
import ch.nolix.systemtest.elementtest.mutableelementtest.MutableElementTestPool;
import ch.nolix.systemtest.elementtest.styletest.StyleTestPool;

//class
public final class ElementTestPool extends TestPool {
	
	//constructor
	public ElementTestPool() {
		super(new MultiStateConfigurationTestPool(), new MutableElementTestPool(), new StyleTestPool());
	}
}
