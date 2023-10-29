//package declaration
package ch.nolix.tech.math.bigdecimalmath;

//Java imports
import java.math.BigDecimal;
import java.util.ArrayList;

import ch.nolix.core.container.pair.Pair;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.ISequence;

//class
abstract class Sequence<V> implements ISequence<V> {

  //multi-attribute
  private final ArrayList<Pair<V, BigDecimal>> valuesAndSquaredMagnitudes = new ArrayList<>();

  //method
  @Override
  public int getIterationCountUntilValueSquaredMagnitudeExceedsLimitOrMinusOne(
    final BigDecimal limit,
    final int maxIterationCount) {

    for (var i = 1; i <= maxIterationCount; i++) {
      if (getSquaredMagnitudeOfValueAt1BasedIndex(i).compareTo(limit) > 0) {
        return i;
      }
    }

    return -1;
  }

  //method
  @Override
  public BigDecimal getSquaredMagnitudeOfValueAt1BasedIndex(final int p1BasedIndex) {

    calculateValuesAndSquaredMagnitudesToIndex(p1BasedIndex);

    return valuesAndSquaredMagnitudes.get(p1BasedIndex - 1).getStoredElement2();
  }

  //method
  @Override
  public V getValueAt1BasedIndex(final int p1BasedIndex) {

    calculateValuesAndSquaredMagnitudesToIndex(p1BasedIndex);

    return valuesAndSquaredMagnitudes.get(p1BasedIndex - 1).getStoredElement1();
  }

  //method declaration
  protected abstract BigDecimal calculateSquaredMagnitudeForValue(V value);

  //method declaration
  protected abstract V calculateValue(int index);

  //method
  protected V getValueAtIndexWhenCalculated(final int index) {
    return valuesAndSquaredMagnitudes.get(index - 1).getStoredElement1();
  }

  //method
  private void calculateValuesAndSquaredMagnitudesToIndex(final int index) {
    for (var i = valuesAndSquaredMagnitudes.size() + 1; i <= index; i++) {

      final var value = calculateValue(i);
      final var valueSquaredMagnitude = calculateSquaredMagnitudeForValue(value);

      valuesAndSquaredMagnitudes.add(new Pair<>(value, valueSquaredMagnitude));
    }
  }
}
