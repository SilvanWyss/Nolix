//package declaration
package ch.nolix.core.web.css;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;

//class
public final class CssProperty implements ICssProperty {
	
	//static method
	public static CssProperty fromCSSProperty(final ICssProperty pCSSProperty) {
		
		if (pCSSProperty instanceof CssProperty lCSSProperty) {
			return lCSSProperty;
		}
		
		return withNameAndValue(pCSSProperty.getName(), pCSSProperty.getValue());
	}
	
	//static method
	public static CssProperty withNameAndValue(final String name, final double value) {
		return new CssProperty(name, String.valueOf(value));
	}
	
	//static method
	public static CssProperty withNameAndValue(final String name, final Enum<?> value) {
		return new CssProperty(name, value.toString());
	}
	
	//static method
	public static CssProperty withNameAndValue(final String name, final int value) {
		return new CssProperty(name, String.valueOf(value));
	}
	
	//static method
	public static CssProperty withNameAndValue(final String name, final String value) {
		return new CssProperty(name, value);
	}
	
	//attribute
	private final String name;
	
	//attribute
	private final String value;
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	private CssProperty(final String name, final String value) {
		
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
