//package declaration
package ch.nolix.coretest.constanttest;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.reflectionhelper.GlobalClassHelper;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

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
			if (!exceptions.containsAnyEqualing(stringValue)) {
				expect(stringValue).fulfils(GlobalStringHelper::isLowerCase);
			}
		}
	}
}
