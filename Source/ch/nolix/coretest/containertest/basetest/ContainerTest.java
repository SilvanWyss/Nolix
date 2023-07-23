//package declaration
package ch.nolix.coretest.containertest.basetest;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.function.FunctionCatalogue;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//class
public abstract class ContainerTest extends Test {
	
	//method
	@TestCase
	public final void testCase_contains_whenContainerContainsGivenElement() {
		
		//setup
		final var string1 = "x";
		final var string2 = "xx";
		final var string3 = "xxx";
		final var string4 = "xxxx";
		final var testUnit = createContainerWithElements(string1, string2, string3, string4);
		
		//execution
		final var result = testUnit.contains(string4);
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public final void testCase_contains_whenContainerDoesNotContainGivenElement() {
		
		//setup
		final var string1 = "x";
		final var string2 = "xx";
		final var string3 = "xxx";
		final var string4 = "xxxx";
		final var testUnit = createContainerWithElements(string1, string2, string3);
		
		//execution
		final var result = testUnit.contains(string4);
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public final void testCase_containsAll_whenContainsAllOfTheGivenElementsAndOtherElements() {
		
		//setup
		final var element1 = "x";
		final var element2 = "xx";
		final var element3 = "xxx";
		final var element4 = "xxxx";
		final var container = createContainerWithElements(element1, element2, element3);
		final var testUnit = createContainerWithElements(element1, element2, element3, element4);
		
		//execution
		final var result = testUnit.containsAll(container);
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public final void testCase_containsAll_whenContainsNoneOfTheGivenElementsButOtherElements() {
		
		//setup
		final var element1 = "x";
		final var element2 = "xx";
		final var element3 = "xxx";
		final var element4 = "xxxx";
		final var container = createContainerWithElements(element1, element2);
		final var testUnit = createContainerWithElements(element3, element4);
		
		//execution
		final var result = testUnit.containsAll(container);
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public final void testCase_containsAll_whenContainsOnlySomeOfTheGivenElementsButOtherElements() {
		
		//setup
		final var element1 = "x";
		final var element2 = "xx";
		final var element3 = "xxx";
		final var element4 = "xxxx";
		final var container = createContainerWithElements(element1, element2, element3);
		final var testUnit = createContainerWithElements(element2, element3, element4);
		
		//execution
		final var result = testUnit.containsAll(container);
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public final void testCase_containsAny_whenIsEmpty() {
		
		//setup
		final var element1 = "x";
		final var element2 = "xx";
		final var testUnit = createEmptyContainerForType(String.class);
		
		//execution
		final var result = testUnit.containsAny(element1, element2);
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public final void testCase_containsAny_whenContainsGivenElementsAndMore() {
		
		//setup
		final var element1 = "x";
		final var element2 = "xx";
		final var element3 = "xxx";
		final var element4 = "xxxx";
		final var testUnit = createContainerWithElements(element1, element2, element3, element4);
		
		//execution
		final var result = testUnit.containsAny(element1, element2, element3, element4);
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public final void testCase_containsAny_whenContainsOtherElementsOnly() {
		
		//setup
		final var element1 = "x";
		final var element2 = "xx";
		final var element3 = "xxx";
		final var element4 = "xxxx";
		final var testUnit = createContainerWithElements(element1, element2);
		
		//execution
		final var result = testUnit.containsAny(element3, element4);
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public final void testCase_containsExactlyInSameOrder_whenIsEmptyAndGivenContainerIsEmpty() {
		
		//setup
		final var testUnit = createEmptyContainerForType(String.class);
		final var container = createEmptyContainerForType(String.class);
		
		//execution
		final var result = testUnit.containsExactlyInSameOrder(container);
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public final void
	testCase_containsExactlyInSameOrder_whenContainsElementsAndGivenContainerContainsSameElementsInSameOrder() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		final var container = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.containsExactlyInSameOrder(container);
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public final void
	testCase_containsExactlyInSameOrder_whenContainsElementsAndGivenContainerContainsSameElementsInOtherOrder() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		final var container = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxxx", "xxxxx");
		
		//execution
		final var result = testUnit.containsExactlyInSameOrder(container);
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public final void testCase_containsOne_1A() {
		
		//setup
		final var testUnit = createEmptyContainerForType(String.class);
		
		//execution
		final var result = testUnit.containsOne();
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public final void testCase_containsOne_1B() {
		
		//setup
		final var testUnit = createContainerWithElements("x");
		
		//execution
		final var result = testUnit.containsOne();
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public final void testCase_containsOne_1C() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx");
		
		//execution
		final var result = testUnit.containsOne();
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public final void testCase_containsOne_ElementTakerBooleanGetter1A() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xx", "xx", "xx", "xx");
		
		//execution
		final var result = testUnit.containsOne(e -> e.equals("x"));
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public final void testCase_containsOne_ElementTakerBooleanGetter1B() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "x", "xx", "xx", "xx", "xx");
		
		//execution
		final var result = testUnit.containsOne(e -> e.equals("x"));
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public final void testCase_forEach() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		final var list = new LinkedList<String>();
		
		//execution
		testUnit.forEach(list::addAtEnd);
		
		//verification
		expect(list.getElementCount()).isEqualTo(6);
		for (var i = 1; i <= 6; i++) {
			expect(testUnit.getStoredAt1BasedIndex(i)).isEqualTo(list.getStoredAt1BasedIndex(i));
		}
	}
	
	//method
	@TestCase
	public final void testCase_from() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.from1BasedStartIndex(4);
		
		//verification
		expect(result.getElementCount()).isEqualTo(3);
		expect(result.getStoredAt1BasedIndex(1)).isEqualTo("xxxx");
		expect(result.getStoredAt1BasedIndex(2)).isEqualTo("xxxxx");
		expect(result.getStoredAt1BasedIndex(3)).isEqualTo("xxxxxx");
	}
	
	//method
	@TestCase
	public final void testCase_getAverage_whenIsEmpty() {
		
		//setup
		final var testUnit = createEmptyContainerForType(Double.class);
		
		//execution & verification
		expectRunning(() -> testUnit.getAverage(FunctionCatalogue::getSelf))
		.throwsException()
		.ofType(EmptyArgumentException.class)
		.withMessage("The given " + testUnit.getClass().getSimpleName() + " is empty.");
	}
	
	//method
	@TestCase
	public final void testCase_getAverage_whenContainsAny() {
		
		//setup
		final var testUnit = createContainerWithElements(5.0, 10.0, 15.0, 20.0, 25.0, 30.0);
		
		//execution
		final var result = testUnit.getAverage(FunctionCatalogue::getSelf);
		
		//verification
		expect(result).isEqualTo(17.5);
	}
	
	//method
	@TestCase
	public final void testCase_getAverageOrZero_whenIsEmpty() {
		
		//setup
		final var testUnit = createEmptyContainerForType(Double.class);
		
		//execution
		final var result = testUnit.getAverageOrZero(FunctionCatalogue::getSelf);
		
		//verification
		expect(result).isEqualTo(0.0);
	}
	
	//method
	@TestCase
	public final void testCase_getAverageOrZero_whenContainsAny() {
		
		//setup
		final var testUnit = createContainerWithElements(5.0, 10.0, 15.0, 20.0, 25.0, 30.0);
		
		//execution
		final var result = testUnit.getAverageOrZero(FunctionCatalogue::getSelf);
		
		//verification
		expect(result).isEqualTo(17.5);
	}
	
	//method
	@TestCase
	public final void testCase_getCount_withIElementTakterBooleanGetter_1A() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.getCount(e -> e.length() > 0);
		
		//verification
		expect(result).isEqualTo(6);
	}
	
	//method
	@TestCase
	public final void testCase_getCount_withIElementTakterBooleanGetter_1B() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.getCount(e -> e.length() > 1);
		
		//verification
		expect(result).isEqualTo(5);
	}
	
	//method
	@TestCase
	public final void testCase_getCount_withIElementTakterBooleanGetter_1C() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.getCount(e -> e.length() > 2);
		
		//verification
		expect(result).isEqualTo(4);
	}
	
	//method
	@TestCase
	public final void testCase_gettCount_withIElementTakterBooleanGetter_1D() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.getCount(e -> e.length() > 3);
		
		//verification
		expect(result).isEqualTo(3);
	}
	
	//method
	@TestCase
	public final void testCase_getCount_withIElementTakterBooleanGetter_1E() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.getCount(e -> e.length() > 4);
		
		//verification
		expect(result).isEqualTo(2);
	}
	
	//method
	@TestCase
	public final void testCase_getCount_withIElementTakterBooleanGetter_1F() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.getCount(e -> e.length() > 5);
		
		//verification
		expect(result).isEqualTo(1);
	}
	
	//method
	@TestCase
	public final void testCase_getCount_withIElementTakterBooleanGetter_1G() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.getCount(e -> e.length() > 6);
		
		//verification
		expect(result).isEqualTo(0);
	}
	
	//method
	@TestCase
	public final void testCase_getElementCount() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "x", "x", "x", "x", "x");
		
