//package declaration
package ch.nolix.system.dynamicmath;

//Java imports
import java.math.BigDecimal;

import ch.nolix.businessapi.dynamicmathapi.IParametrizedImplicitSequenceCreator;
import ch.nolix.common.errorcontrol.validator.Validator;
//own imports
import ch.nolix.common.functionapi.IElementTakerElementGetter;

//class
public final class ParametrizedImplicitSequenceCreator<N> implements IParametrizedImplicitSequenceCreator<N> {
	
	//attributes
	private final int sequencesStartIndex;
	private final IElementTakerElementGetter<N[], N> sequencesNextValueFunction;
	private final IElementTakerElementGetter<N, BigDecimal> magnitudeFunction;
	
	//constructor
	public ParametrizedImplicitSequenceCreator(
		final int sequencesStartIndex,
		final IElementTakerElementGetter<N[], N> sequencesNextValueFunction,
		final IElementTakerElementGetter<N, BigDecimal> magnitudeFunction
	) {
		
		Validator
		.assertThat(sequencesNextValueFunction)
		.thatIsNamed("sequences next value function")
		.isNotNull();
		
		Validator
		.assertThat(magnitudeFunction)
		.thatIsNamed("magnitude function")
		.isNotNull();
		
		this.sequencesStartIndex = sequencesStartIndex;
		this.sequencesNextValueFunction = sequencesNextValueFunction;
		this.magnitudeFunction = magnitudeFunction;
	}
	
	//method
	@Override
	public ImpliciteSequence<N> createSequence(final N[] startValue) {
		return
		new ImpliciteSequence<>(
			sequencesStartIndex,
			startValue,
			sequencesNextValueFunction,
			magnitudeFunction
		);
	}

	@Override
	public int getSequencesStartIndex() {
		return sequencesStartIndex;
	}
}
