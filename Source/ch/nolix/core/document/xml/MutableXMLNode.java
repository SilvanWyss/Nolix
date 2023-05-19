//package declaration
package ch.nolix.core.document.xml;

//own imports
import ch.nolix.core.commontype.commontypeconstant.CharacterCatalogue;
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.xmlapi.IMutableXMLNode;
import ch.nolix.coreapi.documentapi.xmlapi.IXMLAttribute;
import ch.nolix.coreapi.documentapi.xmlapi.IXMLNode;

//class
public final class MutableXMLNode implements IMutableXMLNode {
	
	//static method
	public static MutableXMLNode fromXMLNode(final IXMLNode<?> pXMLNode) {
		
		final var mutableXMLNode = new MutableXMLNode();
		
		if (pXMLNode.hasName()) {
			mutableXMLNode.setName(pXMLNode.getName());
		}
		
		mutableXMLNode.addAttributes(pXMLNode.getAttributes());
		
		for (final var cn : pXMLNode.getOriChildNodes()) {
			mutableXMLNode.addChildNode(fromXMLNode(cn));
		}
		
		if (pXMLNode.hasValue()) {
			mutableXMLNode.setValue(pXMLNode.getValue());
		}
		
		return mutableXMLNode;
	}
	
	//method
	private static String toFormatedString(final IMutableXMLNode mutableXMLNode, final int leadingTabulatorCount) {
		
		final var stringBuilder = new StringBuilder();
		
		stringBuilder
		.append(GlobalStringHelper.createTabulators(leadingTabulatorCount))
		.append('<')
		.append(mutableXMLNode.getName());
		
		if (mutableXMLNode.containsAttributes()) {
			stringBuilder
			.append(' ')
			.append(mutableXMLNode.getAttributes().toStringWithSeparator(' '));
		}
		
		stringBuilder.append('>');
		
		if (mutableXMLNode.hasValue()) {
			if (!mutableXMLNode.hasMixedContent()) {
				stringBuilder.append(mutableXMLNode.getValue());
			} else {
				stringBuilder
				.append(CharacterCatalogue.NEW_LINE)
				.append(GlobalStringHelper.createTabulators(leadingTabulatorCount + 1))
				.append(mutableXMLNode.getValue())
				.append(CharacterCatalogue.NEW_LINE);
				
			}
		}
		
		if (mutableXMLNode.containsChildNodes()) {
			
			for (final var cn : mutableXMLNode.getOriChildNodes()) {
				stringBuilder
				.append(CharacterCatalogue.NEW_LINE)
				.append(toFormatedString(cn, leadingTabulatorCount + 1));
			}
			
			stringBuilder.append(CharacterCatalogue.NEW_LINE);
		}
		
		if (mutableXMLNode.containsChildNodes()) {
			stringBuilder.append(GlobalStringHelper.createTabulators(leadingTabulatorCount));
		}
			
		stringBuilder
		.append("</")
		.append(mutableXMLNode.getName())
		.append('>');
		
		return stringBuilder.toString();
	}

	//optional attribute
	private String name;
	
	//optional attribute
	private String value;
	
	//multi-attribute
	private final LinkedList<IXMLAttribute> attributes = new LinkedList<>();
	
	//multi-attribute
	private final LinkedList<IMutableXMLNode> childNodes = new LinkedList<>();
	
	//method
	@Override
	public MutableXMLNode addAttribute(final IXMLAttribute attribute) {
		
		attributes.addAtEnd(attribute);
		
		return this;
	}
	
	//method
	@Override
	public MutableXMLNode addAttributes(final IXMLAttribute firstAttribute, final IXMLAttribute... attributes) {
		
		addAttribute(firstAttribute);
		
		return addAttributes(ReadContainer.forArray(attributes));
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public MutableXMLNode addAttributes(final Iterable<IXMLAttribute> attributes) {
		
		this.attributes.addAtEnd(attributes);
		
		return this;
	}
	
	//method
	@Override
	public IMutableXMLNode addAttributeWithNameAndValue(final String name, final String value) {
		return addAttribute(new XMLAttribute(name, value));
	}

	//method
	@Override
	public MutableXMLNode addChildNode(final IMutableXMLNode childNode) {
		
		childNodes.addAtEnd(childNode);
		
		return this;
	}
	
	//method
	@Override
	public MutableXMLNode addChildNodes(final IMutableXMLNode firstChildNode, final IMutableXMLNode... childNodes) {
		
		addChildNode(firstChildNode);
		
		return addChildNodes(ReadContainer.forArray(childNodes));
	}
	
	//method
	public MutableXMLNode addChildNodes(final Iterable<IMutableXMLNode> childNodes) {
		
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
	@Override
	public IContainer<IXMLAttribute> getAttributes() {
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
	public IContainer<IMutableXMLNode> getOriChildNodes() {
		return childNodes;
	}
	
	//method
	@Override
	public String getValue() {
		
		supposeHasValue();
		
		return value;
	}
	
	//method
	@Override
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
	@Override
	public boolean hasValue() {
		return (value != null);
	}
	
	//method
	public IMutableXMLNode removeAttributes() {
		
		attributes.clear();
		
		return this;
	}
	
	//method
	public IMutableXMLNode removeChildNodes() {
		
		childNodes.clear();
		
		return this;
	}
	
	//method
	@Override
	public IMutableXMLNode removeName() {
		
		name = null;
		
		return this;
	}
	
	//method
	@Override
	public IMutableXMLNode removeValue() {
		
		value = null;
		
		return this;
	}
	
	//method
	@Override
	public IMutableXMLNode setName(final String name) {
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		
		this.name = name;
		
		return this;
	}
	
	//method
	@Override
	public IMutableXMLNode setValue(final String value) {
		
		GlobalValidator.assertThat(value).isNotEmpty();
		
		this.value = value;
		
		return this;
	}
	
	//method
	public String toFormatedString() {
		return toFormatedString(this, 0);
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
			.append(getAttributes().toStringWithSeparator(' '));
		}
		
		stringBuilder.append('>');
		
		if (hasValue()) {
			stringBuilder
			.append(getValue());
		}
		
		if (containsChildNodes()) {
			for (final var cn : getOriChildNodes()) {
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
}
