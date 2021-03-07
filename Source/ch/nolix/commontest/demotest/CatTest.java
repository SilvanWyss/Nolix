//package declaration
package ch.nolix.commontest.demotest;

import ch.nolix.common.demo.Cat;
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.Test;

//class
public final class CatTest extends Test {
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//execution
		final var testUnit = new Cat("Garfield");
		
		//verification
		expect(testUnit.getName()).isEqualTo("Garfield");
	}
}
