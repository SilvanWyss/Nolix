//package declaration
package ch.nolix.tech.genericMath;

//Java imports
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.Pair;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.validator.Validator;
import ch.nolix.techAPI.genericMathAPI.ISequence;

//class
public final class ImpliciteSequence<N> implements ISequence<N> {
	
	//attributes
	private final int startIndex;
	private final ArrayList<Pair<N, BigDecimal>> valuesAndSquaredMagnitudes = new ArrayList<Pair<N, BigDecimal>>();
	private final IElementTakerElementGetter<N, N> nextValueFunction;
	private final IElementTakerElementGetter<N, BigDecimal> squaredMagnitudeFunction;
	
	//constructor
	public ImpliciteSequence(
		final int startIndex,
		final N startValue,
		final IElementTakerElementGetter<N, N> nextValueFunction,
		final IElementTakerElementGetter<N, BigDecimal> squaredMagnitudeFunction
	) {
		
		Validator
		.suppose(startIndex)
		.thatIsNamed(VariableNameCatalogue.START_INDEX)
		.isNotNegative();
		
		Validator
		.suppose(nextValueFunction)
		.thatIsNamed("next value function")
		.isNotNull();
		
		Validator
		.suppose(squaredMagnitudeFunction)
		.thatIsNamed("squared magnitude function")
		.isNotNull();
		
		this.startIndex = startIndex;
		this.nextValueFunction = nextValueFunction;
		this.squaredMagnitudeFunction = squaredMagnitudeFunction;
		
		valuesAndSquaredMagnitudes.add(
			new Pair<N, BigDecimal>(startValue, squaredMagnitudeFunction.getOutput(startValue))
		);
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
	public N getStartValue() {
		return valuesAndSquaredMagnitudes.get(0).getRefElement1();
	}
	
	//method
	@Override
	public N getValue(final int index) {
		
		calculateValuesAndSquaredMagnitudesUntil(index);
		
		return valuesAndSquaredMagnitudes.get(startIndex + index - 1).getRefElement1();
	}
	
	//method
	private void calculateValuesAndSquaredMagnitudesUntil(final int index) {
		while (valuesAndSquaredMagnitudes.size() < startIndex + index) {
			
			final var value =
			nextValueFunction.getOutput(
				valuesAndSquaredMagnitudes.get(valuesAndSquaredMagnitudes.size() - 1).getRefElement1()
			);
			
			final var squaredMagnitude = squaredMagnitudeFunction.getOutput(value);
			
			valuesAndSquaredMagnitudes.add(new Pair<N, BigDecimal>(value, squaredMagnitude));
		}
	}
}
