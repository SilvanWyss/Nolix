//package declaration
package ch.nolix.coretest.constanttest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class ConstantTestPool extends TestPool {
	
	//constructor
	public ConstantTestPool() {
		super(
			FileExtensionCatalogueTest.class,
			StringCatalogueTest.class
		);
	}
}
