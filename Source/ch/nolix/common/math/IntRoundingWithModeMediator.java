//package declaration
package ch.nolix.common.math;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;

//class
public final class IntRoundingWithModeMediator {
	
	//attributes
	private final int value;
	private final RoundingMode roundingMode;
	
	//class
	IntRoundingWithModeMediator(final int value, final RoundingMode roundingMode) {
		
		Validator
		.assertThat(roundingMode)
		.thatIsNamed("rounding mode")
		.isNotNull();
		
		this.value = value;
		this.roundingMode = roundingMode;
	}
	
	//method
	public int toNext(final int step) {
		
		Validator.assertThat(step).thatIsNamed(VariableNameCatalogue.STEP).isPositive();
		
		final var rest = value % step;
		
		if (rest == 0) {
			return value;
		}
		
		switch (roundingMode) {
			case DOWN:	
				return (value - rest);
			case DOWN_WHEN_REST_IS_HALF_STEP:
				
				if (rest <= step / 2) {
					return (value - rest);
				}
				
				return (value - rest + step);
			case UP:			
				return (value - rest + step);
			default:
				
				if (rest < step / 2) {
					return (value - rest);
				}
				
				return (value - rest + step);
		}
	}
}
