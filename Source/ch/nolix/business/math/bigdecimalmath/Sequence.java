//package declaration
package ch.nolix.business.math.bigdecimalmath;

//Java imports
import java.math.BigDecimal;
import java.util.ArrayList;

import ch.nolix.businessapi.mathapi.bigdecimalmathapi.ISequence;
import ch.nolix.core.container.pair.Pair;

//class
abstract class Sequence<V> implements ISequence<V> {
	
	//multi-attribute
	private final ArrayList<Pair<V, BigDecimal>> valuesAndSquaredMagnitudes = new ArrayList<>();
	
	//method
	@Override
	public int getIterationCountUntilValueSquaredMagnitudeExceedsLimitOrMinusOne(
		final BigDecimal limit,
		final int maxIterationCount
	) {
		
		for (var i = 1; i <= maxIterationCount; i++) {
			if (getSquaredMagnitudeOfValueAt(i).compareTo(limit) > 0) {
				return i;
			}
		}
		
		return -1;
	}
	
	//method
	@Override
	public BigDecimal getSquaredMagnitudeOfValueAt(final int index) {
		
		calculateValuesAndSquaredMagnitudesToIndex(index);
		
		return valuesAndSquaredMagnitudes.get(index - 1).getRefElement2();
	}
	
	//method
	@Override
	public V getValueAtIndex(final int index) {
		
		calculateValuesAndSquaredMagnitudesToIndex(index);
		
		return valuesAndSquaredMagnitudes.get(index - 1).getRefElement1();
	}
	
	//method declaration
	protected abstract BigDecimal calculateSquaredMagnitudeForValue(V value);
	
	//method declaration
	protected abstract V calculateValue(int index);
	
	//method
	protected V getValueAtIndexWhenCalculated(final int index) {
		return valuesAndSquaredMagnitudes.get(index - 1).getRefElement1();
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
