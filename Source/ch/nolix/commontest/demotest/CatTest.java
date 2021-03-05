//package declaration
package ch.nolix.commontest.demotest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.demo.Cat;
import ch.nolix.common.test.Test;

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
