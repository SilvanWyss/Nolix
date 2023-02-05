//package declaration
package ch.nolix.core.web.html;

import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLAttribute;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;

//class
public final class HTMLElement implements IHTMLElement<HTMLElement, HTMLAttribute> {
	
	//static method
	public static HTMLElement fromHTMLElement(final IHTMLElement<?, ?> pHTMLElement) {
		
		if (pHTMLElement instanceof HTMLElement lHTMLAttribute) {
			return lHTMLAttribute;
		}
		
		return
		withTypeAndAttributesAndChildElements(
			pHTMLElement.getType(),
			pHTMLElement.getRefAttributes(),
			pHTMLElement.getRefChildElements()
		);
	}
	
	//static method
	public static HTMLElement withType(final String type) {
		return new HTMLElement(type, new ImmutableList<>(), StringCatalogue.EMPTY_STRING, new ImmutableList<>());
	}
	
	//static method
	public static HTMLElement withTypeAndAttribute(final String type, final IHTMLAttribute attribute) {
		return
		new HTMLElement(
			type,
			ImmutableList.withElement(attribute),
			StringCatalogue.EMPTY_STRING,
			new ImmutableList<>()
		);
	}
	
	//static method
	public static HTMLElement withTypeAndAttributeAndChildElement(
		final String type,
		final IHTMLAttribute attribute,
		final IHTMLElement<?, ?> childElement
	) {
		return
		new HTMLElement(
			type,
			ImmutableList.withElement(attribute),
			StringCatalogue.EMPTY_STRING,
			ImmutableList.withElement(childElement)
		);
	}
	
	//static method
	public static HTMLElement withTypeAndAttributes(
		final String type,
		final IContainer<? extends IHTMLAttribute> attributes
	) {
		return new HTMLElement(type, attributes, StringCatalogue.EMPTY_STRING, new ImmutableList<>());
	}
	
	//static method
	public static HTMLElement withTypeAndAttributesAndChildElement(
		final String type,
		final IContainer<? extends IHTMLAttribute> attributes,
		final IHTMLElement<?, ?> childElement
	) {
		
		final var childElements = new LinkedList<IHTMLElement<?, ?>>();
		childElements.addAtEnd(childElement);
		
		return new HTMLElement(type, attributes, StringCatalogue.EMPTY_STRING, childElements);
	}
	
	//static method
	public static HTMLElement withTypeAndAttributesAndChildElements(
		final String type,
		final IContainer<? extends IHTMLAttribute> attributes,
		final IContainer<? extends IHTMLElement<?, ?>> childElements
	) {
		return new HTMLElement(type, attributes, StringCatalogue.EMPTY_STRING, childElements);
	}
	
	//static method
	public static HTMLElement withTypeAndAttributesAndInnerText(
		final String type,
		final IContainer<? extends IHTMLAttribute> attributes,
		final String innerText
	) {
		return new HTMLElement(type, attributes, innerText, new ImmutableList<>());
	}
	
	//static method
	public static HTMLElement withTypeAndChildElement(final String type, final IHTMLElement<?, ?> childElement) {
		return
		new HTMLElement(
			type,
			new ImmutableList<>(),
			StringCatalogue.EMPTY_STRING,
			ImmutableList.withElements(HTMLElement.fromHTMLElement(childElement))
		);
	}
	
	//static method
	public static HTMLElement withTypeAndChildElements(
		final String type,
		final IContainer<? extends IHTMLElement<?, ?>> childElements
	) {
		return new HTMLElement(type, new ImmutableList<>(), StringCatalogue.EMPTY_STRING, childElements);
	}
	
	//static method
	public static HTMLElement withTypeAndInnerText(final String type, final String innerText) {
		return new HTMLElement(type, new ImmutableList<>(), innerText, new ImmutableList<>());
	}
	
	//attribute
	private final String type;
	
	//attribute
	private final String innerText;
	
	//multi-attribute
	private final IContainer<HTMLAttribute> attributes;
	
	//multi attribute
	private final IContainer<HTMLElement> childElements;
	
	//constructor
	private HTMLElement(
		final String type,
		final IContainer<? extends IHTMLAttribute> attributes,
		final String innerText,
		final IContainer<? extends IHTMLElement<?, ?>> childElements
	) {
		
		GlobalValidator.assertThat(type).thatIsNamed(LowerCaseCatalogue.TYPE).isNotBlank();
		GlobalValidator.assertThat(innerText).thatIsNamed("inner text").isNotNull();
		
		this.type = type;
		this.attributes = attributes.to(HTMLAttribute::fromHTMLAttribute);
		this.innerText = innerText;
		this.childElements = childElements.to(HTMLElement::fromHTMLElement);
	}
	
	//method
	@Override
	public boolean containsAttributes() {
		return getRefAttributes().containsAny();
	}
	
	//method
	@Override
	public boolean containsChildElements() {
		return getRefChildElements().containsAny();
	}
	
	//method
	@Override
	public String getInnerText() {
		return innerText;
	}
	
	//method
	@Override
	public IContainer<HTMLAttribute> getRefAttributes() {
		return attributes;
	}
	
	//method
	@Override
	public IContainer<HTMLElement> getRefChildElements() {
		return childElements;
	}
	
	//method
	@Override
	public String getType() {
		return type;
	}
	
	//method
	@Override
	public String toString() {
		
		if (!containsChildElements()) {
			toStringWhenDoesNotContainChildElements();
		}
		
		return toStringWhenContainsChildElements();
	}
	
	//method
	private String getAttributesAsString() {
		return getRefAttributes().toString(" ");
	}
	
	//method
	private String getChildElementsAsString() {
		return getRefChildElements().toString("");
	}
	
	//method
	private String toStringWhenContainsChildElements() {
		
		if (!containsAttributes()) {
			
			if (getInnerText().isEmpty()) {
				return "<" + getType() + ">" + getChildElementsAsString() + "</" + getType() +">";
			}
			
			return "<" + getType() + ">" + getInnerText() + getChildElementsAsString() + "</" + getType() +">";
		}
		
		if (getInnerText().isEmpty()) {
			return
			"<"
			+ getType()
			+ " "
			+ getAttributesAsString()
			+ ">"
			+ getChildElementsAsString()
			+ "</"
			+ getType()
			+">";
		}
		
		return
		"<"
		+ getType()
		+ " "
		+ getAttributesAsString()
		+ ">"
		+ getInnerText()
		+ getChildElementsAsString()
		+ "</"
		+ getType()
		+">";
	}
	
	//method
	private String toStringWhenDoesNotContainChildElements() {
		
		if (!containsAttributes()) {
			
			if (getInnerText().isEmpty()) {
				return ("<" + getType() + " />");
			}
			
			return ("<" + getType() + ">" + getInnerText() + "</" + getType() + ">");
		}
		
		if (getInnerText().isEmpty()) {
			return ("<" + getType() + " " + getAttributesAsString() + "/>");
		}
		
		return ("<" + getType() + " " + getAttributesAsString() + ">" + getInnerText() + "</" + getType() + ">");
	}
}
