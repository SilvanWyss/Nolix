//package declaration
package ch.nolix.common.xml;

import ch.nolix.common.commontypehelper.StringHelper;
import ch.nolix.common.constant.CharacterCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.mutableoptionalattributeapi.OptionalNamable;
import ch.nolix.common.mutableoptionalattributeapi.OptionalValueable;
import ch.nolix.common.validator.Validator;

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
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.NAME);
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
		
		this.name = Validator.assertThat(name).thatIsNamed(VariableNameCatalogue.NAME).isNotBlank().andReturn();
		
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
		.append('<')
		.append(getName())
		.append('>');
		
		return stringBuilder.toString();
	}
	
	//method
	private void supposeHasValue() {
		if (!hasValue()) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.VALUE);
		}
	}
	
	//method
	private String toFormatedString(final int leadingTabulatorCount) {
		
		final var stringBuilder = new StringBuilder();
		
		stringBuilder
		.append(StringHelper.createTabulators(leadingTabulatorCount))
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
			}
			
			else {
				stringBuilder
				.append(CharacterCatalogue.NEW_LINE)
				.append(StringHelper.createTabulators(leadingTabulatorCount + 1))
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
			stringBuilder.append(StringHelper.createTabulators(leadingTabulatorCount));
		}
			
		stringBuilder
		.append("</")
		.append(getName())
		.append('>');
		
		return stringBuilder.toString();
	}
}
