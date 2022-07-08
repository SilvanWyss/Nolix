//package declaration
package ch.nolix.business.bigdecimalmath;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.businessapi.bigdecimalmathapi.IComplexNumber;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IIntTakerElementGetter;

//class
public final class ComplexExplicitSequence extends Sequence<IComplexNumber> {
	
	//attribute
	private final IIntTakerElementGetter<IComplexNumber> valueFunction;
	
	//constructor
	public ComplexExplicitSequence(final IIntTakerElementGetter<IComplexNumber> valueFunction) {
		
		GlobalValidator.assertThat(valueFunction).thatIsNamed("value function").isNotNull();
		
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
