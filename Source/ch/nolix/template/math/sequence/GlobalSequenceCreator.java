//package declaration
package ch.nolix.template.math.sequence;

//own imports
import ch.nolix.tech.math.bigdecimalmath.ComplexNumber;
import ch.nolix.tech.math.bigdecimalmath.ComplexSequenceDefinedBy1Predecessor;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IComplexNumber;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.ISequenceDefinedBy1Predecessor;

//class
public final class GlobalSequenceCreator {

  //constructor
  private GlobalSequenceCreator() {
  }

  //static method
  public static ISequenceDefinedBy1Predecessor<IComplexNumber> createMandelbrotSequenceForIncrement(
    final IComplexNumber increment) {
    return new ComplexSequenceDefinedBy1Predecessor(
      new ComplexNumber(0.0, 0.0, increment.getDecimalPlaces()),
      z -> z.getPower2().getSum(increment));
  }

  //static method
  public static ISequenceDefinedBy1Predecessor<IComplexNumber> createMandelbrotSequenceForStartValueAndIncrement(
    final IComplexNumber startValue,
    final IComplexNumber increment) {
    return new ComplexSequenceDefinedBy1Predecessor(
      startValue,
      z -> z.getPower2().getSum(increment));
  }
}
