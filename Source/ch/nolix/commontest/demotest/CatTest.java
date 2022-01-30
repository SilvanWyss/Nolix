//package declaration
package ch.nolix.commontest.demotest;

import ch.nolix.core.demo.Cat;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

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
