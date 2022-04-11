//package declaration
package ch.nolix.business.dynamicmath;

import java.math.BigDecimal;

//own imports
import ch.nolix.businessapi.dynamicmathapi.ISequenceDefinedBy1Predecessor;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionapi.IElementTakerElementGetter;

//class
public class SequenceDefinedBy1Predecessor<V> extends Sequence<V> implements ISequenceDefinedBy1Predecessor<V> {
	
	//attribute
	private final V firstValue;
	
	//attribute
	private final IElementTakerElementGetter<V, V> nextValueFunction;
	
	//constructor
	public SequenceDefinedBy1Predecessor(
		final V firstValue,
		final IElementTakerElementGetter<V, V> nextValueFunction,
		final IElementTakerElementGetter<V, BigDecimal> squaredMagnitudeFunction
	) {
		
		super(squaredMagnitudeFunction);
		
		Validator.assertThat(firstValue).thatIsNamed("first value").isNotNull();
		Validator.assertThat(nextValueFunction).thatIsNamed("next value function").isNotNull();
		
		this.firstValue = firstValue;
		this.nextValueFunction = nextValueFunction;
	}
	
	//method
	@Override
	public V getFirstValue() {
		return firstValue;
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
