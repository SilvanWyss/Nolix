//package declaration
package ch.nolix.systemtest.guitest.structurepropertytest;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.systemapi.guiapi.structureproperty.ContentPosition;

//class
public final class ContentPositionTest extends Test {
	
	@TestCase
	public void testCase_fromSpecification() {
		
		//execution
		final var result = ContentPosition.fromSpecification(Node.fromString("Position(CENTER)"));
		
		//verification
		expect(result).isSameAs(ContentPosition.CENTER);
	}
}
