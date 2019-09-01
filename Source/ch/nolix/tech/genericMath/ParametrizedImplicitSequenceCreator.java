//package declaration
package ch.nolix.tech.genericMath;

//Java imports
import java.math.BigDecimal;

import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.validator.Validator;
import ch.nolix.techAPI.genericMathAPI.IParametrizedImplicitSequenceCreator;

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
		.suppose(sequencesNextValueFunction)
		.thatIsNamed("sequences next value function")
		.isNotNull();
		
		Validator
		.suppose(magnitudeFunction)
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
