package ch.nolix.systemapi.style.model;

import ch.nolix.coreapi.attribute.mandatoryattribute.IValueHolder;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.element.base.IElement;

public interface IAttachingAttribute extends IElement, IValueHolder<INode<?>> {

  String getTag();

  boolean hasTag();

  boolean hasTag(Enum<?> tag);

  IAttachingAttribute withValue(String value);
}
