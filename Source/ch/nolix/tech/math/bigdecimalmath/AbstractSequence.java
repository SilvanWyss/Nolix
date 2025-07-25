package ch.nolix.tech.math.bigdecimalmath;

import java.math.BigDecimal;
import java.util.ArrayList;

import ch.nolix.core.container.pair.Pair;
import ch.nolix.techapi.math.bigdecimalmath.ISequence;

abstract class AbstractSequence<V> implements ISequence<V> {

  private final ArrayList<Pair<V, BigDecimal>> valuesAndSquaredMagnitudes = new ArrayList<>();

  @Override
  public int getIterationCountWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne(
    final BigDecimal limit,
    final int maxIterationCount) {

    for (var i = 1; i <= maxIterationCount; i++) {
      if (getSquaredMagnitudeOfValueAtOneBasedIndex(i).compareTo(limit) > 0) {
        return i;
      }
    }

    return -1;
  }

  @Override
  public BigDecimal getSquaredMagnitudeOfValueAtOneBasedIndex(final int oneBasedIndex) {

    calculateValuesAndSquaredMagnitudesToIndex(oneBasedIndex);

    return valuesAndSquaredMagnitudes.get(oneBasedIndex - 1).getStoredElement2();
  }

  @Override
  public V getValueAtOneBasedIndex(final int oneBasedIndex) {

    calculateValuesAndSquaredMagnitudesToIndex(oneBasedIndex);

    return valuesAndSquaredMagnitudes.get(oneBasedIndex - 1).getStoredElement1();
  }

  protected abstract BigDecimal calculateSquaredMagnitudeForValue(V value);

  protected abstract V calculateValue(int index);

  protected V getValueAtIndexWhenCalculated(final int index) {
    return valuesAndSquaredMagnitudes.get(index - 1).getStoredElement1();
  }

  private void calculateValuesAndSquaredMagnitudesToIndex(final int index) {
    for (var i = valuesAndSquaredMagnitudes.size() + 1; i <= index; i++) {

      final var value = calculateValue(i);
      final var valueSquaredMagnitude = calculateSquaredMagnitudeForValue(value);

      valuesAndSquaredMagnitudes.add(new Pair<>(value, valueSquaredMagnitude));
    }
  }
}
