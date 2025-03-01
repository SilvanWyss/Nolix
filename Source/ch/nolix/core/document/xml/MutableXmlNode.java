package ch.nolix.core.document.xml;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.IStringTool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.xmlapi.IMutableXmlNode;
import ch.nolix.coreapi.documentapi.xmlapi.IXmlAttribute;
import ch.nolix.coreapi.documentapi.xmlapi.IXmlNode;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.CharacterCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public final class MutableXmlNode implements IMutableXmlNode {

  private static final IStringTool STRING_TOOL = new StringTool();

  private String name;

  private String value;

  private final LinkedList<IXmlAttribute> attributes = LinkedList.createEmpty();

  private final LinkedList<IMutableXmlNode> childNodes = LinkedList.createEmpty();

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

    for (final var cn : pXmlNode.getStoredChildNodes()) {
      mutableXmlNode.addChildNode(fromXmlNode(cn));
    }

    if (pXmlNode.hasValue()) {
      mutableXmlNode.setValue(pXmlNode.getValue());
    }

    return mutableXmlNode;
  }

  private static String toFormatedString(final IMutableXmlNode mutableXmlNode, final int leadingTabulatorCount) {

    final var stringBuilder = new StringBuilder();

    stringBuilder
      .append(STRING_TOOL.createTabs(leadingTabulatorCount))
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
          .append(STRING_TOOL.createTabs(leadingTabulatorCount + 1))
          .append(mutableXmlNode.getValue())
          .append(CharacterCatalog.NEW_LINE);

      }
    }

    if (mutableXmlNode.containsChildNodes()) {

      for (final var cn : mutableXmlNode.getStoredChildNodes()) {
        stringBuilder
          .append(CharacterCatalog.NEW_LINE)
          .append(toFormatedString(cn, leadingTabulatorCount + 1));
      }

      stringBuilder.append(CharacterCatalog.NEW_LINE);
    }

    if (mutableXmlNode.containsChildNodes()) {
      stringBuilder.append(STRING_TOOL.createTabs(leadingTabulatorCount));
    }

    stringBuilder
      .append("</")
      .append(mutableXmlNode.getName())
      .append('>');

    return stringBuilder.toString();
  }

  @Override
  public MutableXmlNode addAttribute(final IXmlAttribute attribute) {

    attributes.addAtEnd(attribute);

    return this;
  }

  @Override
  public MutableXmlNode addAttributes(final IXmlAttribute attribute, final IXmlAttribute... attributes) {

    addAttribute(attribute);

    return addAttributes(ContainerView.forArray(attributes));
  }

  //For a better performance, this implementation does not use all available comfort methods.
  public MutableXmlNode addAttributes(final Iterable<IXmlAttribute> attributes) {

    this.attributes.addAtEnd(attributes);

    return this;
  }

  @Override
  public IMutableXmlNode addAttributeWithNameAndValue(final String name, final String value) {
    return addAttribute(new XmlAttribute(name, value));
  }

  @Override
  public MutableXmlNode addChildNode(final IMutableXmlNode childNode) {

    childNodes.addAtEnd(childNode);

    return this;
  }

  @Override
  public MutableXmlNode addChildNodes(final IMutableXmlNode childNode, final IMutableXmlNode... childNodes) {

    addChildNode(childNode);

    return addChildNodes(ContainerView.forArray(childNodes));
  }

  //For a better performance, this implementation does not use all available comfort methods.
  public MutableXmlNode addChildNodes(final Iterable<IMutableXmlNode> childNodes) {

    this.childNodes.addAtEnd(childNodes);

    return this;
  }

  @Override
  public boolean containsAttributes() {
    return attributes.containsAny();
  }

  @Override
  public boolean containsChildNodes() {
    return childNodes.containsAny();
  }

  @Override
  public IContainer<IXmlAttribute> getAttributes() {
    return attributes;
  }

  public int getAttributeCount() {
    return attributes.getCount();
  }

  public int getChildNodeCount() {
    return childNodes.getCount();
  }

  @Override
  public String getName() {

    if (name == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.NAME);
    }

    return name;
  }

  @Override
  public IContainer<IMutableXmlNode> getStoredChildNodes() {
    return childNodes;
  }

  @Override
  public String getValue() {

    supposeHasValue();

    return value;
  }

  @Override
  public boolean hasMixedContent() {
    return (hasValue() && containsChildNodes());
  }

  @Override
  public boolean hasName() {
    return (name != null);
  }

  @Override
  public boolean hasValue() {
    return (value != null);
  }

  public IMutableXmlNode removeAttributes() {

    attributes.clear();

    return this;
  }

  public IMutableXmlNode removeChildNodes() {

    childNodes.clear();

    return this;
  }

  @Override
  public IMutableXmlNode removeName() {

    name = null;

    return this;
  }

  @Override
  public IMutableXmlNode removeValue() {

    value = null;

    return this;
  }

  @Override
  public IMutableXmlNode setName(final String name) {

    Validator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();

    this.name = name;

    return this;
  }

  @Override
  public IMutableXmlNode setValue(final String value) {

    Validator.assertThat(value).isNotEmpty();

    this.value = value;

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

  private void supposeHasValue() {
    if (!hasValue()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.VALUE);
    }
  }
}
