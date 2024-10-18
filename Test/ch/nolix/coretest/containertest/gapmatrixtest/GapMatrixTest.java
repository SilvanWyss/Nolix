package ch.nolix.coretest.containertest.gapmatrixtest;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.gapmatrix.GapMatrix;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coretest.containertest.basetest.ContainerTest;

final class GapMatrixTest extends ContainerTest {

  @Test
  void testCase_addColumn_whenGapMatrixIsExpanded() {

    //setup
    final var testUnit = new GapMatrix<>(100, 200);

    //execution
    testUnit.addColumn();

    //verification
    expect(testUnit.isEmpty());
    expect(testUnit.getRowCount()).isEqualTo(100);
    expect(testUnit.getColumnCount()).isEqualTo(201);
  }

  @Test
  void testCase_addColumn_whenGapMatrixIsMinimal() {

    //setup
    final var testUnit = new GapMatrix<>();

    //execution
    testUnit.addColumn();

    //verification
    expect(testUnit.isEmpty());
    expect(testUnit.getRowCount()).isEqualTo(0);
    expect(testUnit.getColumnCount()).isEqualTo(1);
  }

  @Test
  void testCase_addRow_whenGapMatrixIsExpanded() {

    //setup
    final var testUnit = new GapMatrix<>(100, 200);

    //execution
    testUnit.addRow();

    //verification
    expect(testUnit.isEmpty());
    expect(testUnit.getRowCount()).isEqualTo(101);
    expect(testUnit.getColumnCount()).isEqualTo(200);
  }

  @Test
  void testCase_addRow_whenGapMatrixIsMinimal() {

    //setup
    final var testUnit = new GapMatrix<>();

    //execution
    testUnit.addRow();

    //verification
    expect(testUnit.isEmpty());
    expect(testUnit.getRowCount()).isEqualTo(1);
    expect(testUnit.getColumnCount()).isEqualTo(0);
  }

  @Test
  void testCase_create() {

    //execution
    final var result = new GapMatrix<>(100, 200);

    //verification
    expect(result.isEmpty());
    expect(result.getRowCount()).isEqualTo(100);
    expect(result.getColumnCount()).isEqualTo(200);
  }

  @Test
  void testCase_iterate_whenGapMatrixContainsElements() {

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

  @Test
  void testCase_iterate_whenGapMatrixIsEmpty() {

    //setup
    final var testUnit = new GapMatrix<String>(100, 200);

    //execution
    final var iterator = testUnit.iterator();

    //verification
    expectNot(iterator.hasNext());
    expectRunning(iterator::next).throwsException().ofType(NoSuchElementException.class);
  }

  @Override
  protected <E> IContainer<E> createEmptyContainerForType(final Class<E> type) {
    return new GapMatrix<>();
  }

  @Override
  protected <E> IContainer<E> createContainerWithElements(
    final E element,
    final @SuppressWarnings("unchecked") E... elements) {

    final var size = 1 + elements.length;

    final var gapMatrix = new GapMatrix<E>(1, size);

    gapMatrix.setAt1BasedRowIndexAndColumnIndex(1, 1, element);

    for (var i = 0; i < elements.length; i++) {
      gapMatrix.setAt1BasedRowIndexAndColumnIndex(1, i + 2, elements[i]);
    }

    return gapMatrix;
  }
}