		//execution & verification
		expect(testUnit.getElementCount()).isEqualTo(6);
	}
	
	//method
	@TestCase
	public final void testCase_getElementCount_whenLinkedListIsEmpty() {
		
		//setup
		final var testUnit = createEmptyContainerForType(String.class);
		
		//execution & verification
		expect(testUnit.getElementCount()).isEqualTo(0);
	}
	
	//method
	@TestCase
	public final void testCase_get1BasedIndexOfFirst_whenIsEmpty() {
		
		//setup
		final var testUnit = createEmptyContainerForType(String.class);
		
		//execution & verification
		expectRunning(() -> testUnit.get1BasedIndexOfFirst(e -> e.startsWith("x")))
		.throwsException()
		.ofType(ArgumentDoesNotContainElementException.class);
	}
	
	//method
	@TestCase
	public final void testCase_get1BasedIndexOfFirst_whenContainsAMatchingElement() {
		
		//setup
		final var testUnit = createContainerWithElements("wx", "xx", "yx", "zx");
		
		//execution
		final var result = testUnit.get1BasedIndexOfFirst(e -> e.startsWith("y"));
		
		//verification
		expect(result).isEqualTo(3);
	}
	
	//method
	@TestCase
	public final void testCase_getMax_whenIsEmptyAndGivenNormIsInteger() {
		
		//setup
		final var testUnit = createEmptyContainerForType(String.class);
		
		//execution
		expectRunning(() -> testUnit.getMax(String::length))
		.throwsException()
		.ofType(EmptyArgumentException.class)
		.withMessage("The given " + testUnit.getClass().getSimpleName() + " is empty.");
	}
	
	//method
	@TestCase
	public final void testCase_getMax_whenContainsSomeAndGivenNormIsDouble() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.getMax(e ->  1.0 / e.length());
		
		//verification
		expect(result).isEqualTo(1.0);
	}
	
	//method
	@TestCase
	public final void testCase_getMax_whenContainsSomeAndGivenNormIsInteger() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.getMax(String::length);
		
		//verification
		expect(result).isEqualTo(6);
	}
	
	//method
	@TestCase
	public final void testCase_getMedian_whenIsEmpty() {
		
		//setup
		final var testUnit = createEmptyContainerForType(Integer.class);
		
		//execution & verification
		expectRunning(() -> testUnit.getMedian(FunctionCatalogue::getSelf))
		.throwsException()
		.ofType(EmptyArgumentException.class);
	}
	
	//method
	@TestCase
	public final void testCase_getMedian_whenContainsAny_1A() {
		
		//setup
		final var testUnit = createContainerWithElements(10, 1, 9, 2, 8, 3, 4);
		
		//execution
		final var result = testUnit.getMedian(FunctionCatalogue::getSelf);
		
		//verification
		expect(result).isEqualTo(4.0);
	}
	
	//method
	@TestCase
	public final void testCase_getMin_whenIsEmptyAndGivenNormIsInteger() {
		
		//setup
		final var testUnit = createEmptyContainerForType(String.class);
		
		//execution
		expectRunning(() -> testUnit.getMin(String::length))
		.throwsException()
		.ofType(EmptyArgumentException.class)
		.withMessage("The given " + testUnit.getClass().getSimpleName() + " is empty.");
	}
	
	//method
	@TestCase
	public final void testCase_getMin_whenContainsSomeAndGivenNormIsDouble() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx");
		
		//execution
		final var result = testUnit.getMin(e ->  1.0 / e.length());
		
		//verification
		expect(result).isEqualTo(0.2);
	}
	
	//method
	@TestCase
	public final void testCase_getMin_whenContainsSomeAndGivenNormIsInteger() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.getMin(String::length);
		
		//verification
		expect(result).isEqualTo(1);
	}
	
	//method
	@TestCase
	public final void testCase_getStoredByMax_whenIsEmptyAndGivenNormIsInteger() {
		
		//setup
		final var testUnit = createEmptyContainerForType(String.class);
		
		//execution
		expectRunning(() -> testUnit.getStoredByMax(String::length))
		.throwsException()
		.ofType(EmptyArgumentException.class)
		.withMessage("The given " + testUnit.getClass().getSimpleName() + " is empty.");
	}
	
	//method
	@TestCase
	public final void testCase_getStoredByMax_whenContainsSomeAndGivenNormIsDouble() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.getStoredByMax(e ->  1.0 / e.length());
		
		//verification
		expect(result).isEqualTo("x");
	}
	
	//method
	@TestCase
	public final void testCase_getStoredByMax_whenContainsSomeAndGivenNormIsInteger() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.getStoredByMax(String::length);
		
		//verification
		expect(result).isEqualTo("xxxxxx");
	}
	
	//method
	@TestCase
	public final void testCase_getStoredByMin_whenIsEmptyAndGivenNormIsInteger() {
		
		//setup
		final var testUnit = createEmptyContainerForType(String.class);
		
		//execution
		expectRunning(() -> testUnit.getStoredByMin(String::length))
		.throwsException()
		.ofType(EmptyArgumentException.class)
		.withMessage("The given " + testUnit.getClass().getSimpleName() + " is empty.");
	}
	
	//method
	@TestCase
	public final void testCase_getStoredByMin_whenContainsSomeAndGivenNormIsDouble() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.getStoredByMin(e ->  1.0 / e.length());
		
		//verification
		expect(result).isEqualTo("xxxxxx");
	}
	
	//method
	@TestCase
	public final void testCase_getStoredByMin_whenContainsSomeAndGivenNormIsInteger() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.getStoredByMin(String::length);
		
		//verification
		expect(result).isEqualTo("x");
	}
	
	//method
	@TestCase
	public final void testCase_getStoredFirst() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.getStoredFirst();
		
		//verification
		expect(result).isEqualTo("x");
	}
	
	//method
	@TestCase
	public final void testCase_getStoredFirst_whenLinkedListIsEmpty() {
		
		//setup
		final var testUnit = createEmptyContainerForType(String.class);
		
		//execution & verification
		expectRunning(testUnit::getStoredFirst)
		.throwsException()
		.ofType(EmptyArgumentException.class)
		.withMessage("The given " + testUnit.getClass().getSimpleName() +" is empty.");
	}
	
	//method
	@TestCase
	public final void testCase_getStoredGroups_whenIsEmpty() {
		
		//setup
		final var testUnit = createEmptyContainerForType(String.class);
		
		//execution
		final var result = testUnit.getStoredGroups(String::length);
		
		//verification
		expect(result).isEmpty();
	}
	
	//method
	@TestCase
	public final void testCase_getStoredGroups_1A() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "y", "x", "y", "x", "y");
		
		//execution
		final var result = testUnit.getStoredGroups(String::length);
		
		//verification
		expect(result.getElementCount()).isEqualTo(1);
		expect(result.getStoredOne()).containsExactlyEqualing("x", "y", "x", "y", "x", "y");
	}
	
	//method
	@TestCase
	public final void testCase_getStoredGroups_1B() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "y", "xx", "yy", "xxx", "yyy");
		
		//execution
		final var result = testUnit.getStoredGroups(String::length);
		
		//verification
		expect(result.getElementCount()).isEqualTo(3);
		expect(result.getStoredAt1BasedIndex(1)).containsExactlyEqualing("x", "y");
		expect(result.getStoredAt1BasedIndex(2)).containsExactlyEqualing("xx", "yy");
		expect(result.getStoredAt1BasedIndex(3)).containsExactlyEqualing("xxx", "yyy");
	}
	
	//method
	@TestCase
	public final void testCase_getStoredOne_whenDoesNotContainAMatchingElement() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution & verification
		expectRunning(() -> testUnit.getStoredOne(e -> e.length() == 7))
		.throwsException()
		.ofType(InvalidArgumentException.class)
		.withMessage(
			"The given "
			+ testUnit.getClass().getSimpleName()
			+ " 'x,xx,xxx,xxxx,xxxxx,xxxxxx' does not contain an element the given selector selects."
		);
	}
	
	//method
	@TestCase
	public final void testCase_getStoredOne_whenContainsOneMatchingElement() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.getStoredOne(e -> e.length() == 3);
		
		//verification
		expect(result).isEqualTo("xxx");
	}
	
	//method
	@TestCase
	public final void testCase_getStoredOne_whenContainsSeveralMatchingElements() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "y", "xx", "yy", "xxx", "yyy");
		
		//execution & verification
		expectRunning(() -> testUnit.getStoredOne(e -> e.length() == 3))
		.throwsException()
		.ofType(InvalidArgumentException.class)
		.withMessage(
			"The given "
			+ testUnit.getClass().getSimpleName()
			+ " 'x,y,xx,yy,xxx,yyy' contains several elements the given selector selects."
		);
	}
	
	//method
	@TestCase
	public final void testCase_getStoredSelected_1A() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.getStoredSelected(e -> e.length() < 4);
		
		//verification
		expect(result.getElementCount()).isEqualTo(3);
		expect(result.getStoredAt1BasedIndex(1)).isEqualTo("x");
		expect(result.getStoredAt1BasedIndex(2)).isEqualTo("xx");
		expect(result.getStoredAt1BasedIndex(3)).isEqualTo("xxx");
	}
	
	//method
	@TestCase
	public final void testCase_getStoredSelected_1B() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.getStoredSelected(e -> e.length() > 6);
		
		//verification
		expect(result.isEmpty());
	}
	
	//method
	@TestCase
	public final void testCase_getSumByInt_whenIsEmpty() {
		
		//setup
		final var testUnit = createEmptyContainerForType(String.class);
		
		//execution
		final var result = testUnit.getSum(String::length);
		
		//verification
		expect(result.intValue()).isEqualTo(0);
	}
	
	//method
	@TestCase
	public final void testCase_getSum_whenContainsAny() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.getSum(String::length);
		
		//verification
		expect(result.intValue()).isEqualTo(21);
	}
		
	//method
	@TestCase
	public final void testCase_getVariance() {
		
		//setup
		final var testUnit = createContainerWithElements(0.0, 0.0, 0.5, 1.0, 1.0);
		
		//execution
		final var result = testUnit.getVariance(FunctionCatalogue::getSelf);
		
		//verification
		expect(result).isEqualTo(0.2);
	}
	
	//method
	@TestCase
	public final void testCase_toArray() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.toArray();
		
		//verification
		expect(result.length).isEqualTo(6);
		expect(result[0]).isEqualTo("x");
		expect(result[1]).isEqualTo("xx");
		expect(result[2]).isEqualTo("xxx");
		expect(result[3]).isEqualTo("xxxx");
		expect(result[4]).isEqualTo("xxxxx");
		expect(result[5]).isEqualTo("xxxxxx");
	}
	
	//method
	@TestCase
	public final void testCase_toConcatenatedString_whenIsEmpty() {
		
		//setup
		final var testUnit = createEmptyContainerForType(String.class);
		
		//execution
		final var result = testUnit.toConcatenatedString();
		
		//verification
		expect(result).isEmpty();
	}
	
	//method
	@TestCase
	public final void testCase_toConcatenatedString_whenContainsAny() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "yy", "zzz", "pppp");
		
		//execution
		final var result = testUnit.toConcatenatedString();
		
		//verification
		expect(result).isEqualTo("xyyzzzpppp");
	}
	
	//method
	@TestCase
	public final void testCase_toOrderedList_1A() {
		
		//setup
		final var testUnit = createContainerWithElements("xxxxxx", "xxxxx", "xxxx","xxx", "xx", "x");
		
		//execution
		final var result = testUnit.toOrderedList(String::length);
		
		//verification
		expect(result.getElementCount()).isEqualTo(6);
		expect(result.getStoredAt1BasedIndex(1)).isEqualTo("x");
		expect(result.getStoredAt1BasedIndex(2)).isEqualTo("xx");
		expect(result.getStoredAt1BasedIndex(3)).isEqualTo("xxx");
		expect(result.getStoredAt1BasedIndex(4)).isEqualTo("xxxx");
		expect(result.getStoredAt1BasedIndex(5)).isEqualTo("xxxxx");
		expect(result.getStoredAt1BasedIndex(6)).isEqualTo("xxxxxx");
	}
	
	//method
	@TestCase
	public final void testCase_toOrderedList_1B() {
		
		//setup
		final var testUnit = createContainerWithElements("python", "elephant", "zebra", "lion", "shark", "jaguar");
		
		//execution
		final var result = testUnit.toOrderedList(FunctionCatalogue::getSelf);
		
		//verification
		expect(result.getElementCount()).isEqualTo(6);
		expect(result.getStoredAt1BasedIndex(1)).isEqualTo("elephant");
		expect(result.getStoredAt1BasedIndex(2)).isEqualTo("jaguar");
		expect(result.getStoredAt1BasedIndex(3)).isEqualTo("lion");
		expect(result.getStoredAt1BasedIndex(4)).isEqualTo("python");
		expect(result.getStoredAt1BasedIndex(5)).isEqualTo("shark");
		expect(result.getStoredAt1BasedIndex(6)).isEqualTo("zebra");
	}
	
	//method
	@TestCase
	public final void testCase_toIntArray() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.toIntArray(String::length);
		
		//verification
		expect(result.length).isEqualTo(6);
		expect(result[0]).isEqualTo(1);
		expect(result[1]).isEqualTo(2);
		expect(result[2]).isEqualTo(3);
		expect(result[3]).isEqualTo(4);
		expect(result[4]).isEqualTo(5);
		expect(result[5]).isEqualTo(6);
	}
	
	//method
	@TestCase
	public final void testCase_toStringArray_whenIsEmpty() {
		
		//setup
		final var testUnit = createEmptyContainerForType(Integer.class);
		
		//execution
		final var result = testUnit.toStringArray();
		
		//verification
		expect(result.length).isEqualTo(0);
	}
	
	//method
	@TestCase
	public final void testCase_toStringArray_whenContainsElements() {
		
		//setup
		final var testUnit = createContainerWithElements(10, 20, 30, 40);
		
		//execution
		final var result = testUnit.toStringArray();
		
		//verification
		expect(result.length).isEqualTo(4);
		expect(result[0]).isEqualTo("10");
		expect(result[1]).isEqualTo("20");
		expect(result[2]).isEqualTo("30");
		expect(result[3]).isEqualTo("40");
	}
	
	//method
	@TestCase
	public final void testCase_toStrings() {
		
		//setup
		final var testUnit = createContainerWithElements(10, 20, 30, 40, 50, 60);
		
		//execution
		final var result = testUnit.toStrings();
		
		//verification
		expect(result.getElementCount()).isEqualTo(6);
		expect(result.getStoredAt1BasedIndex(1)).isEqualTo("10");
		expect(result.getStoredAt1BasedIndex(2)).isEqualTo("20");
		expect(result.getStoredAt1BasedIndex(3)).isEqualTo("30");
		expect(result.getStoredAt1BasedIndex(4)).isEqualTo("40");
		expect(result.getStoredAt1BasedIndex(5)).isEqualTo("50");
		expect(result.getStoredAt1BasedIndex(6)).isEqualTo("60");
	}
	
	//method
	@TestCase
	public final void testCase_toStrings_whenContainerIsEmpty() {
		
		//setup
		final var testUnit = createEmptyContainerForType(Integer.class);
		
		//execution
		final var result = testUnit.toStrings();
		
		//verifications
		expect(result).isEmpty();
	}
	
	//method
	@TestCase
	public final void testCase_until() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.until1BasedIndex(5);
		
		//verification
		expect(result.getElementCount()).isEqualTo(5);
		expect(result.getStoredAt1BasedIndex(1)).isEqualTo("x");
		expect(result.getStoredAt1BasedIndex(2)).isEqualTo("xx");
		expect(result.getStoredAt1BasedIndex(3)).isEqualTo("xxx");
		expect(result.getStoredAt1BasedIndex(4)).isEqualTo("xxxx");
		expect(result.getStoredAt1BasedIndex(5)).isEqualTo("xxxxx");
	}
	
	//method
	@TestCase
	public final void testCase_withElements() {
		
		//execution
		final var result = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution & verification part 1
		expect(
			result.containsAny(s -> s.equals("x")),
			result.containsAny(s -> s.equals("xx")),
			result.containsAny(s -> s.equals("xxx")),
			result.containsAny(s -> s.equals("xxxx")),
			result.containsAny(s -> s.equals("xxxxx")),
			result.containsAny(s -> s.equals("xxxxxx"))
		);
		
		//execution & verification part 2
		expectNot(
			result.containsAny(s -> s.equals("xxxxxxx")),
			result.containsAny(s -> s.equals("xxxxxxxx")),
			result.containsAny(s -> s.equals("xxxxxxxxx")),
			result.containsAny(s -> s.equals("xxxxxxxxxx")),
			result.containsAny(s -> s.equals("xxxxxxxxxxx")),
			result.containsAny(s -> s.equals("xxxxxxxxxxxx"))
		);
	}
	
	//method
	@TestCase
	public final void testCase_withoutFirst() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
		
		//execution
		final var result = testUnit.withoutFirst();
		
		//verification
		expect(result.getElementCount()).isEqualTo(5);
		expect(result.getStoredAt1BasedIndex(1)).isEqualTo("xx");
		expect(result.getStoredAt1BasedIndex(2)).isEqualTo("xxx");
		expect(result.getStoredAt1BasedIndex(3)).isEqualTo("xxxx");
		expect(result.getStoredAt1BasedIndex(4)).isEqualTo("xxxxx");
		expect(result.getStoredAt1BasedIndex(5)).isEqualTo("xxxxxx");
	}
	
	//method declaration
	protected abstract <E> IContainer<E> createContainerWithElements(
		E element,
		@SuppressWarnings("unchecked")E... elements
	);
	
	//method declaration
	protected abstract <E> IContainer<E> createEmptyContainerForType(Class<E> type);
}
