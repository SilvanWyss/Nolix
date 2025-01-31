package ch.nolix.core.container.matrix;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.testing.performancetest.PerformanceTest;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;

final class MatrixPerformanceTest extends PerformanceTest {

  @Test
  void testCase_getCount() {
    expectOnAnObjectFrom(this::createMatrix).running(Matrix::getCount).hasConstantOrLowerTimeComplexity();
  }

  private Matrix<Integer> createMatrix(final int rowAndColumnCount) {

    final Matrix<Integer> matrix = Matrix.createEmpty();

    for (var i = 1; i <= rowAndColumnCount; i++) {

      final ILinkedList<Integer> row = LinkedList.createEmpty();

      GlobalSequencer.forCount(rowAndColumnCount).run(j -> row.addAtEnd(j - (j % 5)));

      matrix.addRow(row);
    }

    return matrix;
  }
}
