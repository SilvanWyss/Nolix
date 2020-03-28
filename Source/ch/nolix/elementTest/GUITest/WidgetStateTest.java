//package declaration
package ch.nolix.elementTest.GUITest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.test.Test;
import ch.nolix.element.GUI.WidgetState;

//class
public final class WidgetStateTest extends Test {
	
	//method
	@TestCase
	public void testCase_getSpecification_whenHovered() {
		
		//setup
		final var testUnit = WidgetState.HOVERED;
		
		//execution
		final var result = testUnit.getSpecification();
		
		//verification
		expect(result).hasStringRepresentation("WidgetState(Hovered)");
	}
	
	//method
	@TestCase
	public void testCase_getSpecification_whenNormal() {
		
		//setup
		final var testUnit = WidgetState.NORMAL;
		
		//execution
		final var result = testUnit.getSpecification();
		
		//verification
		expect(result).hasStringRepresentation("WidgetState(Normal)");
	}
	
	//method
	@TestCase
	public void testCase_toCamelCaseString_whenHovered() {
		
		//setup
		final var testUnit = WidgetState.HOVERED;
		
		//execution
		final var result = testUnit.toCamelCaseString();
		
		//verification
		expect(result).isEqualTo("Hovered");
	}
	
	//method
	@TestCase
	public void testCase_toCamelCaseString_whenNormal() {
		
		//setup
		final var testUnit = WidgetState.NORMAL;
		
		//execution
		final var result = testUnit.toCamelCaseString();
		
		//verification
		expect(result).isEqualTo("Normal");
	}
}
