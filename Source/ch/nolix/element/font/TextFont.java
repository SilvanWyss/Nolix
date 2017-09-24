//package declaration
package ch.nolix.element.font;

//own import
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.SpecifiedByClassNameAndOneAttribute;

//enum
/**
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 50
 */
public enum TextFont implements SpecifiedByClassNameAndOneAttribute {
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
	
	//method
	/**
	 * @return the attribute of this text font.
	 */
	public final StandardSpecification getAttribute() {
		return
		StandardSpecification.createSpecificationWithHeaderOnly(super.toString());
	}
	
	//method
	/**
	 * @return a string representation of this text font.
	 */
	public final String toString() {
		return getSpecification().toString();
	}
}
