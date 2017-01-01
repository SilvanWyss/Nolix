/*
 * file:	AlphaValidatorBooleanTutorial1.java
 * author:	Silvan Wyss
 * month:	2016-12
 * lines:	30
 */

//package declaration
package ch.nolix.commonTutorial.alphaValidatorTutorial;

//own import
import ch.nolix.common.zetaValidator.ZetaValidator;

//package-visible class
/**
 * This class provides a tutorial for the alpha validator class.
 */
final class AlphaValidatorBooleanTutorial1 {

	//main method
	/**
	 * Lets the alpha validator once suppose that true is true and once suppose that true is false.
	 */
	public static void main(String[] args) {
		
		//Supposes that true is true, what makes that the alpha validator does not complain.
		ZetaValidator.supposeThat(true);
		
		//Supposes that true is false, what makes that the alpha validator throws a TrueArgumentException.
		ZetaValidator.supposeThatNot(true);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private AlphaValidatorBooleanTutorial1() {}
}
