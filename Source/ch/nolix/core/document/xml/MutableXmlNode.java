package ch.nolix.core.document.xml;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.charactertool.CharacterCatalog;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.xml.IMutableXmlNode;
import ch.nolix.coreapi.document.xml.IXmlAttribute;
import ch.nolix.coreapi.document.xml.IXmlNode;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

public final class MutableXmlNode implements IMutableXmlNode {
  private String memberName;

  private String memberValue;

  private final LinkedList<IXmlAttribute> memberAttributes = LinkedList.createEmpty();

  private final LinkedList<IMutableXmlNode> memberChildNodes = LinkedList.createEmpty();

  private MutableXmlNode() {
  }

  public static MutableXmlNode createBlankMutableXmlNode() {
    return new MutableXmlNode();
  }

  public static MutableXmlNode fromXmlNode(final IXmlNode<?> pXmlNode) {
    final var mutableXmlNode = new MutableXmlNode();

    if (pXmlNode.hasName()) {
      mutableXmlNode.setName(pXmlNode.getName());
    }

    mutableXmlNode.addAttributes(pXmlNode.getAttributes());

    for (final var c : pXmlNode.getStoredChildNodes()) {
      mutableXmlNode.addChildNode(fromXmlNode(c));
    }

    if (pXmlNode.hasValue()) {
      mutableXmlNode.setValue(pXmlNode.getValue());
    }

    return mutableXmlNode;
  }

  private static String toFormatedString(final IMutableXmlNode mutableXmlNode, final int leadingTabulatorCount) {
    final var stringBuilder = new StringBuilder();

    stringBuilder
      .append(StringTool.createTabs(leadingTabulatorCount))
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
          .append(CharacterCatalog.NEW_LINE)
          .append(StringTool.createTabs(leadingTabulatorCount + 1))
          .append(mutableXmlNode.getValue())
          .append(CharacterCatalog.NEW_LINE);

      }
    }

    if (mutableXmlNode.containsChildNodes()) {
      for (final var c : mutableXmlNode.getStoredChildNodes()) {
        stringBuilder
          .append(CharacterCatalog.NEW_LINE)
          .append(toFormatedString(c, leadingTabulatorCount + 1));
      }

      stringBuilder.append(CharacterCatalog.NEW_LINE);
    }

    if (mutableXmlNode.containsChildNodes()) {
      stringBuilder.append(StringTool.createTabs(leadingTabulatorCount));
    }

    stringBuilder
      .append("</")
      .append(mutableXmlNode.getName())
      .append('>');

    return stringBuilder.toString();
  }

  @Override
  public MutableXmlNode addAttribute(final IXmlAttribute attribute) {
    memberAttributes.addAtEnd(attribute);

    return this;
  }

  @Override
  public MutableXmlNode addAttributes(final IXmlAttribute attribute, final IXmlAttribute... attributes) {
    addAttribute(attribute);

    return addAttributes(ContainerView.forArray(attributes));
  }

  //For a better performance, this implementation does not use all available comfort methods.
  public MutableXmlNode addAttributes(final Iterable<IXmlAttribute> attributes) {
    this.memberAttributes.addAtEnd(attributes);

    return this;
  }

  @Override
  public IMutableXmlNode addAttributeWithNameAndValue(final String name, final String value) {
    return addAttribute(new XmlAttribute(name, value));
  }

  @Override
  public MutableXmlNode addChildNode(final IMutableXmlNode childNode) {
    memberChildNodes.addAtEnd(childNode);

    return this;
  }

  @Override
  public MutableXmlNode addChildNodes(final IMutableXmlNode childNode, final IMutableXmlNode... childNodes) {
    addChildNode(childNode);

    return addChildNodes(ContainerView.forArray(childNodes));
  }

  //For a better performance, this implementation does not use all available comfort methods.
  public MutableXmlNode addChildNodes(final Iterable<IMutableXmlNode> childNodes) {
    this.memberChildNodes.addAtEnd(childNodes);

    return this;
  }

  @Override
  public boolean containsAttributes() {
    return memberAttributes.containsAny();
  }

  @Override
  public boolean containsChildNodes() {
    return memberChildNodes.containsAny();
  }

  @Override
  public IContainer<IXmlAttribute> getAttributes() {
    return memberAttributes;
  }

  public int getAttributeCount() {
    return memberAttributes.getCount();
  }

  public int getChildNodeCount() {
    return memberChildNodes.getCount();
  }

  @Override
  public String getName() {
    if (memberName == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.NAME);
    }

    return memberName;
  }

  @Override
  public IContainer<IMutableXmlNode> getStoredChildNodes() {
    return memberChildNodes;
  }

  @Override
  public String getValue() {
    supposeHasValue();

    return memberValue;
  }

  @Override
  public boolean hasMixedContent() {
    return (hasValue() && containsChildNodes());
  }

  @Override
  public boolean hasName() {
    return (memberName != null);
  }

  @Override
  public boolean hasValue() {
    return (memberValue != null);
  }

  public IMutableXmlNode removeAttributes() {
    memberAttributes.clear();

    return this;
  }

  public IMutableXmlNode removeChildNodes() {
    memberChildNodes.clear();

    return this;
  }

  @Override
  public void removeName() {
    memberName = null;
  }

  @Override
  public void removeValue() {
    memberValue = null;
  }

  @Override
  public IMutableXmlNode setName(final String name) {
    Validator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();

    this.memberName = name;

    return this;
  }

  @Override
  public IMutableXmlNode setValue(final String value) {
    Validator.assertThat(value).isNotEmpty();

    this.memberValue = value;

    return this;
  }

  public String toFormatedString() {
    return toFormatedString(this, 0);
  }

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
      for (final var c : getStoredChildNodes()) {
        stringBuilder
          .append(c.toString());
      }
    }

    stringBuilder
      .append("</")
      .append(getName())
      .append('>');

    return stringBuilder.toString();
  }

  private void supposeHasValue() {
    if (!hasValue()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.VALUE);
    }
  }
}
