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
	public void testCase_contains_withI2ElementTakerBooleanGetter1A() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.containsAny((e1, e2) -> e1.length() == e2.length());
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_contains_withI2ElementTakerBooleanGetter1B() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.containsAny((e1, e2) -> e1.length() == e2.length() + 1);
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_contains_withI2ElementTakerBooleanGetter1C() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.containsAny((e1, e2) -> e1.length() == e2.length() + 2);
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_contains_withI2ElementTakerBooleanGetter1D() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.containsAny((e1, e2) -> e1.length() == e2.length() + 3);
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_contains_withI2ElementTakerBooleanGetter1E() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.containsAny((e1, e2) -> e1.length() == e2.length() + 4);
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_contains_withI2ElementTakerBooleanGetter1F() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.containsAny((e1, e2) -> e1.length() == e2.length() + 5);
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_contains_withI2ElementTakerBooleanGetter1G() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.containsAny((e1, e2) -> e1.length() == e2.length() + 6);
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public void testCase_containsOne_1A() {
		
		//setup
		final var testUnit = createEmptyContainerOfType(String.class);
		
		//execution
		final var result = testUnit.containsOne();
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public void testCase_containsOne_1B() {
		
		//setup
		final var testUnit = createContainerWithElements("x");
		
		//execution
		final var result = testUnit.containsOne();
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_containsOne_1C() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx");
		
		//execution
		final var result = testUnit.containsOne();
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public void testCase_containsOne_ElementTakerBooleanGetter1A() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx", "xx", "xx", "xx", "xx");
		
		//execution
		final var result = testUnit.containsOne(e -> e.equals("x"));
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_containsOne_ElementTakerBooleanGetter1B() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "x", "xx", "xx", "xx", "xx");
		
		//execution
		final var result = testUnit.containsOne(e -> e.equals("x"));
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public void testCase_from() {
		
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
	public void testCase_getCount_withIElementTakterBooleanGetter_1A() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getCount(e -> e.length() > 0);
		
		//verification
		expect(result).isEqualTo(6);
	}
	
	//method
	@TestCase
	public void testCase_getCount_withIElementTakterBooleanGetter_1B() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getCount(e -> e.length() > 1);
		
		//verification
		expect(result).isEqualTo(5);
	}
	
	//method
	@TestCase
	public void testCase_getCount_withIElementTakterBooleanGetter_1C() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getCount(e -> e.length() > 2);
		
		//verification
		expect(result).isEqualTo(4);
	}
	
	//method
	@TestCase
	public void testCase_gettCount_withIElementTakterBooleanGetter_1D() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getCount(e -> e.length() > 3);
		
		//verification
		expect(result).isEqualTo(3);
	}
	
	//method
	@TestCase
	public void testCase_getCount_withIElementTakterBooleanGetter_1E() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getCount(e -> e.length() > 4);
		
		//verification
		expect(result).isEqualTo(2);
	}
	
	//method
	@TestCase
	public void testCase_getCount_withIElementTakterBooleanGetter_1F() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getCount(e -> e.length() > 5);
		
		//verification
		expect(result).isEqualTo(1);
	}
	
	//method
	@TestCase
	public void testCase_getCount_withIElementTakterBooleanGetter_1G() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getCount(e -> e.length() > 6);
		
		//verification
		expect(result).isEqualTo(0);
	}
	
	//method
	@TestCase
	public void testCase_getElementCount() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "x", "x", "x", "x", "x");
		
		//execution & verification
		expect(testUnit.getElementCount()).isEqualTo(6);
	}
	
	//method
	@TestCase
	public void testCase_getElementCount_whenLinkedListIsEmpty() {
		
		//setup
		final var testUnit = createEmptyContainerOfType(String.class);
		
		//execution & verification
		expect(testUnit.getElementCount()).isEqualTo(0);
	}
	
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
	
	@TestCase
	public void testCase_getRefByMaxInt() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getRefByMaxInt(String::length);
		
		//verification
		expect(result).isEqualTo("xxxxxx");
	}
	
	//method
	@TestCase
	public void testCase_getRefByMinInt() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getRefByMinInt(String::length);
		
		//verification
		expect(result).isEqualTo("x");
	}
	
	//method
	@TestCase
	public void testCase_getRefFirst() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getRefFirst();
		
		//verification
		expect(result).isEqualTo("x");
	}
	
	//method
	@TestCase
	public void testCase_getRefFirst_whenLinkedListIsEmpty() {
		
		//setup
		final var testUnit = createEmptyContainerOfType(String.class);
		
		//execution & verification
		expectRunning(testUnit::getRefFirst)
		.throwsException()
		.ofType(EmptyArgumentException.class)
		.withMessage("The given " + testUnit.getClass().getSimpleName() +" is empty.");
	}
	
	//method
	@TestCase
	public void testCase_getRefSelected_1A() {
		
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
	public void testCase_getRefSelected_1B() {
		
		//setup
		final var testUnit = createContainerWithElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getRefSelected(e -> e.length() > 6);
		
		//verification
		expect(result.isEmpty());
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
