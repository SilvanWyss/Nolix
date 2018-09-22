//package declaration
package ch.nolix.coreTutorial.validator2Tutorial;

//own import
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * The {@link StringValidationTutorial} is a tutorial for the {@link Validator}.
 * 
 * @author Silvan
 * @month 2017-03
 * @lines 30
 */
public final class StringValidationTutorial {

	//main method
	/**
	 * Lets the {@link Validator} validate the maximum length of a {@link String}.
	 */
	public static void main(String[] args) {
		
		//Lets the validator validate that the string 'Hello World!' is not null and not empty.
		Validator.suppose("Hello World!").isNotEmpty();
		
		//Lets the validator validate that he string 'Hello World!' has the max length 20.
		Validator.suppose("Hello World!").hasMaxLength(12);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link Validator} can be created.
	 */
	private StringValidationTutorial() {}
}
