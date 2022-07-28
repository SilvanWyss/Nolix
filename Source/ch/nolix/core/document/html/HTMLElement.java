//package declaration
package ch.nolix.core.document.html;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.htmlapi.IHTMLAttribute;
import ch.nolix.coreapi.documentapi.htmlapi.IHTMLElement;

//class
public final class HTMLElement implements IHTMLElement<HTMLElement, HTMLAttribute> {
	
	//static method
	public static HTMLElement fromHTMLElement(final IHTMLElement<?, ?> pHTMLElement) {
		
		if (pHTMLElement instanceof HTMLElement) {
			return (HTMLElement)pHTMLElement;
		}
		
		return
		withTypeAndAttributesAndChildElements(
			pHTMLElement.getType(),
			pHTMLElement.getRefAttributes(),
			pHTMLElement.getRefChildElements()
		);
	}
	
	//static method
	public static HTMLElement withTypeAndAttributes(
		final String type,
		final IContainer<? extends IHTMLAttribute> attributes
	) {
		return new HTMLElement(type, attributes, new ImmutableList<>());
	}
	
	//static method
	public static HTMLElement withTypeAndAttributesAndChildElements(
		final String type,
		final IContainer<? extends IHTMLAttribute> attributes,
		final IContainer<? extends IHTMLElement<?, ?>> childElements
	) {
		return new HTMLElement(type, attributes, childElements);
	}
	
	//static method
	public static HTMLElement withTypeAndChildElements(
		final String type,
		final IContainer<? extends IHTMLElement<?, ?>> childElements
	) {
		return new HTMLElement(type, new ImmutableList<>(), childElements);
	}
	
	//attribute
	private final String type;
	
	//multi-attribute
	private final IContainer<HTMLAttribute> attributes;
	
	//multi attribute
	private final IContainer<HTMLElement> childElements;
	
	//constructor
	private HTMLElement(
		final String type,
		final IContainer<? extends IHTMLAttribute> attributes,
		final IContainer<? extends IHTMLElement<?, ?>> childElements
	) {
		
		GlobalValidator.assertThat(type).thatIsNamed(LowerCaseCatalogue.TYPE).isNotBlank();
		
		this.type = type;
		this.childElements = childElements.to(HTMLElement::fromHTMLElement);
		this.attributes = attributes.to(HTMLAttribute::fromHTMLAttribute);
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
			return "<" + getType() + ">" + getChildElementsAsString() + "</" + getType() +">";
		}
		
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
	
	//method
	private String toStringWhenDoesNotContainChildElements() {
		
		if (!containsAttributes()) {
			return ("<" + getType() + " />");
		}
		
		return ("<" + getType() + " " + getAttributesAsString() + "/>");
	}
}
