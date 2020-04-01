//package declaration
package ch.nolix.common.XML;

//own imports
import ch.nolix.common.commonTypeHelpers.StringHelper;
import ch.nolix.common.constants.CharacterCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.containers.ReadContainer;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.mutableOptionalAttributeAPI.OptionalNamable;
import ch.nolix.common.mutableOptionalAttributeAPI.OptionalValueable;
import ch.nolix.common.skillAPI.Freezable;
import ch.nolix.common.validator.Validator;

//class
public final class XMLNode
implements Freezable<XMLNode>, OptionalNamable<XMLNode>, OptionalValueable<XMLNode, String> {
	
	//attribute
	private boolean frozen = false;
	
	//optional attribute
	private String name;
	private String value;
	
	//multi-attributes
	private final LinkedList<XMLAttribute> attributes = new LinkedList<>();
	private final LinkedList<XMLNode> childNodes = new LinkedList<>();
	
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
		return attributes.containsAny();
	}
	
	//method
	public boolean containsChildNodes() {
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
		return attributes.getSize();
	}
	
	//method
	public int getChildNodeCount() {
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
	public XMLNode removeName() {
		
		supposeIsNotFrozen();
		
		name = null;
		
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
	public XMLNode setName(final String name) {
		
		this.name = Validator.suppose(name).thatIsNamed(VariableNameCatalogue.NAME).isNotBlank().andReturn();
		
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
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.VALUE);
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
