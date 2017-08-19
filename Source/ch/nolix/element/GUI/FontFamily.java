//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.invalidStateException.InvalidStateException;
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
	 * @return the Java font family that represents this font family.
	 */
	public String getJavaFontFamily() {
		
		//Enumerates this font family.
		switch (this) {
			case Arial:
				return "Arial";
			case Console:
				return java.awt.Font.MONOSPACED;
			case Papyrus:
				return toString();
			case Verdana:
				return toString();
			default:
				throw new InvalidStateException(this);
		}
	}
}
