//package declaration
package ch.nolix.business.dynamicmath;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.businessapi.dynamicmathapi.ISequenceDefinedBy2Predecessor;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionapi.IElementTakerElementGetter;

//class
public class SequenceDefinedBy2Predecessor<V> extends Sequence<V> implements ISequenceDefinedBy2Predecessor<V> {
	
	//attribute
	private final V firstValue;
	
	//attribute
	private final V secondValue;
	
	//attribute
	private final IElementTakerElementGetter<V, V> nextValueFunction;
	
	//constructor
	public SequenceDefinedBy2Predecessor(
		final V firstValue,
		final V secondValue,
		final IElementTakerElementGetter<V, V> nextValueFunction,
		final IElementTakerElementGetter<V, BigDecimal> squaredMagnitudeFunction
	) {
		
		super(squaredMagnitudeFunction);
		
		Validator.assertThat(firstValue).thatIsNamed("first value").isNotNull();
		Validator.assertThat(secondValue).thatIsNamed("second value").isNotNull();
		Validator.assertThat(nextValueFunction).thatIsNamed("next value function").isNotNull();
		
		this.firstValue = firstValue;
		this.secondValue = secondValue;
		this.nextValueFunction = nextValueFunction;
	}
	
	//method
	@Override
	public V getFirstValue() {
		return firstValue;
	}
	
	//method
	@Override
	public V getSecondValue() {
		return secondValue;
	}
	
	//method
	@Override
	protected V calculateValue(final int index) {
		
		if (index == 1) {
			return getFirstValue();
		}
		
		return nextValueFunction.getOutput(getValueAtIndex(index - 1));
	}
}
