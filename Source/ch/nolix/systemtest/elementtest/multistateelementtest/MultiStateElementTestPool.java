//package declaration
package ch.nolix.systemtest.elementtest.multistateelementtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class MultiStateElementTestPool extends TestPool {
	
	//constructor
	public MultiStateElementTestPool() {
		super(MultiStateElementWithCascadingPropertyTest.class, MultiStateElementWithNonCascadingPropertyTest.class);
	}
}
