package ch.nolix.tech.math.bigdecimalmath;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;

final class ComplexSequenceDefinedBy1PredecessorTest extends StandardTest {

  @Test
  void test_getIterationCountWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne_1() {

    //setup
    final var testUnit = new ComplexSequenceDefinedBy1Predecessor(
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
    final var testUnit = new ComplexSequenceDefinedBy1Predecessor(
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
  void test_getSquaredMagnitudeOfValueAt1BasedIndex() {

    //setup
    final var testUnit = new ComplexSequenceDefinedBy1Predecessor(
      new ComplexNumber(0.0, 0.0, 20),
      p -> p.getPower2().getSum(new ComplexNumber(1.0, 0.0)));

    //execution & verification
    expect(testUnit.getSquaredMagnitudeOfValueAt1BasedIndex(1)).isEqualTo(BigDecimal.valueOf(0.0).setScale(20));
    expect(testUnit.getSquaredMagnitudeOfValueAt1BasedIndex(2)).isEqualTo(BigDecimal.valueOf(1.0).setScale(20));
    expect(testUnit.getSquaredMagnitudeOfValueAt1BasedIndex(3)).isEqualTo(BigDecimal.valueOf(4.0).setScale(20));
    expect(testUnit.getSquaredMagnitudeOfValueAt1BasedIndex(4)).isEqualTo(BigDecimal.valueOf(25.0).setScale(20));
  }

  @Test
  void test_getValueAt1BasedIndex() {

    //setup
    final var testUnit = new ComplexSequenceDefinedBy1Predecessor(
      new ComplexNumber(0.0, 0.0, 20),
      p -> p.getPower2().getSum(new ComplexNumber(1.0, 0.0)));

    //execution & verification
    expect(testUnit.getValueAt1BasedIndex(1)).isEqualTo(new ComplexNumber(0.0, 0.0, 20));
    expect(testUnit.getValueAt1BasedIndex(2)).isEqualTo(new ComplexNumber(1.0, 0.0, 20));
    expect(testUnit.getValueAt1BasedIndex(3)).isEqualTo(new ComplexNumber(2.0, 0.0, 20));
    expect(testUnit.getValueAt1BasedIndex(4)).isEqualTo(new ComplexNumber(5.0, 0.0, 20));
  }
}
