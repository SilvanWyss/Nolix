/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.template.bigdecimalmath.sequence;

import ch.nolix.system.math.bigdecimalmath.ComplexNumber;
import ch.nolix.system.math.bigdecimalmath.ComplexSequenceDefinedBy1Predecessor;
import ch.nolix.systemapi.math.bigdecimalmath.IComplexNumber;
import ch.nolix.systemapi.math.bigdecimalmath.ISequenceDefinedBy1Predecessor;

/**
 * @author Silvan Wyss
 */
public final class ComplexSequenceFactory {
  private ComplexSequenceFactory() {
  }

  /**
   * @param increment
   * @return a new Mandelbrot sequence for the given increment
   */
  public static ISequenceDefinedBy1Predecessor<IComplexNumber> createMandelbrotSequenceForIncrement(
    final IComplexNumber increment) {
    return createMandelbrotSequenceForStartValueAndIncrement(
      ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0), increment);
  }

  /**
   * @param startValue
   * @param increment
   * @return a new Mandelbrot sequence for the given startValue and increment
   */
  public static ISequenceDefinedBy1Predecessor<IComplexNumber> createMandelbrotSequenceForStartValueAndIncrement(
    final IComplexNumber startValue,
    final IComplexNumber increment) {
    return //
    ComplexSequenceDefinedBy1Predecessor.withFirstValueAndNextValueFunction(
      startValue,
      z -> z.getPower2().getSum(increment));
  }
}
