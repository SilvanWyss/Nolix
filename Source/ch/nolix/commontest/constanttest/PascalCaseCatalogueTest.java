//package declaration
package ch.nolix.commontest.constanttest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.commontypehelper.StringHelper;
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.reflectionhelper.GlobalClassHelper;
import ch.nolix.common.test.Test;

//class
public final class PascalCaseCatalogueTest extends Test {
	
	//method
	@TestCase
	public void testCase_constants() {
		
		//setup
		final var exceptions = LinkedList.withElements(PascalCaseCatalogue.GUI, PascalCaseCatalogue.URL);
		
		//verification
		for (final var c : GlobalClassHelper.getPublicStaticFieldValuesOfClass(PascalCaseCatalogue.class)) {
			
			expect(c).isOfType(String.class);
			
			final var stringValue = c.toString();
			if (!exceptions.containsEqualing(stringValue)) {
				expect(stringValue).fulfils(StringHelper::isPascalCase);
			}
		}
	}
}
