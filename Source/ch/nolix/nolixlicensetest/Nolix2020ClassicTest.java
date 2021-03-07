//package declaration
package ch.nolix.nolixlicensetest;

import ch.nolix.common.errorcontrol.invalidargumentexception.UnacceptedKeyException;
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.Test;
import ch.nolix.nolixlicense.Nolix2020Classic;

//class
public final class Nolix2020ClassicTest extends Test {
	
	//method
	@TestCase
	public void testCase_activate_whenTheKeyIsNotValid() {
		
		//setup
		final var testUnit = new Nolix2020Classic();
		
		//execution & verification
		expectRunning(() -> testUnit.activate("0000-0000"))
		.throwsException()
		.ofType(UnacceptedKeyException.class)
		.withMessage("The given key '0000-0000' is not accepted.");
	}
}
