//package declaration
package ch.nolix.tech.genericMath;

//Java imports
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

//own imports
import ch.nolix.core.constants.MultiVariableNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.tuple.Pair;
import ch.nolix.core.validator.Validator;
import ch.nolix.techAPI.genericMathAPI.IImplicitSequence;

//class
public final class ImpliciteSequence<N> implements IImplicitSequence<N> {
	
	//attribute
	private final int startIndex;
	
	//multi-attribute
	private final ArrayList<N> startValues = new ArrayList<>();
	
	//attributes
	private final IElementTakerElementGetter<ArrayList<N>, N> nextValueFunction;
	private final IElementTakerElementGetter<N, BigDecimal> squaredMagnitudeFunction;
	
	//multi-attribute
	private final ArrayList<Pair<N, BigDecimal>> valuesAndSquaredMagnitudes = new ArrayList<>();
	
	//constructor
	public ImpliciteSequence(
		final int startIndex,
		final ArrayList<N> startValues,
		final IElementTakerElementGetter<ArrayList<N>, N> nextValueFunction,
		final IElementTakerElementGetter<N, BigDecimal> squaredMagnitudeFunction
	) {
		
		Validator
		.suppose(startIndex)
		.thatIsNamed(VariableNameCatalogue.START_INDEX)
		.isNotNegative();
		
		Validator
		.suppose(startValues)
		.thatIsNamed(MultiVariableNameCatalogue.START_VALUES)
		.isNotEmpty();
		
		Validator
		.supposeTheElements(startValues)
		.areNotNull();
		
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
		
		for (final var sv : startValues) {
			this.startValues.add(sv);
		}
		
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
	public ArrayList<N> getStartValues() {
		return startValues;
	}
	
	//method
	@Override
	public int getStartValuesCount() {
		return startValues.size();
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
			
			final var previousValues = new ArrayList<N>(startValuesCount);
			final var endIndex = valuesAndSquaredMagnitudes.size() - startValuesCount;
			for (var i = valuesAndSquaredMagnitudes.size() - 1; i >= endIndex; i--) {
				previousValues.add(valuesAndSquaredMagnitudes.get(i).getRefElement1());
			}
			
			final var value = nextValueFunction.getOutput(previousValues);			
			final var squaredMagnitude = squaredMagnitudeFunction.getOutput(value);			
			valuesAndSquaredMagnitudes.add(new Pair<N, BigDecimal>(value, squaredMagnitude));
		}
	}
}
