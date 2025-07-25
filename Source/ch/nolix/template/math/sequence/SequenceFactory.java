package ch.nolix.template.math.sequence;

import ch.nolix.tech.math.bigdecimalmath.ComplexNumber;
import ch.nolix.tech.math.bigdecimalmath.ComplexSequenceDefinedBy1Predecessor;
import ch.nolix.techapi.math.bigdecimalmath.IComplexNumber;
import ch.nolix.techapi.math.bigdecimalmath.ISequenceDefinedBy1Predecessor;

public final class SequenceFactory {

  private SequenceFactory() {
  }

  public static ISequenceDefinedBy1Predecessor<IComplexNumber> createMandelbrotSequenceForIncrement(
    final IComplexNumber increment) {
    return createMandelbrotSequenceForStartValueAndIncrement(new ComplexNumber(0.0, 0.0), increment);
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
