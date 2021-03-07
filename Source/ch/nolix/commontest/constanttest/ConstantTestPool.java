//package declaration
package ch.nolix.commontest.constanttest;

import ch.nolix.common.testing.basetest.TestPool;

//class
public final class ConstantTestPool extends TestPool {
	
	//constructor
	public ConstantTestPool() {
		super(
			FileExtensionCatalogueTest.class,
			LowerCaseCatalogueTest.class,
			PascalCaseCatalogueTest.class,
			PluralLowerCaseCatalogueTest.class,
			PluralPascalCaseCatalogueTest.class,
			StringCatalogueTest.class
		);
	}
}
