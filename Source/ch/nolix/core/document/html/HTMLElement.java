//package declaration
package ch.nolix.core.document.html;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.htmlapi.IHTMLElement;

//class
public final class HTMLElement implements IHTMLElement<HTMLElement, HTMLAttribute> {
	
	//static method
	public static HTMLElement withTypeAndAttributes(final String type, final IContainer<HTMLAttribute> attributes) {
		return new HTMLElement(type, attributes, new ImmutableList<>());
	}
	
	//static method
	public static HTMLElement withTypeAndAttributesAndChildElements(
		final String type,
		final IContainer<HTMLAttribute> attributes,
		final IContainer<HTMLElement> childElements
	) {
		return new HTMLElement(type, attributes, childElements);
	}
	
	//static method
	public static HTMLElement withTypeAndChildElements(
		final String type,
		final IContainer<HTMLElement> childElements
	) {
		return new HTMLElement(type, new ImmutableList<>(), childElements);
	}
	
	//attribute
	private final String type;
	
	//multi-attribute
	private final ImmutableList<HTMLAttribute> attributes;
	
	//multi attribute
	private final ImmutableList<HTMLElement> childElements;
	
	//constructor
	private HTMLElement(
		final String type,
		final IContainer<HTMLAttribute> attributes,
		final IContainer<HTMLElement> childElements
	) {
		
		GlobalValidator.assertThat(type).thatIsNamed(LowerCaseCatalogue.TYPE).isNotBlank();
		
		this.type = type;
		this.childElements = ImmutableList.forIterable(childElements);
		this.attributes = ImmutableList.forIterable(attributes);
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
