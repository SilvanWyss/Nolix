//package declaration
package ch.nolix.coretest.constanttest;

import ch.nolix.core.constant.StringCatalogue;
import ch.nolix.core.reflectionhelper.GlobalClassHelper;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class StringCatalogueTest extends Test {
	
	//method
	@TestCase
	public void testCase_constants() {
		
		//verification
		for (final var c : GlobalClassHelper.getPublicStaticFieldValuesOfClass(StringCatalogue.class)) {
			expect(c).isOfType(String.class);
		}
	}
}
