//package declaration
package ch.nolix.core.document.xml;

//own imports
import ch.nolix.core.commontype.commontypeconstant.CharacterCatalogue;
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.xmlapi.IXMLNode;

//class
public final class MutableXMLNode implements IXMLNode<MutableXMLNode> {
	
	//optional attribute
	private String name;
	
	//optional attribute
	private String value;
	
	//multi-attribute
	private final LinkedList<XMLAttribute> attributes = new LinkedList<>();
	
	//multi-attribute
	private final LinkedList<MutableXMLNode> childNodes = new LinkedList<>();
	
	//method
	public MutableXMLNode addAttribute(final String name, final String value) {
		return addAttribute(new XMLAttribute(name, value));
	}
	
	//method
	public MutableXMLNode addAttribute(final XMLAttribute attribute) {
		
		attributes.addAtEnd(attribute);
		
		return this;
	}
	
	//method
	public MutableXMLNode addAttributes(final XMLAttribute... attributes) {
		return addAttributes(ReadContainer.forArray(attributes));
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public MutableXMLNode addAttributes(final Iterable<XMLAttribute> attributes) {
		
		this.attributes.addAtEnd(attributes);
		
		return this;
	}
	
	//method
	public MutableXMLNode addChildNode(final MutableXMLNode childNode) {
		
		childNodes.addAtEnd(childNode);
		
		return this;
	}
	
	//method
	public MutableXMLNode addChildNode(final MutableXMLNode... childNodes) {
		return addChildNodes(ReadContainer.forArray(childNodes));
	}
	
	//method
	public MutableXMLNode addChildNodes(final Iterable<MutableXMLNode> childNodes) {
		
		//For a better performance, this implementation does not use all comfortable methods.
		this.childNodes.addAtEnd(childNodes);
		
		return this;
	}
	
	//method
	@Override
	public boolean containsAttributes() {
		return attributes.containsAny();
	}
	
	//method
	@Override
	public boolean containsChildNodes() {
		return childNodes.containsAny();
	}
	
	//method
	public IContainer<XMLAttribute> getAttributes() {
		return attributes;
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
	public String getName() {
		
		if (name == null) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.NAME);
		}
		
		return name;
	}
	
	//method
	@Override
	public String getNameInQuotes() {
		return GlobalStringHelper.getInQuotes(getName());
	}
	
	//method
	@Override
	public IContainer<MutableXMLNode> getRefChildNodes() {
		return childNodes;
	}
	
	//method
	public String getValue() {
		
		supposeHasValue();
		
		return value;
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
	public boolean hasName(final String name) {
		
		if (!hasName()) {
			return false;
		}
		
		return getName().equals(name);
	}
	
	//method
	public boolean hasValue() {
		return (value != null);
	}
	
	//method
	public MutableXMLNode removeAttributes() {
		
		attributes.clear();
		
		return this;
	}
	
	//method
	public MutableXMLNode removeChildNodes() {
		
		childNodes.clear();
		
		return this;
	}
	
	//method
	@Override
	public MutableXMLNode removeName() {
		
		name = null;
		
		return this;
	}
	
	//method
	public MutableXMLNode removeValue() {
		
		value = null;
		
		return this;
	}
	
	//method
	@Override
	public MutableXMLNode setName(final String name) {
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		
		this.name = name;
		
		return this;
	}
	
	//method
	public MutableXMLNode setValue(final String value) {
		
		GlobalValidator.assertThat(value).isNotEmpty();
		
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
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.VALUE);
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
