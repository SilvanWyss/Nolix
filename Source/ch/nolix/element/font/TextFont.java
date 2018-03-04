//package declaration
package ch.nolix.element.font;

//own import
import ch.nolix.core.specificationInterfaces.ISpecifiedEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 30
 */
public enum TextFont implements ISpecifiedEnum {
	Arial,
	Console,
	Papyrus,
	Verdana;
	
	//type name
	public static final String TYPE_NAME = "TextFont";
	
	//method
	/**
	 * @return the Java font family of this font family.
	 */
	public String getJavaFontFamily() {
		
		//Enumerates this text font.
		switch (this) {
			case Console:
				return java.awt.Font.MONOSPACED;
			default:
				return toString();
		}
	}
}
