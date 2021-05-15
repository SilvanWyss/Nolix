//package declaration
package ch.nolix.elementtest.formatelementtest;

//own imports
import ch.nolix.common.testing.basetest.TestPool;

//class
public final class FormatElementTestPool extends TestPool {
	
	//constructor
	public FormatElementTestPool() {
		super(FormatElementWithCascadingPropertyTest.class, FormatElementWithNonCascadingPropertyTest.class);
	}
}
