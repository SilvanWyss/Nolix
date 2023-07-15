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
import ch.nolix.coreapi.documentapi.xmlapi.IMutableXmlNode;
import ch.nolix.coreapi.documentapi.xmlapi.IXmlAttribute;
import ch.nolix.coreapi.documentapi.xmlapi.IXmlNode;

//class
public final class MutableXmlNode implements IMutableXmlNode {
	
	//static method
	public static MutableXmlNode fromXmlNode(final IXmlNode<?> pXmlNode) {
		
		final var mutableXmlNode = new MutableXmlNode();
		
		if (pXmlNode.hasName()) {
			mutableXmlNode.setName(pXmlNode.getName());
		}
		
		mutableXmlNode.addAttributes(pXmlNode.getAttributes());
		
		for (final var cn : pXmlNode.getStoredChildNodes()) {
			mutableXmlNode.addChildNode(fromXmlNode(cn));
		}
		
		if (pXmlNode.hasValue()) {
			mutableXmlNode.setValue(pXmlNode.getValue());
		}
		
		return mutableXmlNode;
	}
	
	//method
	private static String toFormatedString(final IMutableXmlNode mutableXmlNode, final int leadingTabulatorCount) {
		
		final var stringBuilder = new StringBuilder();
		
		stringBuilder
		.append(GlobalStringHelper.createTabulators(leadingTabulatorCount))
		.append('<')
		.append(mutableXmlNode.getName());
		
		if (mutableXmlNode.containsAttributes()) {
			stringBuilder
			.append(' ')
			.append(mutableXmlNode.getAttributes().toStringWithSeparator(' '));
		}
		
		stringBuilder.append('>');
		
		if (mutableXmlNode.hasValue()) {
			if (!mutableXmlNode.hasMixedContent()) {
				stringBuilder.append(mutableXmlNode.getValue());
			} else {
				stringBuilder
				.append(CharacterCatalogue.NEW_LINE)
				.append(GlobalStringHelper.createTabulators(leadingTabulatorCount + 1))
				.append(mutableXmlNode.getValue())
				.append(CharacterCatalogue.NEW_LINE);
				
			}
		}
		
		if (mutableXmlNode.containsChildNodes()) {
			
			for (final var cn : mutableXmlNode.getStoredChildNodes()) {
				stringBuilder
				.append(CharacterCatalogue.NEW_LINE)
				.append(toFormatedString(cn, leadingTabulatorCount + 1));
			}
			
			stringBuilder.append(CharacterCatalogue.NEW_LINE);
		}
		
		if (mutableXmlNode.containsChildNodes()) {
			stringBuilder.append(GlobalStringHelper.createTabulators(leadingTabulatorCount));
		}
			
		stringBuilder
		.append("</")
		.append(mutableXmlNode.getName())
		.append('>');
		
		return stringBuilder.toString();
	}

	//optional attribute
	private String name;
	
	//optional attribute
	private String value;
	
	//multi-attribute
	private final LinkedList<IXmlAttribute> attributes = new LinkedList<>();
	
	//multi-attribute
	private final LinkedList<IMutableXmlNode> childNodes = new LinkedList<>();
	
	//method
	@Override
	public MutableXmlNode addAttribute(final IXmlAttribute attribute) {
		
		attributes.addAtEnd(attribute);
		
		return this;
	}
	
	//method
	@Override
	public MutableXmlNode addAttributes(final IXmlAttribute firstAttribute, final IXmlAttribute... attributes) {
		
		addAttribute(firstAttribute);
		
		return addAttributes(ReadContainer.forArray(attributes));
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public MutableXmlNode addAttributes(final Iterable<IXmlAttribute> attributes) {
		
		this.attributes.addAtEnd(attributes);
		
		return this;
	}
	
	//method
	@Override
	public IMutableXmlNode addAttributeWithNameAndValue(final String name, final String value) {
		return addAttribute(new XmlAttribute(name, value));
	}

	//method
	@Override
	public MutableXmlNode addChildNode(final IMutableXmlNode childNode) {
		
		childNodes.addAtEnd(childNode);
		
		return this;
	}
	
	//method
	@Override
	public MutableXmlNode addChildNodes(final IMutableXmlNode firstChildNode, final IMutableXmlNode... childNodes) {
		
		addChildNode(firstChildNode);
		
		return addChildNodes(ReadContainer.forArray(childNodes));
	}
	
	//method
	public MutableXmlNode addChildNodes(final Iterable<IMutableXmlNode> childNodes) {
		
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
	public IContainer<IXmlAttribute> getAttributes() {
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
	public IContainer<IMutableXmlNode> getStoredChildNodes() {
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
	public IMutableXmlNode removeAttributes() {
		
		attributes.clear();
		
		return this;
	}
	
	//method
	public IMutableXmlNode removeChildNodes() {
		
		childNodes.clear();
		
		return this;
	}
	
	//method
	@Override
	public IMutableXmlNode removeName() {
		
		name = null;
		
		return this;
	}
	
	//method
	@Override
	public IMutableXmlNode removeValue() {
		
		value = null;
		
		return this;
	}
	
	//method
	@Override
	public IMutableXmlNode setName(final String name) {
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		
		this.name = name;
		
		return this;
	}
	
	//method
	@Override
	public IMutableXmlNode setValue(final String value) {
		
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
			for (final var cn : getStoredChildNodes()) {
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
