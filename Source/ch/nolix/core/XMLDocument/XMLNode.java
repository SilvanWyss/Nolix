//package declaration
package ch.nolix.core.XMLDocument;

//own imports
import ch.nolix.core.bases.OptionalNamableElement;
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.helper.StringHelper;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidStateException.ArgumentMissesAttributeException;
import ch.nolix.core.skillAPI.Freezable;
import ch.nolix.core.skillAPI.OptionalValueable;
import ch.nolix.core.validator2.Validator;

//class
public final class XMLNode
extends OptionalNamableElement<XMLNode>
implements Freezable<XMLNode>, OptionalValueable<XMLNode, String> {
	
	//attribute
	private boolean frozen = false;
	
	//optional attribute
	private String value;
	
	//multi-attributes
	private final List<XMLAttribute> attributes = new List<XMLAttribute>();
	private final List<XMLNode> childNodes = new List<XMLNode>();
	
	//constructor
	public XMLNode() {}
	
	//constructor
	public XMLNode(final String name) {
		setName(name);
	}
	
	//constructor
	public XMLNode(final String name, final String value) {
		setName(name);
		setValue(value);
	}
	
	//method
	public XMLNode addAttribute(final String name, final String value) {
		return addAttribute(new XMLAttribute(name, value));
	}
	
	//method
	public XMLNode addAttribute(final XMLAttribute attribute) {
		
		supposeIsNotFrozen();
		attributes.addAtEnd(attribute);
		
		return this;
	}
	
	//method
	public XMLNode addAttributes(final XMLAttribute... attributes) {
		return addAttributes(new ReadContainer<XMLAttribute>(attributes));
	}
	
	//method
	public XMLNode addAttributes(final Iterable<XMLAttribute> attributes) {
		
		supposeIsNotFrozen();
		
		//For a better performance, this implementation does not use all comfortable methods.
		this.attributes.addAtEnd(attributes);
		
		return this;
	}
	
	//method
	public XMLNode addChildNode(final XMLNode childNode) {
		
		supposeIsNotFrozen();
		childNodes.addAtEnd(childNode);
		
		return this;
	}
	
	//method
	public XMLNode addChildNode(final XMLNode... childNodes) {
		return addChildNodes(new ReadContainer<XMLNode>(childNodes));
	}
	
	//method
	public XMLNode addChildNodes(final Iterable<XMLNode> childNodes) {
		
		supposeIsNotFrozen();
		
		//For a better performance, this implementation does not use all comfortable methods.
		this.childNodes.addAtEnd(childNodes);
		
		return this;
	}
	
	//method
	public boolean containsAttributes() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return attributes.containsAny();
	}
	
	//method
	public boolean containsChildNodes() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return childNodes.containsAny();
	}
	
	//method
	@Override
	public XMLNode freeze() {
		
		if (!isFrozen()) {
			frozen = true;
			childNodes.forEach(cn -> cn.freeze());
		}
		
		return this;
	}
	
	//method
	public int getAttributeCount() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return attributes.getSize();
	}
	
	//method
	public int getChildNodeCount() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return childNodes.getSize();
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
	public IContainer<XMLNode> getRefChildNodes() {
		return childNodes;
	}
	
	//method
	public boolean hasMixedContent() {
		return (hasValue() && containsChildNodes());
	}
	
	//method
	@Override
	public boolean hasValue() {
		return (value != null);
	}
	
	//method
	@Override
	public boolean isFrozen() {
		return frozen;
	}
	
	//method
	public XMLNode removeAttributes() {
		
		supposeIsNotFrozen();
		
		attributes.clear();
		
		return this;
	}
	
	//method
	public XMLNode removeChildNodes() {
		
		supposeIsNotFrozen();
		
		childNodes.clear();
		
		return this;
	}
	
	//method
	@Override
	public XMLNode removeValue() {
		
		supposeIsNotFrozen();
		
		value = null;
		
		return this;
	}
	
	//method
	@Override
	public XMLNode setValue(final String value) {
		
		Validator.suppose(value).isNotEmpty();
		supposeIsNotFrozen();
		
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
			throw new ArgumentMissesAttributeException(this, VariableNameCatalogue.VALUE);
		}
	}
	
	//method
	private void supposeIsNotFrozen() {
		if (isFrozen()) {
			throw new InvalidArgumentException(this, "is frozen");
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
