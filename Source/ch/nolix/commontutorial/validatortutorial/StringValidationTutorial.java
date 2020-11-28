package ch.nolix.commontutorial.validatortutorial;

import ch.nolix.common.validator.Validator;

//class
/**
 * The {@link StringValidationTutorial} is a tutorial for the {@link Validator}.
 * 
 * @author Silvan
 * @month 2017-03
 * @lines 30
 */
public final class StringValidationTutorial {
	
	/**
	 * Lets the {@link Validator} validate the maximum length of a {@link String}.
	 */
	public static void main(String[] args) {
		
		//Lets the validator validate that the string 'Hello World!' is not null and not empty.
		Validator.assertThat("Hello World!").isNotEmpty();
		
		//Lets the validator validate that he string 'Hello World!' has the max length 20.
		Validator.assertThat("Hello World!").isNotLongerThan(12);
	}
	
	/**
	 * Avoids that an instance of the {@link Validator} can be created.
	 */
	private StringValidationTutorial() {}
}
