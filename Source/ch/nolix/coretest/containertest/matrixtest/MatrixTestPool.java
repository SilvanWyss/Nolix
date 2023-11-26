//package declaration
package ch.nolix.coretest.containertest.matrixtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class MatrixTestPool extends TestPool {

  //constructor
  public MatrixTestPool() {
    super(GapMatrixTest.class, MatrixTest.class);
  }
}
