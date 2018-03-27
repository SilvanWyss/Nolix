//package declaration
package ch.nolix.element.font;

//own imports
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.ISpecifiedEnum;

//enum
/**
 * A {@link TextFont} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 50
 */
public enum TextFont implements ISpecifiedEnum {
	Arial,
	ComicSansMS,
	Console,
	Papyrus,
	Verdana;
	
	//constant
	public static final String TYPE_NAME = "TextFont";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link TextFont} from the given specification.
	 * @throws InvalidArgumentException
	 * if the given specification is not valid.
	 */
	public static TextFont createFromSpecification(
		final Specification specification
	) {
		return TextFont.valueOf(specification.getOneAttributeAsString());
	}
	
	//method
	/**
	 * @return the Java font family of this {@link TextFont}.
	 */
	public String getSwingFontFamily() {
		
		//Enumerates this text font.
		switch (this) {
			case ComicSansMS:
				return "Comic Sans MS";
			case Console:
				return "Monospaced";
			default:
				return toString();
		}
	}
}
