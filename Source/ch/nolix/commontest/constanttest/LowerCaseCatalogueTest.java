//package declaration
package ch.nolix.commontest.constanttest;

import ch.nolix.common.commontype.commontypehelper.StringHelper;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.reflectionhelper.GlobalClassHelper;
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.Test;

//class
public final class LowerCaseCatalogueTest extends Test {
	
	//method
	@TestCase
	public void testCase_constants() {
		
		//setup
		final var exceptions = LinkedList.withElements(LowerCaseCatalogue.GUI, LowerCaseCatalogue.URL);
		
		//verification
		for (final var c : GlobalClassHelper.getPublicStaticFieldValuesOfClass(LowerCaseCatalogue.class)) {
			
			expect(c).isOfType(String.class);
			
			final var stringValue = c.toString();
			if (!exceptions.containsEqualing(stringValue)) {
				expect(stringValue).fulfils(StringHelper::isLowerCase);
			}
		}
	}
}
