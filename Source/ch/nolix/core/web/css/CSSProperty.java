//package declaration
package ch.nolix.core.web.css;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICSSProperty;

//class
public final class CSSProperty implements ICSSProperty {
	
	//static method
	public static CSSProperty fromCSSProperty(final ICSSProperty pCSSProperty) {
		
		if (pCSSProperty instanceof CSSProperty) {
			return (CSSProperty)pCSSProperty;
		}
		
		return withNameAndValue(pCSSProperty.getName(), pCSSProperty.getValue());
	}
	
	//static method
	public static CSSProperty withNameAndValue(final String name, final double value) {
		return new CSSProperty(name, String.valueOf(value));
	}
	
	//static method
	public static CSSProperty withNameAndValue(final String name, final int value) {
		return new CSSProperty(name, String.valueOf(value));
	}
	
	//static method
	public static CSSProperty withNameAndValue(final String name, final String value) {
		return new CSSProperty(name, value);
	}
	
	//attribute
	private final String name;
	
	//attribute
	private final String value;
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	private CSSProperty(final String name, final String value) {
		
		if (name == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.KEY);
		}
		
		if (value == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.VALUE);
		}
		
		this.name = name;
		this.value = value;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
	
	//method
	@Override
	public String getValue() {
		return value;
	}
	
	//method
	@Override
	public String toString() {
		return (getName() + ": " + getValue() + ";");
	}
}
