//package declaration
package ch.nolix.nolixLicensesTest;

//own imports
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.test.Test;
import ch.nolix.nolixLicenses.ApplicationUltimate;

//text class
public final class ApplicationUltimateTest extends Test {
	
	//test case
	public void testCase_creation_whenTheKeyIsNotValid() {
		
		//execution & verification
		expect(() -> new ApplicationUltimate("000000"))
		.throwsException().ofType(InvalidArgumentException.class)
		.withMessage("The given key '000000' is not valid.");
	}
}
