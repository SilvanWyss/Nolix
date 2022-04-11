//package declaration
package ch.nolix.business.dynamicmath;

//Java imports
import java.math.BigDecimal;
import java.util.ArrayList;

//own imports
import ch.nolix.businessapi.dynamicmathapi.ISequence;
import ch.nolix.core.container.pair.Pair;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionapi.IElementTakerElementGetter;

//class
abstract class Sequence<V> implements ISequence<V> {
	
	//attribute
	private final IElementTakerElementGetter<V, BigDecimal> squaredMagnitudeFunction;
	
	//multi-attribute
	private final ArrayList<Pair<V, BigDecimal>> valuesAndSquaredMagnitudes = new ArrayList<>();
	
	//constructor
	public Sequence(final IElementTakerElementGetter<V, BigDecimal> squaredMagnitudeFunction) {
		
		Validator.assertThat(squaredMagnitudeFunction).thatIsNamed("squared magnitude function").isNotNull();
		
		this.squaredMagnitudeFunction = squaredMagnitudeFunction;
	}
	
	//method
	@Override
	public int getIterationCountUntilValueSquaredMagnitudeExceedsSquaredMaxMagnitudeOrMinusOne(
		final BigDecimal squaredMaxMagnitude,
		final int maxIterationCount
	) {
		
		for (var i = 1; i <= maxIterationCount; i++) {
			if (getSquaredMagnitudeOfValueAt(i).compareTo(squaredMaxMagnitude) > 0) {
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
	protected abstract V calculateValue(int index);
	
	//method
	private void calculateValuesAndSquaredMagnitudesToIndex(final int index) {
		for (var i = valuesAndSquaredMagnitudes.size(); i <= index; i++) {
			
			final var value = calculateValue(i);
			final var valueSquaredMagnitude = calculateSquaredMagnitudeForValue(value);
			
			valuesAndSquaredMagnitudes.add(new Pair<>(value, valueSquaredMagnitude));
		}
	}
	
	//method
	private BigDecimal calculateSquaredMagnitudeForValue(final V value) {
		return squaredMagnitudeFunction.getOutput(value);
	}
}
