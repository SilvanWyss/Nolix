//package declaration
package ch.nolix.element.font;

//own import
import ch.nolix.core.specification.StandardSpecification;

//enum
/**
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 40
 */
public enum FontFamily {
	Arial,
	Console,
	Papyrus,
	Verdana;

	//method
	/**
	 * @return a new specification of this font family.
	 */
	public StandardSpecification getSpecification() {
		return StandardSpecification.createSpecificationWithHeaderOnly(toString());
	}
	
	//method
	/**
	 * @return the Java font family of this font family.
	 */
	public String getJavaFontFamily() {
		
		//Enumerates this font family.
		switch (this) {
			case Console:
				return java.awt.Font.MONOSPACED;
			default:
				return toString();
		}
	}
}
