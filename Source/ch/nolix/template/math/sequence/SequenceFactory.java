/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.template.math.sequence;

import ch.nolix.system.math.bigdecimalmath.ComplexNumber;
import ch.nolix.system.math.bigdecimalmath.ComplexSequenceDefinedBy1Predecessor;
import ch.nolix.systemapi.math.bigdecimalmath.IComplexNumber;
import ch.nolix.systemapi.math.bigdecimalmath.ISequenceDefinedBy1Predecessor;

/**
 * @author Silvan Wyss
 */
public final class SequenceFactory {
  private SequenceFactory() {
  }

  public static ISequenceDefinedBy1Predecessor<IComplexNumber> createMandelbrotSequenceForIncrement(
    final IComplexNumber increment) {
    return createMandelbrotSequenceForStartValueAndIncrement(
      ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0), increment);
  }

  public static ISequenceDefinedBy1Predecessor<IComplexNumber> createMandelbrotSequenceForStartValueAndIncrement(
    final IComplexNumber startValue,
    final IComplexNumber increment) {
    return //
    ComplexSequenceDefinedBy1Predecessor.withFirstValueAndNextValueFunction(
      startValue,
      z -> z.getPower2().getSum(increment));
  }
}
