//package declaration
package ch.nolix.element.textFormat;

//own imports
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.element.baseAPI.IElementEnum;

//enum
/**
 * A {@link Font} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 50
 */
public enum Font implements IElementEnum {
	Arial,
	ComicSansMS,
	Console,
	Papyrus,
	Verdana;
	
	//constant
	public static final String TYPE_NAME = "Font";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link Font} from the given specification.
	 * @throws InvalidArgumentException
	 * if the given specification is not valid.
	 */
	public static Font createFromSpecification(
		final DocumentNodeoid specification
	) {
		return Font.valueOf(specification.getOneAttributeAsString());
	}
	
	//method
	/**
	 * @return the Java font family of this {@link Font}.
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
