//package declaration
package ch.nolix.systemtest.elementtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.elementtest.multistateelementtest.MultiStateElementTestPool;

//class
public final class ElementTestPool extends TestPool {
	
	//constructor
	public ElementTestPool() {
		super(new MultiStateElementTestPool());
	}
}
