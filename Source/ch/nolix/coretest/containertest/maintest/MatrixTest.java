//package declaration
package ch.nolix.coretest.containertest.maintest;

//own imports
import ch.nolix.core.container.matrix.Matrix;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coretest.containertest.basetest.ContainerTest;

//class
public final class MatrixTest extends ContainerTest {

  //method
  @TestCase
  public void testCase_addColumn() {

    //setup
    final var matrix = new Matrix<String>();

    //execution
    matrix.addColumn("apple", "banana", "cerish");

    //verification
    expect(matrix.getRowCount()).isEqualTo(3);
    expect(matrix.getColumnCount()).isEqualTo(1);
    expect(matrix.getColumn(1).toString()).isEqualTo("apple,banana,cerish");
  }

  //method
  @TestCase
  public void testCase_addRow() {

    //setup
    final var matrix = new Matrix<String>();

    //execution
    matrix.addRow("apple", "banana", "cerish");

    //verification
    expect(matrix.getRowCount()).isEqualTo(1);
    expect(matrix.getColumnCount()).isEqualTo(3);
    expect(matrix.getRow(1).toString()).isEqualTo("apple,banana,cerish");
  }

  //method
  @TestCase
  public void testCase_getColumn() {

    //setup
    final var matrix = new Matrix<String>();
    matrix.addRow("apple", "banana", "cerish");
    matrix.addRow("elephant", "lion", "monkey");
    matrix.addRow("flower", "tree", "palm");

    //execution
    final var column1 = matrix.getColumn(1);
    final var column2 = matrix.getColumn(2);
    final var column3 = matrix.getColumn(3);

    //verification
    expect(column1.getElementCount()).isEqualTo(3);
    expect(column1.toString()).isEqualTo("apple,elephant,flower");
    expect(column2.getElementCount()).isEqualTo(3);
    expect(column2.toString()).isEqualTo("banana,lion,tree");
    expect(column3.getElementCount()).isEqualTo(3);
    expect(column3.toString()).isEqualTo("cerish,monkey,palm");
  }

  //method
  @TestCase
  public void testCase_getCopy() {

    //setup
    final var matrix = new Matrix<String>();
    matrix.addRow("apple", "banana", "cerish");
    matrix.addRow("elephant", "lion", "monkey");
    matrix.addRow("flower", "tree", "palm");

    //execution
    final var copy = matrix.getCopy();

    //verification
    expect(copy.getColumnCount()).isEqualTo(3);
    expect(copy.getRowCount()).isEqualTo(3);
    expect(copy.getRow(1).toString()).isEqualTo("apple,banana,cerish");
    expect(copy.getRow(2).toString()).isEqualTo("elephant,lion,monkey");
    expect(copy.getRow(3).toString()).isEqualTo("flower,tree,palm");
  }

  //method
  @TestCase
  public void testCase_getIndexOf() {

    //setup
    final var matrix = new Matrix<String>();
    matrix.addRow("apple", "banana", "cerish");
    matrix.addRow("elephant", "lion", "monkey");
    matrix.addRow("flower", "tree", "palm");

    //execution
    final var index1 = matrix.getIndexOf(1, 1);
    final var index2 = matrix.getIndexOf(1, 2);
    final var index3 = matrix.getIndexOf(1, 3);
    final var index4 = matrix.getIndexOf(2, 1);
    final var index5 = matrix.getIndexOf(2, 2);
    final var index6 = matrix.getIndexOf(2, 3);
    final var index7 = matrix.getIndexOf(3, 1);
    final var index8 = matrix.getIndexOf(3, 2);
    final var index9 = matrix.getIndexOf(3, 3);

    //verification
    expect(index1).isEqualTo(1);
    expect(index2).isEqualTo(2);
    expect(index3).isEqualTo(3);
    expect(index4).isEqualTo(4);
    expect(index5).isEqualTo(5);
    expect(index6).isEqualTo(6);
    expect(index7).isEqualTo(7);
    expect(index8).isEqualTo(8);
    expect(index9).isEqualTo(9);
  }

  //method
  @TestCase
  public void testCase_getRow() {

    //setup
    final var matrix = new Matrix<String>();
    matrix.addRow("apple", "banana", "cerish");
    matrix.addRow("elephant", "lion", "monkey");
    matrix.addRow("flower", "tree", "palm");

    //execution
    final var row1 = matrix.getRow(1);
    final var row2 = matrix.getRow(2);
    final var row3 = matrix.getRow(3);

    //verification
    expect(row1.getElementCount()).isEqualTo(3);
    expect(row1.toString()).isEqualTo("apple,banana,cerish");
    expect(row2.getElementCount()).isEqualTo(3);
    expect(row2.toString()).isEqualTo("elephant,lion,monkey");
    expect(row3.getElementCount()).isEqualTo(3);
    expect(row3.toString()).isEqualTo("flower,tree,palm");
  }

  //method
  @TestCase
  public void testCase_toLeftRotatedMatrix() {

    //setup
    final var matrix = new Matrix<String>();
    matrix.addRow("apple", "banana", "cerish");
    matrix.addRow("elephant", "lion", "monkey");
    matrix.addRow("flower", "tree", "palm");

    //execution
    final var leftRotatedMatrix = matrix.toLeftRotatedMatrix();

    //verification
    expect(leftRotatedMatrix.getRowCount()).isEqualTo(3);
    expect(leftRotatedMatrix.getColumnCount()).isEqualTo(3);
    expect(leftRotatedMatrix.getRow(1).toString()).isEqualTo("cerish,monkey,palm");
    expect(leftRotatedMatrix.getRow(2).toString()).isEqualTo("banana,lion,tree");
    expect(leftRotatedMatrix.getRow(3).toString()).isEqualTo("apple,elephant,flower");
  }

  //method
  @TestCase
  public void testCase_toRightRotatedMatrix() {

    //setup
    final var matrix = new Matrix<String>();
    matrix.addRow("apple", "banana", "cerish");
    matrix.addRow("elephant", "lion", "monkey");
    matrix.addRow("flower", "tree", "palm");

    //execution
    final var rightRotatedMatrix = matrix.toRightRotatedMatrix();

    //verification
    expect(rightRotatedMatrix.getRowCount()).isEqualTo(3);
    expect(rightRotatedMatrix.getColumnCount()).isEqualTo(3);
    expect(rightRotatedMatrix.getRow(1).toString()).isEqualTo("flower,elephant,apple");
    expect(rightRotatedMatrix.getRow(2).toString()).isEqualTo("tree,lion,banana");
    expect(rightRotatedMatrix.getRow(3).toString()).isEqualTo("palm,monkey,cerish");
  }

  //method
  @Override
  protected <E> IContainer<E> createContainerWithElements(
      final E element,
      final @SuppressWarnings("unchecked") E... elements) {

    final var matrix = new Matrix<E>();

    matrix.addRow(element, elements);

    return matrix;
  }

  //method
  @Override
  protected <E> IContainer<E> createEmptyContainerForType(final Class<E> type) {
    return new Matrix<>();
  }
}
