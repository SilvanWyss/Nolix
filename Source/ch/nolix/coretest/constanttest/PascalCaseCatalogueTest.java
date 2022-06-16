//package declaration
package ch.nolix.coretest.constanttest;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.core.reflection.GlobalClassHelper;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

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
			if (!exceptions.containsAnyEqualing(stringValue)) {
				expect(stringValue).fulfils(GlobalStringHelper::isPascalCase);
			}
		}
	}
}
