//package declaration
package ch.nolix.coreperformancetest.containerperformancetest.matrixperformancetest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.matrix.Matrix;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.testing.performancetest.PerformanceTest;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;

//class
final class MatrixPerformanceTest extends PerformanceTest {

  //method
  @Test
  void testCase_getCount() {
    expectOnAnObjectFrom(this::createMatrix).running(Matrix::getCount).hasConstantTimeComplexity();
  }

  //method
  private Matrix<Integer> createMatrix(final int rowAndColumnCount) {

    final var matrix = new Matrix<Integer>();

    for (var i = 1; i <= rowAndColumnCount; i++) {

      final ILinkedList<Integer> row = LinkedList.createEmpty();

      GlobalSequencer.forCount(rowAndColumnCount).run(j -> row.addAtEnd(j - (j % 5)));

      matrix.addRow(row);
    }

    return matrix;
  }
}
