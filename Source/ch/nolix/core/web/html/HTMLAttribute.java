package ch.nolix.core.web.html;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlAttribute;

//class
public final class HTMLAttribute implements IHtmlAttribute {
	
	//static method
	public static HTMLAttribute fromHTMLAttribute(final IHtmlAttribute pHTMLAttribute) {
		
		if (pHTMLAttribute instanceof HTMLAttribute lHTMLAttribute) {
			return lHTMLAttribute;
		}
		
		return withNameAndValue(pHTMLAttribute.getName(), pHTMLAttribute.getValue());
	}
	
	//static method
	public static HTMLAttribute withNameAndValue(final String name, final int value) {
		return withNameAndValue(name, String.valueOf(value));
	}
	
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

