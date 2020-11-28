//package declaration
package ch.nolix.nolixlicensetest;

import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.test.Test;
import ch.nolix.nolixlicense.Nolix2020Classic;

//class
public final class Nolix2020ClassicTest extends Test {
	
	//method
	@TestCase
	public void testCase_creation_whenTheKeyIsNotValid() {
		
		//execution & verification
		expect(() -> new Nolix2020Classic("000000"))
		.throwsException().ofType(InvalidArgumentException.class)
		.withMessage("The given key '000000' is not valid.");
	}
}
