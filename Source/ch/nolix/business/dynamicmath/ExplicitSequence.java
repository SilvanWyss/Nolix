//package declaration
package ch.nolix.business.dynamicmath;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.businessapi.dynamicmathapi.IComplexNumber;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionapi.IIntTakerElementGetter;

//class
public final class ExplicitSequence extends Sequence<IComplexNumber> {
	
	//attribute
	private final IIntTakerElementGetter<IComplexNumber> valueFunction;
	
	//constructor
	public ExplicitSequence(final IIntTakerElementGetter<IComplexNumber> valueFunction) {
		
		Validator.assertThat(valueFunction).thatIsNamed("value function").isNotNull();
		
		this.valueFunction = valueFunction;
	}
	
	//method
	@Override
	protected BigDecimal calculateSquaredMagnitudeForValue(final IComplexNumber value) {
		return value.getSquaredMagnitude();
	}
	
	//method
	@Override
	protected IComplexNumber calculateValue(final int index) {
		return valueFunction.getOutput(index);
	}
}
