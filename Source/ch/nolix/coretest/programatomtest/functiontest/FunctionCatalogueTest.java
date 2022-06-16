//package declaration
package ch.nolix.coretest.programatomtest.functiontest;

//own imports
import ch.nolix.core.programatom.function.FunctionCatalogue;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class FunctionCatalogueTest extends Test {
	
	//method
	@TestCase
	public void testCase_getFalse() {
		
		//execution
		final var result = FunctionCatalogue.getFalse();
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public void testCase_getNull() {
		
		//execution
		final var result = FunctionCatalogue.getNull();
		
		//verification
		expect(result).isNull();
	}
	
	//method
	@TestCase
	public void testCase_getOne() {
		
		//execution
		final var result = FunctionCatalogue.getOne();
		
		//verification
		expect(result).isEqualTo(0);
	}
	
	//method
	@TestCase
	public void testCase_getTrue() {
		
		//execution
		final var result = FunctionCatalogue.getTrue();
		
		//verification
		expect(result);
	}
}
