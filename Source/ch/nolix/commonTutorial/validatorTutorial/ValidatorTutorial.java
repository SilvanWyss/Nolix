package ch.nolix.commonTutorial.validatorTutorial;

import ch.nolix.common.validator.Validator;

/**
 * The {@link ValidatorTutorial} is a tutorial for the {@link Validator}.
 * Of the {@link ValidatorTutorial} an instance cannot be created.
 * 
 * @author Silvan Wys
 * @month 2017-05
 * @lines 40
 */
public final class ValidatorTutorial {
	
	public static void main(String[] args) {
		printAmount("apple", 5);
		printAmount("banana", 10);
		printAmount("cake", 2);
	}
	
	/**
	 * Prints out to the console the amount of the product with given productName.
	 * 
	 * @param productName
	 * @param amount
	 * @throws ArgumentIsNullException if the given productName is null.
	 * @throws EmptyArgumentException if the given productName is empty.
	 * @throws NegativArgumentException if the given amount is negative.
	 */
	private static void printAmount(final String productName, final int amount) {
		
		//Checks if the given productName is not null or empty.
		Validator.suppose(productName).thatIsNamed("product name").isNotEmpty();
		
		//Checks if the given amount is not negative.
		Validator.suppose(amount).thatIsNamed("amount").isNotNegative();
		
		System.out.println(productName + ": " + amount + " pieces");
	}
	
	/**
	 * Avoids that an instance of the {@link ValidatorTutorial} can be created.
	 */
	private ValidatorTutorial() {}
}
