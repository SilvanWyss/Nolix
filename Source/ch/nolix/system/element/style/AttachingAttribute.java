package ch.nolix.system.element.style;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;
import ch.nolix.system.element.base.Element;
import ch.nolix.systemapi.elementapi.styleapi.IAttachingAttribute;
import ch.nolix.systemapi.elementapi.styleapi.IAttachingAttributeValidator;

public final class AttachingAttribute extends Element implements IAttachingAttribute {

  private static final IAttachingAttributeValidator ATTACHING_ATTRIBUTE_VALIDATOR = new AttachingAttributeValidator();

  private final String optionalTag;

  private final Node value;

  private AttachingAttribute(final INode<?> value) {

    GlobalValidator.assertThat(value).thatIsNamed(LowerCaseVariableCatalogue.VALUE).isNotNull();

    this.optionalTag = null;
    this.value = Node.fromNode(value);
  }

  private AttachingAttribute(final String tag, final INode<?> value) {

    GlobalValidator.assertThat(tag).thatIsNamed(LowerCaseVariableCatalogue.TAG).isNotNull();
    GlobalValidator.assertThat(value).thatIsNamed(LowerCaseVariableCatalogue.VALUE).isNotNull();

    this.optionalTag = tag.toString();
    this.value = Node.fromNode(value);
  }

  private AttachingAttribute(final String tag, final String value) {

    GlobalValidator.assertThat(tag).thatIsNamed(LowerCaseVariableCatalogue.TAG).isNotNull();
    GlobalValidator.assertThat(value).thatIsNamed(LowerCaseVariableCatalogue.VALUE).isNotNull();

    this.optionalTag = tag.toString();
    this.value = Node.fromString(value);
  }

  public static AttachingAttribute forTagAndValue(final Enum<?> tag, final INode<?> value) {
    return new AttachingAttribute(tag.toString(), value);
  }

  public static AttachingAttribute forTagAndValue(final Enum<?> tag, final String value) {
    return new AttachingAttribute(tag.toString(), Node.fromString(value));
  }

  public static AttachingAttribute forValue(final INode<?> value) {
    return new AttachingAttribute(value);
  }

  public static AttachingAttribute forValue(final String value) {
    return new AttachingAttribute(Node.fromString(value));
  }

  //For a better performance, this implementation does not use all comfortable methods.
  public static AttachingAttribute fromAttachingAttribute(final IAttachingAttribute attachingAttribute) {

    if (attachingAttribute instanceof final AttachingAttribute concreteAttachingAttribute) {
      return concreteAttachingAttribute;
    }

    if (attachingAttribute.hasTag()) {
      return new AttachingAttribute(attachingAttribute.getTag(), attachingAttribute.getValue());
    }

    return forValue(attachingAttribute.getValue());
  }

  public static AttachingAttribute fromSpecification(final INode<?> specification) {

    final var attributes = specification.getStoredChildNodes();

    return //
    switch (attributes.getCount()) {
      case 1 ->
        forValue(attributes.getStoredAt1BasedIndex(1));
      case 2 ->
        new AttachingAttribute(attributes.getStoredAt1BasedIndex(1).toString(), attributes.getStoredAt1BasedIndex(2));
      default ->
        throw //
        InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.SPECIFICATION, specification);
    };
  }

  @Override
  public IContainer<INode<?>> getAttributes() {

    final ILinkedList<INode<?>> attributes = LinkedList.createEmpty();

    if (optionalTag != null) {
      attributes.addAtEnd(Node.withHeaderAndChildNode(PascalCaseVariableCatalogue.TAG, optionalTag));
    }

    attributes.addAtEnd(value);

    return attributes;
  }

  @Override
  public String getTag() {

    ATTACHING_ATTRIBUTE_VALIDATOR.assertHasTag(this);

    return optionalTag;
  }

  @Override
  public Node getValue() {
    return value;
  }

  @Override
  public boolean hasTag() {
    return (optionalTag != null);
  }

  @Override
  public boolean hasTag(final Enum<?> tag) {
    return //
    hasTag()
    && tag != null
    && getTag().equals(tag.toString());
  }

  //For a better performance, this implementation does not use all comfortable methods.
  @Override
  public IAttachingAttribute withValue(String value) {

    if (optionalTag != null) {
      return new AttachingAttribute(optionalTag, value);
    }

    return forValue(value);
  }
}
