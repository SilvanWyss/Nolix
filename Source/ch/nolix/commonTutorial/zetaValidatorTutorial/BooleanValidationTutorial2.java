//package declaration
package ch.nolix.commonTutorial.zetaValidatorTutorial;

//own import
import ch.nolix.common.zetaValidator.ZetaValidator;

//class
/**
 * This class provides a tutorial how the zeta validator validates a boolean.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 30
 */
public final class BooleanValidationTutorial2 {

	//main method
	/**
	 * Lets the zeta validator suppose once that true booleans are all true and once suppose that mixed booleans are all true.
	 */
	public static void main(String[] args) {
		
		//Supposes that true booleans are all true, what makes that the alpha validator does not complain.
		ZetaValidator.supposeThat(true, true, true, true);
		
		//Supposes that mixed booleans are all true, what makes that the alpha validator throws a FalseArgumentException.
		ZetaValidator.supposeThat(true, true, true, false);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private BooleanValidationTutorial2() {}
}
