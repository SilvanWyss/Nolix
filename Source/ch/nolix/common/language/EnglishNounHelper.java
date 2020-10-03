//package declaration
package ch.nolix.common.language;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;

//class
public final class EnglishNounHelper {
	
	//static method
	public static String getPlural(final String noun) {
		
		Validator.assertThat(noun).thatIsNamed(VariableNameCatalogue.NOUN).isNotBlank();
		
		switch (noun) {
			case "child":
				return "children";
			case "foot":
				return "feet";
			case "goose":
				return "geese";
			case "mouse":
				return "mice";
			case "tooth":
				return "teeth";
			default:
		}
		
		if (noun.endsWith("man")) {
			return (noun.substring(0, noun.length() - 3) + "men");
		}
		
		if (noun.endsWith("ef")) {
			return (noun.substring(0, noun.length() - 1) + "ves");
		}
		
		if (noun.endsWith("ss") || noun.endsWith("x")) {
			return (noun + "es");
		}
		
		if (
			noun.endsWith("ay")
			|| noun.endsWith("ey")
			|| noun.endsWith("iy")
			|| noun.endsWith("oy") 
			|| noun.endsWith("uy")
			|| noun.endsWith("ff")
		) {
			return (noun + "s");
		}
				
		if (noun.endsWith("y")) {
			return (noun.substring(0, noun.length() - 1) + "ies");
		}
		
		if (noun.endsWith("f")) {
			return (noun.substring(0, noun.length() - 1) + "ves");
		}
		
		if (noun.endsWith("s")) {
			return (noun + "ses");
		}
		
		return (noun + "s");
	}
	
	//visibility-reducing constructor
	private EnglishNounHelper() {}
}
