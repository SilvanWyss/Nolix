//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.ContainerMediator;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerBooleanGetter;

//class
public final class ContainerMediatorTest extends Test {
	
	//method
	@TestCase
	public void testCase_contains_whenTheGivenConditionIsNull() {
		
		//setup
		final var list = ImmutableList.withElement("ax", "ax", "bx", "bx", "cx", "cx", "dx", "dx");
		final IElementTakerBooleanGetter<String> condition = null;
		final var testUnit = new ContainerMediator<>(list);
		
		//execution & verification
		expectRunning(() -> testUnit.contains(condition))
		.throwsException()
		.ofType(ArgumentIsNullException.class)
		.withMessage("The given condition is null.");
	}
	
	//method
	@TestCase
	public void testCase_contains_whenTheGivenArgumentDoesNotContainAWantedElement() {
		
		//setup
		final var list = ImmutableList.withElement("ax", "ax", "bx", "bx", "cx", "cx", "dx", "dx");
		final var testUnit = new ContainerMediator<>(list);
		
		//execution & verification
		expectRunning(() -> testUnit.contains(e -> e.startsWith("e")))
		.throwsException()
		.ofType(InvalidArgumentException.class)
		.withMessage(
			"The given ImmutableList 'ax,ax,bx,bx,cx,cx,dx,dx' does not contain "
			+ "an element that fulfils the given condition."
		);
	}
	
	//method
	@TestCase
	public void testCase_contains_whenTheGivenArgumentContainsAWantedElement() {
		
		//setup
		final var list = ImmutableList.withElement("ax", "ax", "bx", "bx", "cx", "cx", "dx", "dx");
		final var testUnit = new ContainerMediator<>(list);
		
		//execution & verification
		expectRunning(() -> testUnit.contains(e -> e.startsWith("c"))).doesNotThrowException();
	}
}
