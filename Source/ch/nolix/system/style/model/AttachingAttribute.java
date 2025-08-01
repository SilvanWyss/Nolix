package ch.nolix.system.style.model;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.system.element.base.AbstractElement;
import ch.nolix.system.style.tool.AttachingAttributeValidator;
import ch.nolix.systemapi.style.model.IAttachingAttribute;
import ch.nolix.systemapi.style.tool.IAttachingAttributeValidator;

public final class AttachingAttribute extends AbstractElement implements IAttachingAttribute {

  private static final IAttachingAttributeValidator ATTACHING_ATTRIBUTE_VALIDATOR = new AttachingAttributeValidator();

  private final String optionalTag;

  private final Node value;

  private AttachingAttribute(final INode<?> value) {

    Validator.assertThat(value).thatIsNamed(LowerCaseVariableCatalog.VALUE).isNotNull();

    this.optionalTag = null;
    this.value = Node.fromNode(value);
  }

  private AttachingAttribute(final String tag, final INode<?> value) {

    Validator.assertThat(tag).thatIsNamed(LowerCaseVariableCatalog.TAG).isNotBlank();
    Validator.assertThat(value).thatIsNamed(LowerCaseVariableCatalog.VALUE).isNotNull();

    this.optionalTag = tag;
    this.value = Node.fromNode(value);
  }

  private AttachingAttribute(final String tag, final String value) {

    Validator.assertThat(tag).thatIsNamed(LowerCaseVariableCatalog.TAG).isNotBlank();
    Validator.assertThat(value).thatIsNamed(LowerCaseVariableCatalog.VALUE).isNotNull();

    this.optionalTag = tag;
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

  //For a better performance, this implementation does not use all available comfort methods.
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
        forValue(attributes.getStoredAtOneBasedIndex(1));
      case 2 ->
        new AttachingAttribute(
          attributes.getStoredAtOneBasedIndex(1).toString(),
          attributes.getStoredAtOneBasedIndex(2));
      default ->
        throw //
        InvalidArgumentException.forArgumentAndArgumentName(specification, LowerCaseVariableCatalog.SPECIFICATION);
    };
  }

  @Override
  public IContainer<INode<?>> getAttributes() {

    final ILinkedList<INode<?>> attributes = LinkedList.createEmpty();

    if (optionalTag != null) {
      attributes.addAtEnd(Node.withHeaderAndChildNode(PascalCaseVariableCatalog.TAG, optionalTag));
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

  //For a better performance, this implementation does not use all available comfort methods.
  @Override
  public IAttachingAttribute withValue(String value) {

    if (optionalTag != null) {
      return new AttachingAttribute(optionalTag, value);
    }

    return forValue(value);
  }
}
