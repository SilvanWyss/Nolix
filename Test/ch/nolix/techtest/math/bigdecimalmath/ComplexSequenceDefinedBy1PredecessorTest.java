package ch.nolix.techtest.math.bigdecimalmath;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.tech.math.bigdecimalmath.ComplexNumber;
import ch.nolix.tech.math.bigdecimalmath.ComplexSequenceDefinedBy1Predecessor;

/**
 * @author Silvan Wyss
 */
final class ComplexSequenceDefinedBy1PredecessorTest extends StandardTest {
  @Test
  void test_getIterationCountWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne_1() {
    //setup
    final var testUnit = //
    ComplexSequenceDefinedBy1Predecessor.withFirstValueAndNextValueFunction(
      new ComplexNumber(0.0, 0.0, 20),
      p -> p.getPower2().getSum(new ComplexNumber(1.0, 0.0)));

    //execution & verification
    expect(testUnit.getIterationCountWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne(BigDecimal.valueOf(2.5), 4))
      .isEqualTo(3);
    expect(testUnit.getIterationCountWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne(BigDecimal.valueOf(12.5), 4))
      .isEqualTo(4);
    expect(testUnit.getIterationCountWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne(BigDecimal.valueOf(112.5), 4))
      .isEqualTo(-1);
    expect(testUnit.getIterationCountWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne(BigDecimal.valueOf(1112.5), 4))
      .isEqualTo(-1);
  }

  @Test
  void test_getIterationCountWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne_2() {
    //setup
    final var testUnit = //
    ComplexSequenceDefinedBy1Predecessor.withFirstValueAndNextValueFunction(
      new ComplexNumber(0.0, 0.0, 20),
      p -> p.getPower2().getSum(new ComplexNumber(1.0, 0.0)));

    //execution & verification
    expect(testUnit.getIterationCountWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne(BigDecimal.valueOf(2.5), 1))
      .isEqualTo(-1);
    expect(testUnit.getIterationCountWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne(BigDecimal.valueOf(2.5), 2))
      .isEqualTo(-1);
    expect(testUnit.getIterationCountWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne(BigDecimal.valueOf(2.5), 3))
      .isEqualTo(3);
    expect(testUnit.getIterationCountWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne(BigDecimal.valueOf(2.5), 4))
      .isEqualTo(3);
  }

  @Test
  void test_getSquaredMagnitudeOfValueAtOneBasedIndex() {
    //setup
    final var testUnit = //
    ComplexSequenceDefinedBy1Predecessor.withFirstValueAndNextValueFunction(
      new ComplexNumber(0.0, 0.0, 20),
      p -> p.getPower2().getSum(new ComplexNumber(1.0, 0.0)));

    //execution & verification
    expect(testUnit.getSquaredMagnitudeOfValueAtOneBasedIndex(1)).isEqualTo(BigDecimal.valueOf(0.0).setScale(20));
    expect(testUnit.getSquaredMagnitudeOfValueAtOneBasedIndex(2)).isEqualTo(BigDecimal.valueOf(1.0).setScale(20));
    expect(testUnit.getSquaredMagnitudeOfValueAtOneBasedIndex(3)).isEqualTo(BigDecimal.valueOf(4.0).setScale(20));
    expect(testUnit.getSquaredMagnitudeOfValueAtOneBasedIndex(4)).isEqualTo(BigDecimal.valueOf(25.0).setScale(20));
  }

  @Test
  void test_getValueAtOneBasedIndex() {
    //setup
    final var testUnit = //
    ComplexSequenceDefinedBy1Predecessor.withFirstValueAndNextValueFunction(
      new ComplexNumber(0.0, 0.0, 20),
      p -> p.getPower2().getSum(new ComplexNumber(1.0, 0.0)));

    //execution & verification
    expect(testUnit.getValueAtOneBasedIndex(1)).isEqualTo(new ComplexNumber(0.0, 0.0, 20));
    expect(testUnit.getValueAtOneBasedIndex(2)).isEqualTo(new ComplexNumber(1.0, 0.0, 20));
    expect(testUnit.getValueAtOneBasedIndex(3)).isEqualTo(new ComplexNumber(2.0, 0.0, 20));
    expect(testUnit.getValueAtOneBasedIndex(4)).isEqualTo(new ComplexNumber(5.0, 0.0, 20));
  }
}
