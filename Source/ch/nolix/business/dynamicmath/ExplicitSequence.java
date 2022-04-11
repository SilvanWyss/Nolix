//package declaration
package ch.nolix.business.dynamicmath;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionapi.IElementTakerElementGetter;
import ch.nolix.core.functionapi.IIntTakerElementGetter;

//class
public final class ExplicitSequence<V> extends Sequence<V> {
	
	//attribute
	private final IIntTakerElementGetter<V> valueFunction;
	
	//constructor
	public ExplicitSequence(
		final IIntTakerElementGetter<V> valueFunction,
		final IElementTakerElementGetter<V, BigDecimal> squaredMagnitudeFunction
	) {
		
		super(squaredMagnitudeFunction);
		
		Validator.assertThat(valueFunction).thatIsNamed("value function").isNotNull();
		
		this.valueFunction = valueFunction;
	}
	
	//method
	@Override
	protected V calculateValue(final int index) {
		return valueFunction.getOutput(index);
	}
}
