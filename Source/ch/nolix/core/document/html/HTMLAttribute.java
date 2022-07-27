package ch.nolix.core.document.html;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.documentapi.htmlapi.IHTMLAttribute;

//class
public final class HTMLAttribute implements IHTMLAttribute {
	
	//static method
	public static HTMLAttribute withNameAndValue(final String name, final String value) {
		return new HTMLAttribute(name, value);
	}
	
	//attribute
	private final String name;
	
	//attribute
	private final String value;
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	private HTMLAttribute(final String name, final String value) {
		
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
		return (getName() + "=\"" + getValue() + "\"");
	}
}

