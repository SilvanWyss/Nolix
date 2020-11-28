//package declaration
package ch.nolix.commonTest.containerTest;

import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.container.GapMatrix;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.test.Test;

//class
public final class GapMatrixTest extends Test {
	
	//method
	@TestCase
	public void testCase_addColumn_whenGapMatrixIsExpanded() {
		
		//setup
		final var testUnit = new GapMatrix<>(100, 200);
		
		//execution
		testUnit.addColumn();
		
		//verification
		expect(testUnit.isEmpty());
		expect(testUnit.getRowCount()).isEqualTo(100);
		expect(testUnit.getColumnCount()).isEqualTo(201);
	}
	
	//method
	@TestCase
	public void testCase_addColumn_whenGapMatrixIsMinimal() {
		
		//setup
		final var testUnit = new GapMatrix<>();
		
		//execution
		testUnit.addColumn();
		
		//verification
		expect(testUnit.isEmpty());
		expect(testUnit.getRowCount()).isEqualTo(0);
		expect(testUnit.getColumnCount()).isEqualTo(1);
	}
	
	//method
	@TestCase
	public void testCase_addRow_whenGapMatrixIsExpanded() {
		
		//setup
		final var testUnit = new GapMatrix<>(100, 200);
		
		//execution
		testUnit.addRow();
		
		//verification
		expect(testUnit.isEmpty());
		expect(testUnit.getRowCount()).isEqualTo(101);
		expect(testUnit.getColumnCount()).isEqualTo(200);
	}
	
	//method
	@TestCase
	public void testCase_addRow_whenGapMatrixIsMinimal() {
		
		//setup
		final var testUnit = new GapMatrix<>();
		
		//execution
		testUnit.addRow();
		
		//verification
		expect(testUnit.isEmpty());
		expect(testUnit.getRowCount()).isEqualTo(1);
		expect(testUnit.getColumnCount()).isEqualTo(0);
	}
	
	//method
	@TestCase
	public void testCase_create() {
		
		//execution
		final var result = new GapMatrix<>(100, 200);
		
		//verification
		expect(result.isEmpty());
		expect(result.getRowCount()).isEqualTo(100);
		expect(result.getColumnCount()).isEqualTo(200);
	}
	
	//method
	@TestCase
	public void testCase_iterate_whenGapMatrixContainsElements() {
		
		//setup
		final var testUnit = new GapMatrix<String>(100, 200);
		testUnit.insert(10, 10, "I");
		testUnit.insert(10, 11, "II");
		testUnit.insert(10, 12, "III");
		testUnit.insert(20, 10, "IV");
		testUnit.insert(20, 11, "V");
		testUnit.insert(20, 12, "VI");
		
		//execution
		final var iterator = testUnit.iterator();
		
		//verification
		expect(iterator.hasNext());
		expect(iterator.next()).isEqualTo("I");
		expect(iterator.hasNext());
		expect(iterator.next()).isEqualTo("II");
		expect(iterator.hasNext());
		expect(iterator.next()).isEqualTo("III");
		expect(iterator.hasNext());
		expect(iterator.next()).isEqualTo("IV");
		expect(iterator.hasNext());
		expect(iterator.next()).isEqualTo("V");
		expect(iterator.hasNext());
		expect(iterator.next()).isEqualTo("VI");
		expectNot(iterator.hasNext());
		expect(iterator::next).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
	}
	
	//method
	@TestCase
	public void testCase_iterate_whenGapMatrixIsEmpty() {
		
		//setup
		final var testUnit = new GapMatrix<String>(100, 200);
		
		//execution
		final var iterator = testUnit.iterator();
		
		//verification
		expectNot(iterator.hasNext());
		expect(iterator::next).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
	}
}
