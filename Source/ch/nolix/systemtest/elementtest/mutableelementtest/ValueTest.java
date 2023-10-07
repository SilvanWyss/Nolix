//package declaration
package ch.nolix.systemtest.elementtest.mutableelementtest;

//own imports
import ch.nolix.core.programatom.function.FunctionCatalogue;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.element.property.Value;

//class
public final class ValueTest extends Test {
	
	//method
	@TestCase
	public void testCase_forInt() {
		
		//execution
		final var result = Value.forInt("amount", FunctionCatalogue::takeObjectAndDoNothing);
		
		//verification
		expect(result.getName()).isEqualTo("amount");
		expectNot(result.containsAny());
	}
	
	//method
	@TestCase
	public void testCase_getSpecification() {
		
		//setup
		final var testUnit = Value.forInt("amount", FunctionCatalogue::takeObjectAndDoNothing);
		testUnit.setValue(500);
		
		//execution
		final var result = testUnit.getSpecification();
		
		//verification
		expect(result).hasStringRepresentation("amount(500)");
	}
}
