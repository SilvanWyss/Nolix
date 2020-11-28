//package declaration
package ch.nolix.tech.genericMath;

//Java imports
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import ch.nolix.common.constant.MultiVariableNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.pair.Pair;
import ch.nolix.common.validator.Validator;
import ch.nolix.techAPI.genericMathAPI.IImplicitSequence;

//class
public final class ImpliciteSequence<N> implements IImplicitSequence<N> {
	
	//attribute
	private final int startIndex;
	
	//multi-attribute
	private final N[] startValues;
	
	//attributes
	private final IElementTakerElementGetter<N[], N> nextValueFunction;
	private final IElementTakerElementGetter<N, BigDecimal> squaredMagnitudeFunction;
	
	//multi-attribute
	private final ArrayList<Pair<N, BigDecimal>> valuesAndSquaredMagnitudes = new ArrayList<>();
	
	//constructor
	public ImpliciteSequence(
		final int startIndex,
		final N[] startValues,
		final IElementTakerElementGetter<N[], N> nextValueFunction,
		final IElementTakerElementGetter<N, BigDecimal> squaredMagnitudeFunction
	) {
		
		Validator
		.assertThat(startIndex)
		.thatIsNamed(VariableNameCatalogue.START_INDEX)
		.isNotNegative();
		
		Validator
		.assertThat(startValues)
		.thatIsNamed(MultiVariableNameCatalogue.START_VALUES)
		.isNotEmpty();
		
		Validator
		.assertThatTheElements(startValues)
		.areNotNull();
		
		Validator
		.assertThat(nextValueFunction)
		.thatIsNamed("next value function")
		.isNotNull();
		
		Validator
		.assertThat(squaredMagnitudeFunction)
		.thatIsNamed("squared magnitude function")
		.isNotNull();
		
		this.startIndex = startIndex;
		this.nextValueFunction = nextValueFunction;
		this.squaredMagnitudeFunction = squaredMagnitudeFunction;
		this.startValues = startValues.clone();
		
		for (final var sv : startValues) {
			valuesAndSquaredMagnitudes.add(new Pair<N, BigDecimal>(sv, squaredMagnitudeFunction.getOutput(sv)));
		}
	}
	
	//method
	@Override
	public int getConvergenceGrade(final BigDecimal maxMagnitude, final int maxIndex) {	
		
		final BigDecimal maxSquaredMagnitude =
		maxMagnitude.multiply(maxMagnitude).setScale(maxMagnitude.scale(), RoundingMode.HALF_UP);
		
		for (var i = getStartIndex(); i < maxIndex; i++) {
			if (getSquaredMagnitude(i).compareTo(maxSquaredMagnitude) > 0) {
				return i;
			}
		}
		
		return maxIndex;
	}
	
	//method
	@Override
	public BigDecimal getSquaredMagnitude(final int index) {
		
		calculateValuesAndSquaredMagnitudesUntil(index);
		
		return valuesAndSquaredMagnitudes.get(index - 1).getRefElement2();
	}
	
	//method
	@Override
	public IElementTakerElementGetter<N, BigDecimal> getSquaredMagnitudeFunction() {
		return squaredMagnitudeFunction;
	}
	
	//method
	@Override
	public int getStartIndex() {
		return startIndex;
	}
	
	//method
	@Override
	public N[] getStartValues() {
		return startValues;
	}
	
	//method
	@Override
	public int getStartValuesCount() {
		return startValues.length;
	}
	
	//method
	@Override
	public N getValue(final int index) {
		
		calculateValuesAndSquaredMagnitudesUntil(index);
		
		return valuesAndSquaredMagnitudes.get(index - startIndex).getRefElement1();
	}
	
	//method
	private void calculateValuesAndSquaredMagnitudesUntil(final int index) {
		
		final var startValuesCount = getStartValuesCount();
		
		while (valuesAndSquaredMagnitudes.size() < index - startIndex + 1) {
			
			@SuppressWarnings("unchecked")
			final var previousValues =
			(N[])Array.newInstance(valuesAndSquaredMagnitudes.get(0).getRefElement1().getClass(), startValuesCount);
			
			final var endIndex = valuesAndSquaredMagnitudes.size() - startValuesCount;
			var j = 0;
			
			for (var i = valuesAndSquaredMagnitudes.size() - 1; i >= endIndex; i--) {
				previousValues[j] = valuesAndSquaredMagnitudes.get(i).getRefElement1();
				j++;
			}
			
			final var value = nextValueFunction.getOutput(previousValues);
			final var squaredMagnitudeOfValue = squaredMagnitudeFunction.getOutput(value);
			valuesAndSquaredMagnitudes.add(new Pair<N, BigDecimal>(value, squaredMagnitudeOfValue));
		}
	}
}
