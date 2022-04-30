//package declaration
package ch.nolix.core.document.xml;

//own imports
import ch.nolix.core.attributeapi.mutableoptionalattributeapi.OptionalNamable;
import ch.nolix.core.attributeapi.mutableoptionalattributeapi.OptionalValueable;
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.constant.CharacterCatalogue;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.Validator;

//class
public final class XMLNode implements OptionalNamable<XMLNode>, OptionalValueable<XMLNode, String> {
	
	//optional attribute
	private String name;
	private String value;
	
	//multi-attributes
	private final LinkedList<XMLAttribute> attributes = new LinkedList<>();
	private final LinkedList<XMLNode> childNodes = new LinkedList<>();
	
	//constructor
	public XMLNode() {}
		
	//method
	public XMLNode addAttribute(final String name, final String value) {
		return addAttribute(new XMLAttribute(name, value));
	}
	
	//method
	public XMLNode addAttribute(final XMLAttribute attribute) {
		
		attributes.addAtEnd(attribute);
		
		return this;
	}
	
	//method
	public XMLNode addAttributes(final XMLAttribute... attributes) {
		return addAttributes(ReadContainer.forArray(attributes));
	}
	
	//method
	public XMLNode addAttributes(final Iterable<XMLAttribute> attributes) {
		
		//For a better performance, this implementation does not use all comfortable methods.
		this.attributes.addAtEnd(attributes);
		
		return this;
	}
	
	//method
	public XMLNode addChildNode(final XMLNode childNode) {
		
		childNodes.addAtEnd(childNode);
		
		return this;
	}
	
	//method
	public XMLNode addChildNode(final XMLNode... childNodes) {
		return addChildNodes(ReadContainer.forArray(childNodes));
	}
	
	//method
	public XMLNode addChildNodes(final Iterable<XMLNode> childNodes) {
		
		//For a better performance, this implementation does not use all comfortable methods.
		this.childNodes.addAtEnd(childNodes);
		
		return this;
	}
	
	//method
	public boolean containsAttributes() {
		return attributes.containsAny();
	}
	
	//method
	public boolean containsChildNodes() {
		return childNodes.containsAny();
	}
	
	//method
	public int getAttributeCount() {
		return attributes.getElementCount();
	}
	
	//method
	public int getChildNodeCount() {
		return childNodes.getElementCount();
	}
	
	//method
	@Override
	public String getValue() {
		
		supposeHasValue();
		
		return value;
	}
	
	//method
	public IContainer<XMLAttribute> getAttributes() {
		return attributes;
	}
	
	//method
	@Override
	public String getName() {
		
		if (name == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.NAME);
		}
		
		return name;
	}
	
	//method
	public IContainer<XMLNode> getRefChildNodes() {
		return childNodes;
	}
	
	//method
	public boolean hasMixedContent() {
		return (hasValue() && containsChildNodes());
	}
	
	//method
	@Override
	public boolean hasName() {
		return (name != null);
	}
	
	//method
	@Override
	public boolean hasValue() {
		return (value != null);
	}
	
	//method
	public XMLNode removeAttributes() {
		
		attributes.clear();
		
		return this;
	}
	
	//method
	public XMLNode removeChildNodes() {
		
		childNodes.clear();
		
		return this;
	}
	
	//method
	@Override
	public XMLNode removeName() {
		
		name = null;
		
		return this;
	}
	
	//method
	@Override
	public XMLNode removeValue() {
		
		value = null;
		
		return this;
	}
	
	//method
	@Override
	public XMLNode setName(final String name) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		
		this.name = name;
		
		return this;
	}
	
	//method
	@Override
	public XMLNode setValue(final String value) {
		
		Validator.assertThat(value).isNotEmpty();
		
		this.value = value;
		
		return this;
	}
	
	//method
	public String toFormatedString() {
		return toFormatedString(0);
	}
	
	//method
	@Override
	public String toString() {
		
		final var stringBuilder = new StringBuilder();
		
		stringBuilder
		.append('<')
		.append(getName());
		
		if (containsAttributes()) {
			stringBuilder
			.append(' ')
			.append(getAttributes().toString(' '));
		}
		
		stringBuilder.append('>');
		
		if (hasValue()) {
			stringBuilder
			.append(getValue());
		}
		
		if (containsChildNodes()) {
			for (final var cn : getRefChildNodes()) {
				stringBuilder
				.append(cn.toString());
			}
		}
		
		stringBuilder
		.append("</")
		.append(getName())
		.append('>');
		
		return stringBuilder.toString();
	}
	
	//method
	private void supposeHasValue() {
		if (!hasValue()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.VALUE);
		}
	}
	
	//method
	private String toFormatedString(final int leadingTabulatorCount) {
		
		final var stringBuilder = new StringBuilder();
		
		stringBuilder
		.append(GlobalStringHelper.createTabulators(leadingTabulatorCount))
		.append('<')
		.append(getName());
		
		if (containsAttributes()) {
			stringBuilder
			.append(' ')
			.append(getAttributes().toString(' '));
		}
		
		stringBuilder.append('>');
		
		if (hasValue()) {
			if (!hasMixedContent()) {
				stringBuilder.append(getValue());
			} else {
				stringBuilder
				.append(CharacterCatalogue.NEW_LINE)
				.append(GlobalStringHelper.createTabulators(leadingTabulatorCount + 1))
				.append(getValue())
				.append(CharacterCatalogue.NEW_LINE);
				
			}
		}
		
		if (containsChildNodes()) {
			
			for (final var cn : getRefChildNodes()) {
				stringBuilder
				.append(CharacterCatalogue.NEW_LINE)
				.append(cn.toFormatedString(leadingTabulatorCount + 1));
			}
			
			stringBuilder.append(CharacterCatalogue.NEW_LINE);
		}
		
		if (containsChildNodes()) {
			stringBuilder.append(GlobalStringHelper.createTabulators(leadingTabulatorCount));
		}
			
		stringBuilder
		.append("</")
		.append(getName())
		.append('>');
		
		return stringBuilder.toString();
	}
}
