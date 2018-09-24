//package declaration
package ch.nolix.core.enums;

import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.specificationAPI.ISpecifiedEnum;

//enum
/**
 * A {@link TextStyle} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 30
 */
public enum TextStyle implements ISpecifiedEnum {
	Default,
	Bold,
	BoldAndItalic,
	Italic;
	
	//constant
	public static String TYPE_NAME = "TextStyle";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link TextStyle} from the given specification.
	 * @throws NullArgumentException
	 * if the given specification is not valid.
	 */
	public static TextStyle createFromSpecification(
		final DocumentNodeoid specification
	) {
		return
		TextStyle.valueOf(specification.getOneAttributeAsString());
	}
}
