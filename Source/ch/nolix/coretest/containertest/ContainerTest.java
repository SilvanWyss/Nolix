//package declaration
package ch.nolix.coretest.containertest;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.programatom.function.FunctionCatalogue;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;

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
	public final void testCase_contains_withI2ElementTakerBooleanGetter1A() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.containsAny((e1, e2) -> e1.length() == e2.length());
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public final void testCase_contains_withI2ElementTakerBooleanGetter1B() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.containsAny((e1, e2) -> e1.length() == e2.length() + 1);
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public final void testCase_contains_withI2ElementTakerBooleanGetter1C() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.containsAny((e1, e2) -> e1.length() == e2.length() + 2);
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public final void testCase_contains_withI2ElementTakerBooleanGetter1D() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.containsAny((e1, e2) -> e1.length() == e2.length() + 3);
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public final void testCase_contains_withI2ElementTakerBooleanGetter1E() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.containsAny((e1, e2) -> e1.length() == e2.length() + 4);
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public final void testCase_contains_withI2ElementTakerBooleanGetter1F() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.containsAny((e1, e2) -> e1.length() == e2.length() + 5);
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public final void testCase_contains_withI2ElementTakerBooleanGetter1G() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.containsAny((e1, e2) -> e1.length() == e2.length() + 6);
		
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
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		final var list = new LinkedList<String>();
		
		//execution
		testUnit.forEach(list::addAtEnd);
		
		//verification
		expect(list.getElementCount()).isEqualTo(6);
		for (var i = 1; i <= 6; i++) {
			expect(testUnit.getRefAt1BasedIndex(i)).isEqualTo(list.getRefAt1BasedIndex(i));
		}
	}
	
	//method
	@TestCase
	public final void testCase_from() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.from(4);
		
		//verification
		expect(result.getElementCount()).isEqualTo(3);
		expect(result.getRefAt1BasedIndex(1)).isEqualTo("xxxx");
		expect(result.getRefAt1BasedIndex(2)).isEqualTo("xxxxx");
		expect(result.getRefAt1BasedIndex(3)).isEqualTo("xxxxxx");
	}
	
	//method
	@TestCase
	public final void testCase_getAverageByDoube_whenIsEmpty() {
		
		//setup
		final var testUnit = createEmptyContainerForType(Double.class);
		
		//execution & verification
		expectRunning(() -> testUnit.getAverageByDouble(FunctionCatalogue::getSelf))
		.throwsException()
		.ofType(EmptyArgumentException.class)
		.withMessage("The given " + testUnit.getClass().getSimpleName() + " is empty.");
	}
	
	//method
	@TestCase
	public final void testCase_getAverageByDoube_whenIsNotEmpty() {
		
		//setup
		final var testUnit = createContainerWithElements(5.0, 10.0, 15.0, 20.0, 25.0, 30.0);
		
		//execution
		final var result = testUnit.getAverageByDouble(FunctionCatalogue::getSelf);
		
		//verification
		expect(result).isEqualTo(17.5);
	}
	
	//method
	@TestCase
	public final void testCase_getCount_withIElementTakterBooleanGetter_1A() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getCount(e -> e.length() > 0);
		
		//verification
		expect(result).isEqualTo(6);
	}
	
	//method
	@TestCase
	public final void testCase_getCount_withIElementTakterBooleanGetter_1B() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getCount(e -> e.length() > 1);
		
		//verification
		expect(result).isEqualTo(5);
	}
	
	//method
	@TestCase
	public final void testCase_getCount_withIElementTakterBooleanGetter_1C() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getCount(e -> e.length() > 2);
		
		//verification
		expect(result).isEqualTo(4);
	}
	
	//method
	@TestCase
	public final void testCase_gettCount_withIElementTakterBooleanGetter_1D() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getCount(e -> e.length() > 3);
		
		//verification
		expect(result).isEqualTo(3);
	}
	
	//method
	@TestCase
	public final void testCase_getCount_withIElementTakterBooleanGetter_1E() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getCount(e -> e.length() > 4);
		
		//verification
		expect(result).isEqualTo(2);
	}
	
	//method
	@TestCase
	public final void testCase_getCount_withIElementTakterBooleanGetter_1F() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getCount(e -> e.length() > 5);
		
		//verification
		expect(result).isEqualTo(1);
	}
	
	//method
	@TestCase
	public final void testCase_getCount_withIElementTakterBooleanGetter_1G() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
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
	public final void testCase_getMax() {
		
		//setup
		final var testUnit = createContainerWithElements(10, 20, 30, 40, 50, 10, 20, 30);
		
		//execution
		final var result = testUnit.getMax(FunctionCatalogue::getSelf);
		
		//verification
		expect(result).isEqualTo(50);
	}
	
	//method
	@TestCase
	public final void testCase_getMax_whenContainerIsEmpty() {
		
		//setup
		final var testUnit = createEmptyContainerForType(Integer.class);
		
		//execution
		expectRunning(() -> testUnit.getMax(FunctionCatalogue::getSelf))
		.throwsException()
		.ofType(EmptyArgumentException.class)
		.withMessage("The given " + testUnit.getClass().getSimpleName() + " is empty.");
	}
	
	@TestCase
	public final void testCase_getMedianByDouble_whenIsEmpty() {
		
		//setup
		final var testUnit = createEmptyContainerForType(Double.class);
		
		//execution & verification
		expectRunning(() -> testUnit.getMedianByDouble(FunctionCatalogue::getSelf))
		.throwsException()
		.ofType(EmptyArgumentException.class);
	}
	
	@TestCase
	public final void testCase_getMedianByDouble_whenContainsAny_1A() {
		
		//setup
		final var testUnit = createContainerWithElements(1.0, 2.0, 3.0, 4.0, 5.0);
		
		//execution
		final var result = testUnit.getMedianByDouble(FunctionCatalogue::getSelf);
		
		//verification
		expect(result).isEqualTo(3.0);
	}
	
	@TestCase
	public final void testCase_getMedianByDouble_whenContainsAny_1B() {
		
		//setup
		final var testUnit = createContainerWithElements(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
		
		//execution
		final var result = testUnit.getMedianByDouble(FunctionCatalogue::getSelf);
		
		//verification
		expect(result).isEqualTo(3.5);
	}
	
	@TestCase
	public final void testCase_getMedianByDouble_whenContainsAny_2() {
		
		//setup
		final var testUnit = createContainerWithElements(1.0, 1.0, 1.0, 1.0, 1.0, 1.0);
		
		//execution
		final var result = testUnit.getMedianByDouble(FunctionCatalogue::getSelf);
		
		//verification
		expect(result).isEqualTo(1.0);
	}
	
	@TestCase
	public final void testCase_getRefByMaxInt() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getRefByMaxInt(String::length);
		
		//verification
		expect(result).isEqualTo("xxxxxx");
	}
	
	//method
	@TestCase
	public final void testCase_getRefByMinInt() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getRefByMinInt(String::length);
		
		//verification
		expect(result).isEqualTo("x");
	}
	
	//method
	@TestCase
	public final void testCase_getRefFirst() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getRefFirst();
		
		//verification
		expect(result).isEqualTo("x");
	}
	
	//method
	@TestCase
	public final void testCase_getRefFirst_whenLinkedListIsEmpty() {
		
		//setup
		final var testUnit = createEmptyContainerForType(String.class);
		
		//execution & verification
		expectRunning(testUnit::getRefFirst)
		.throwsException()
		.ofType(EmptyArgumentException.class)
		.withMessage("The given " + testUnit.getClass().getSimpleName() +" is empty.");
	}
	
	//method
	@TestCase
	public final void testCase_getRefFirst_withI2ElementTakerBooleanGetter() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getRefFirst((e1, e2) -> e1.length() + 2 == e2.length());
		
		//verification
		expect(result.getRefElement1()).isEqualTo("x");
		expect(result.getRefElement2()).isEqualTo("xxx");
	}
	
	//method
	@TestCase
	public final void testCase_getRefSelected_1A() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getRefSelected(e -> e.length() < 4);
		
		//verification
		expect(result.getElementCount()).isEqualTo(3);
		expect(result.getRefAt1BasedIndex(1)).isEqualTo("x");
		expect(result.getRefAt1BasedIndex(2)).isEqualTo("xx");
		expect(result.getRefAt1BasedIndex(3)).isEqualTo("xxx");
	}
	
	//method
	@TestCase
	public final void testCase_getRefSelected_1B() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getRefSelected(e -> e.length() > 6);
		
		//verification
		expect(result.isEmpty());
	}
	
	//method
	@TestCase
	public final void testCase_getSumByInt() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getSumByInt(String::length);
		
		//verification
		expect(result).isEqualTo(21);
	}
	
	//method
	@TestCase
	public final void testCase_getSumByInt_whenContainerIsEmpty() {
		
		//setup
		final var testUnit = createEmptyContainerForType(String.class);
		
		//execution
		final var result = testUnit.getSumByInt(String::length);
		
		//verification
		expect(result).isEqualTo(0);
	}
	
	//method
	@TestCase
	public final void testCase_getVarianceByDouble() {
		
		//setup
		final var testUnit = createContainerWithElements(0.0, 0.0, 0.5, 1.0, 1.0);
		
		//execution
		final var result = testUnit.getVarianceByDouble(FunctionCatalogue::getSelf);
		
		//verification
		expect(result).isEqualTo(0.2);
	}
	
	//method
	@TestCase
	public final void testCase_toArrayOfType() {
		
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
		expect(result[0]).is(string1);
		expect(result[1]).is(string2);
		expect(result[2]).is(string3);
		expect(result[3]).is(string4);
	}
	
	//method
	@TestCase
	public final void testCase_toArray() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
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
	public final void testCase_toOrderedList_1A() {
		
		//setup
		final var testUnit = createContainerWithElements("xxxxxx", "xxxxx", "xxxx","xxx", "xx", "x");
		
		//execution
		final var result = testUnit.toOrderedList(String::length);
		
		//verification
		expect(result.getElementCount()).isEqualTo(6);
		expect(result.getRefAt1BasedIndex(1)).isEqualTo("x");
		expect(result.getRefAt1BasedIndex(2)).isEqualTo("xx");
		expect(result.getRefAt1BasedIndex(3)).isEqualTo("xxx");
		expect(result.getRefAt1BasedIndex(4)).isEqualTo("xxxx");
		expect(result.getRefAt1BasedIndex(5)).isEqualTo("xxxxx");
		expect(result.getRefAt1BasedIndex(6)).isEqualTo("xxxxxx");
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
		expect(result.getRefAt1BasedIndex(1)).isEqualTo("elephant");
		expect(result.getRefAt1BasedIndex(2)).isEqualTo("jaguar");
		expect(result.getRefAt1BasedIndex(3)).isEqualTo("lion");
		expect(result.getRefAt1BasedIndex(4)).isEqualTo("python");
		expect(result.getRefAt1BasedIndex(5)).isEqualTo("shark");
		expect(result.getRefAt1BasedIndex(6)).isEqualTo("zebra");
	}
	
	//method
	@TestCase
	public final void testCase_toIntArray() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
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
	public final void toStrings() {
		
		//setup
		final var testUnit = createContainerWithElements(10, 20, 30, 40, 50, 60);
		
		//execution
		final var result = testUnit.toStrings();
		
		//verification
		expect(result.getElementCount()).isEqualTo(6);
		expect(result.getRefAt1BasedIndex(1)).isEqualTo("10");
		expect(result.getRefAt1BasedIndex(2)).isEqualTo("20");
		expect(result.getRefAt1BasedIndex(3)).isEqualTo("30");
		expect(result.getRefAt1BasedIndex(4)).isEqualTo("40");
		expect(result.getRefAt1BasedIndex(5)).isEqualTo("50");
		expect(result.getRefAt1BasedIndex(6)).isEqualTo("60");
	}
	
	//method
	@TestCase
	public final void toStrings_whenContainerIsEmpty() {
		
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
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.until(5);
		
		//verification
		expect(result.getElementCount()).isEqualTo(5);
		expect(result.getRefAt1BasedIndex(1)).isEqualTo("x");
		expect(result.getRefAt1BasedIndex(2)).isEqualTo("xx");
		expect(result.getRefAt1BasedIndex(3)).isEqualTo("xxx");
		expect(result.getRefAt1BasedIndex(4)).isEqualTo("xxxx");
		expect(result.getRefAt1BasedIndex(5)).isEqualTo("xxxxx");
	}
	
	//method
	@TestCase
	public final void testCase_withElements() {
		
		//execution
		final var result = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution & verification
			expect(
				result.containsAny(s -> s.equals("x")),
				result.containsAny(s -> s.equals("xx")),
				result.containsAny(s -> s.equals("xxx")),
				result.containsAny(s -> s.equals("xxxx")),
				result.containsAny(s -> s.equals("xxxxx")),
				result.containsAny(s -> s.equals("xxxxxx"))
			);
			
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
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.withoutFirst();
		
		//verification
		expect(result.getElementCount()).isEqualTo(5);
		expect(result.getRefAt1BasedIndex(1)).isEqualTo("xx");
		expect(result.getRefAt1BasedIndex(2)).isEqualTo("xxx");
		expect(result.getRefAt1BasedIndex(3)).isEqualTo("xxxx");
		expect(result.getRefAt1BasedIndex(4)).isEqualTo("xxxxx");
		expect(result.getRefAt1BasedIndex(5)).isEqualTo("xxxxxx");
	}
	
	//method declaration
	protected abstract <E> IContainer<E> createContainerWithElements(@SuppressWarnings("unchecked")E... elements);
	
	//method declaration
	protected abstract <E> IContainer<E> createEmptyContainerForType(Class<E> type);
}
