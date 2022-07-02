//package declaration
package ch.nolix.systemtest.guitest.structurepropertytest;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.systemapi.guiapi.structureproperty.DirectionInRectangle;

public final class DirectionInRectangleTest extends Test {
	
	//method
	@TestCase
	public void testCase_getAttributes() {
		
		//setup
		final var testUnit = DirectionInRectangle.HORIZONTAL;
		
		//execution
		final var result = testUnit.getAttributes();
		
		//verification
		expect(result.getElementCount()).isEqualTo(1);
		expect(result.getRefOne()).hasStringRepresentation("HORIZONTAL");
	}
	
	//method
	@TestCase
	public void testCase_getSpecification() {
		
		//setup
		final var testUnit = DirectionInRectangle.HORIZONTAL;
		
		//execution
		final var result = testUnit.getSpecification();
		
		//verification
		expect(result).isEqualTo(Node.fromString("DirectionInRectangle(HORIZONTAL)"));
	}
}
