//package declaration
package ch.nolix.commontest.constanttest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.commontypehelper.StringHelper;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.reflectionhelper.GlobalClassHelper;
import ch.nolix.common.test.Test;

//class
public final class LowerCaseCatalogueTest extends Test {
	
	//method
	@TestCase
	public void testCase_constants() {
		
		//setup
		final var exceptions = LinkedList.withElements(LowerCaseCatalogue.GUI, LowerCaseCatalogue.URL);
		
		for (final var c : GlobalClassHelper.getPublicStaticFieldValuesOfClass(LowerCaseCatalogue.class)) {
			
			//verification part 1
			expect(c).isOfType(String.class);
			
			//verification part 2
			final var stringValue = c.toString();
			if (!exceptions.containsEqualing(stringValue)) {
				expect(stringValue).fulfils(StringHelper::isLowerCase);
			}
		}
	}
}
