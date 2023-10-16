//package declaration
package ch.nolix.coretest.mathtest.algebratest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class AlgebraTestPool extends TestPool {

  //constructor
  public AlgebraTestPool() {
    super(
        MatrixTest.class,
        PolynomTest.class,
        QuadraticFunctionTest.class,
        VectorTest.class);
  }
}
