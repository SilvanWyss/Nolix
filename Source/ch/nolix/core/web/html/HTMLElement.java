//package declaration
package ch.nolix.core.web.html;

import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlAttribute;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;

//class
public final class HTMLElement implements IHtmlElement<HTMLElement, HtmlAttribute> {
	
	//static method
	public static HTMLElement fromHTMLElement(final IHtmlElement<?, ?> pHTMLElement) {
		
		if (pHTMLElement instanceof HTMLElement lHTMLAttribute) {
			return lHTMLAttribute;
		}
		
		return
		withTypeAndAttributesAndChildElements(
			pHTMLElement.getType(),
			pHTMLElement.getOriAttributes(),
			pHTMLElement.getOriChildElements()
		);
	}
	
	//static method
	public static HTMLElement withType(final String type) {
		return new HTMLElement(type, new ImmutableList<>(), StringCatalogue.EMPTY_STRING, new ImmutableList<>());
	}
	
	//static method
	public static HTMLElement withTypeAndAttribute(final String type, final IHtmlAttribute attribute) {
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
		final IHtmlAttribute attribute,
		final IHtmlElement<?, ?> childElement
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
		final IContainer<? extends IHtmlAttribute> attributes
	) {
		return new HTMLElement(type, attributes, StringCatalogue.EMPTY_STRING, new ImmutableList<>());
	}
	
	//static method
	public static HTMLElement withTypeAndAttributesAndChildElement(
		final String type,
		final IContainer<? extends IHtmlAttribute> attributes,
		final IHtmlElement<?, ?> childElement
	) {
		
		final var childElements = new LinkedList<IHtmlElement<?, ?>>();
		childElements.addAtEnd(childElement);
		
		return new HTMLElement(type, attributes, StringCatalogue.EMPTY_STRING, childElements);
	}
	
	//static method
	public static HTMLElement withTypeAndAttributesAndChildElements(
		final String type,
		final IContainer<? extends IHtmlAttribute> attributes,
		final IContainer<? extends IHtmlElement<?, ?>> childElements
	) {
		return new HTMLElement(type, attributes, StringCatalogue.EMPTY_STRING, childElements);
	}
	
	//static method
	public static HTMLElement withTypeAndAttributesAndInnerText(
		final String type,
		final IContainer<? extends IHtmlAttribute> attributes,
		final String innerText
	) {
		return new HTMLElement(type, attributes, innerText, new ImmutableList<>());
	}
	
	//static method
	public static HTMLElement withTypeAndChildElement(final String type, final IHtmlElement<?, ?> childElement) {
		return
		new HTMLElement(
			type,
			new ImmutableList<>(),
			StringCatalogue.EMPTY_STRING,
			ImmutableList.withElement(HTMLElement.fromHTMLElement(childElement))
		);
	}
	
	//static method
	public static HTMLElement withTypeAndChildElements(
		final String type,
		final IContainer<? extends IHtmlElement<?, ?>> childElements
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
	private final IContainer<HtmlAttribute> attributes;
	
	//multi attribute
	private final IContainer<HTMLElement> childElements;
	
	//constructor
	private HTMLElement(
		final String type,
		final IContainer<? extends IHtmlAttribute> attributes,
		final String innerText,
		final IContainer<? extends IHtmlElement<?, ?>> childElements
	) {
		
		GlobalValidator.assertThat(type).thatIsNamed(LowerCaseCatalogue.TYPE).isNotBlank();
		GlobalValidator.assertThat(innerText).thatIsNamed("inner text").isNotNull();
		
		this.type = type;
		this.attributes = attributes.to(HtmlAttribute::fromHTMLAttribute);
		this.innerText = innerText;
		this.childElements = childElements.to(HTMLElement::fromHTMLElement);
	}
	
	//method
	@Override
	public boolean containsAttributes() {
		return getOriAttributes().containsAny();
	}
	
	//method
	@Override
	public boolean containsChildElements() {
		return getOriChildElements().containsAny();
	}
	
	//method
	@Override
	public String getInnerText() {
		return innerText;
	}
	
	//method
	@Override
	public IContainer<HtmlAttribute> getOriAttributes() {
		return attributes;
	}
	
	//method
	@Override
	public IContainer<HTMLElement> getOriChildElements() {
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
		return getOriAttributes().toStringWithSeparator(" ");
	}
	
	//method
	private String getChildElementsAsString() {
		return getOriChildElements().toStringWithSeparator("");
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
