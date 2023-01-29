//package declaration
package ch.nolix.coretest.containertest;

//Java imports
import java.util.NoSuchElementException;

//own imports
import ch.nolix.core.container.matrix.GapMatrix;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//class
public final class GapMatrixTest extends ContainerTest {
	
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
		testUnit.setAt1BasedRowIndexAndColumnIndex(10, 10, "I");
		testUnit.setAt1BasedRowIndexAndColumnIndex(10, 11, "II");
		testUnit.setAt1BasedRowIndexAndColumnIndex(10, 12, "III");
		testUnit.setAt1BasedRowIndexAndColumnIndex(20, 10, "IV");
		testUnit.setAt1BasedRowIndexAndColumnIndex(20, 11, "V");
		testUnit.setAt1BasedRowIndexAndColumnIndex(20, 12, "VI");
		
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
		expectRunning(iterator::next).throwsException().ofType(NoSuchElementException.class);
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
		expectRunning(iterator::next).throwsException().ofType(NoSuchElementException.class);
	}
	
	//method
	@Override
	protected <E> IContainer<E> createEmptyContainerForType(final Class<E> type) {
		return new GapMatrix<>();
	}
	
	@Override
	protected <E> IContainer<E> createContainerWithElements(@SuppressWarnings("unchecked")E... elements) {
		
		final var gapMatrix = new GapMatrix<E>(1, elements.length);
		
		for (var i = 0; i < elements.length; i++) {
			gapMatrix.setAt1BasedRowIndexAndColumnIndex(1, i + 1, elements[i]);
		}
		
		return gapMatrix;
	}
}
