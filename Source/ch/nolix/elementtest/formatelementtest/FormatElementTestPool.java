//package declaration
package ch.nolix.elementtest.formatelementtest;

import ch.nolix.core.testing.basetest.TestPool;

//class
public final class FormatElementTestPool extends TestPool {
	
	//constructor
	public FormatElementTestPool() {
		super(FormatElementWithCascadingPropertyTest.class, FormatElementWithNonCascadingPropertyTest.class);
	}
}
