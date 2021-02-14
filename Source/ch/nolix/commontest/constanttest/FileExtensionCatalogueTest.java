//package declaration
package ch.nolix.commontest.constanttest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.constant.FileExtensionCatalogue;
import ch.nolix.common.reflectionhelper.GlobalClassHelper;
import ch.nolix.common.test.Test;

//class
public final class FileExtensionCatalogueTest extends Test {
	
	//method
	@TestCase
	public void testCase_constants() {
		
		//verification
		for (final var c : GlobalClassHelper.getPublicStaticFieldValuesOfClass(FileExtensionCatalogue.class)) {
			expect(c).isOfType(String.class);
		}
	}
}
