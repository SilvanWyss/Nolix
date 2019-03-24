//package declaration
package ch.nolix.core.math;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.validator.Validator;

//class
public final class IntRoundingWithModeMediator {
	
	//attributes
	private final int value;
	private final RoundingMode roundingMode;
	
	//package-visible class
	IntRoundingWithModeMediator(final int value, final RoundingMode roundingMode) {
		
		Validator
		.suppose(roundingMode)
		.thatIsNamed("rounding mode")
		.isNotNull();
		
		this.value = value;
		this.roundingMode = roundingMode;
	}
	
	//method
	public int toNext(final int step) {
		
		Validator.suppose(step).thatIsNamed(VariableNameCatalogue.STEP).isPositive();
		
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
