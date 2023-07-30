//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.core.testing.validation.MultiLongMediator;

//class
public final class MultiLongMediatorTest extends Test {
	
	//method
	@TestCase
	public void testCase_arePositive_whenTheGivenValuesArePositive() {
		
		//setup
		final var expectationErrors = new LinkedList<String>();
		final var testUnit = new MultiLongMediator(expectationErrors::addAtEnd, new int[]{10, 20, 30, 40, 50, 60});
		
		//setup verification
		expect(expectationErrors).isEmpty();
		
		//execution
		testUnit.arePositive();
		
		//verification
		expect(expectationErrors).isEmpty();
	}
	
	//method
	@TestCase
	public void testCase_arePositive_whenSomeOfTheGivenValuesAreNotPositive() {
		
		//setup
		final var expectationErrors = new LinkedList<String>();
		final var testUnit = new MultiLongMediator(expectationErrors::addAtEnd, new int[]{10, 20, 30, 40, 50, -60});
		
		//setup verification
		expect(expectationErrors).isEmpty();
		
		//execution
		testUnit.arePositive();
		
		//verification
		expect(expectationErrors)
		.containsExactlyEqualing("Positive values were expected, but the 6th value is not positive.");
	}
}
