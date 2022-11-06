//package declaration
package ch.nolix.coretest.containertest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.programatom.function.FunctionCatalogue;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;

//class
public abstract class ContainerTest extends Test {
	
	//method
	@TestCase
	public void testCase_getMax() {
		
		//setup
		final var testUnit = createContainerWithElements(10, 20, 30, 40, 50, 10, 20, 30);
		
		//execution
		final var result = testUnit.getMax(FunctionCatalogue::getSelf);
		
		//verification
		expect(result).isEqualTo(50);
	}
	
	//method
	@TestCase
	public void testCase_getMax_whenContainerIsEmpty() {
		
		//setup
		final var testUnit = createEmptyContainerOfType(Integer.class);
		
		//execution
		expectRunning(() -> testUnit.getMax(FunctionCatalogue::getSelf))
		.throwsException()
		.ofType(EmptyArgumentException.class)
		.withMessage("The given " + testUnit.getClass().getSimpleName() + " is empty.");
	}
	
	//method
	@TestCase
	public void testCase_toArrayOfType() {
		
		//setup
		final var string1 = "x";
		final var string2 = "xx";
		final var string3 = "xxx";
		final var string4 = "xxxx";
		final var testUnit = createContainerWithElements(string1, string2, string3, string4);
		
		//execution
		final var result = testUnit.toArrayOfType(String.class);
		
		//verification
		expect(result.length).isEqualTo(4);
		expect(result[0]).isSameAs(string1);
		expect(result[1]).isSameAs(string2);
		expect(result[2]).isSameAs(string3);
		expect(result[3]).isSameAs(string4);
	}
	
	//method declaration
	protected abstract <E> IContainer<E> createContainerWithElements(@SuppressWarnings("unchecked")E... elements);
	
	//method declaration
	protected abstract <E> IContainer<E> createEmptyContainerOfType(Class<E> type);
}
