//package declaration
package ch.nolix.techtest.mathtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.techtest.mathtest.bigdecimalmathtest.BigDecimalMathTestPool;
import ch.nolix.techtest.mathtest.fractaltest.FractalTestPool;

//class
public final class MathTestPool extends TestPool {

  //constructor
  public MathTestPool() {
    super(new BigDecimalMathTestPool(), new FractalTestPool());
  }
}
