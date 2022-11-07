//package declaration
package ch.nolix.coretest.errorcontroltest.invalidargumentexceptiontest;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class InvalidArgumentExceptionTest extends Test {
	
	@TestCase
	public void testCase_forArgument_whenArgumentIsNull() {
		
		//execution
		final var result = InvalidArgumentException.forArgument(null);
		
		//verification
		expect(result.getArgumentName()).isEqualTo("argument");
		expect(result.getRefArgument()).isNull();
		expect(result.getErrorPredicate()).isEqualTo("is not valid");
		expect(result.getMessage()).isEqualTo("The given argument is not valid.");
	}
	
	@TestCase
	public void testCase_forArgument_whenArgumentIsANode() {
		
		//setup
		final var node = Node.fromString("Parking(Slot(Id(A)), Slot(Id(B)))");
		
		//execution
		final var result = InvalidArgumentException.forArgument(node);
		
		//verification
		expect(result.getArgumentName()).isEqualTo("Node");
		expect(result.getRefArgument()).is(node);
		expect(result.getErrorPredicate()).isEqualTo("is not valid");
		expect(result.getMessage()).isEqualTo("The given Node 'Parking(Slot(Id(A)), Slot(Id(B)))' is not valid.");
	}
}
