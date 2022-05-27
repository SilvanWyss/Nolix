//package declaration
package ch.nolix.business.dynamicmath;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.businessapi.dynamicmathapi.IComplexNumber;
import ch.nolix.businessapi.dynamicmathapi.ISequenceDefinedBy2Predecessor;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.functionuniversalapi.I2ElementTakerElementGetter;

//class
public final class ComplexSequenceDefinedBy2Predecessor
extends Sequence<IComplexNumber>
implements ISequenceDefinedBy2Predecessor<IComplexNumber> {
	
	//attribute
	private final IComplexNumber firstValue;
	
	//attribute
	private final IComplexNumber secondValue;
	
	//attribute
	private final I2ElementTakerElementGetter<IComplexNumber, IComplexNumber, IComplexNumber> nextValueFunction;
	
	//constructor
	public ComplexSequenceDefinedBy2Predecessor(
		final IComplexNumber firstValue,
		final IComplexNumber secondValue,
		I2ElementTakerElementGetter<IComplexNumber, IComplexNumber, IComplexNumber> nextValueFunction
	) {
		
		GlobalValidator.assertThat(firstValue).thatIsNamed("first value").isNotNull();
		GlobalValidator.assertThat(secondValue).thatIsNamed("second value").isNotNull();
		GlobalValidator.assertThat(nextValueFunction).thatIsNamed("next value function").isNotNull();
		
		this.firstValue = firstValue;
		this.secondValue = secondValue;
		this.nextValueFunction = nextValueFunction;
	}
	
	//method
	@Override
	public IComplexNumber getFirstValue() {
		return firstValue;
	}
	
	//method
	@Override
	public IComplexNumber getSecondValue() {
		return secondValue;
	}
	
	//method
	@Override
	protected BigDecimal calculateSquaredMagnitudeForValue(final IComplexNumber value) {
		return value.getSquaredMagnitude();
	}
	
	//method
	@Override
	protected IComplexNumber calculateValue(final int index) {
		
		if (index == 1) {
			return getFirstValue();
		}
		
		if (index == 2) {
			return getSecondValue();
		}
		
		return nextValueFunction.getOutput(getValueAtIndex(index - 2), getValueAtIndex(index - 1));
	}
}
