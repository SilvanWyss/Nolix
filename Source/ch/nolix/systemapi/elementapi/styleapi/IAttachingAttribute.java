package ch.nolix.systemapi.elementapi.styleapi;

import ch.nolix.coreapi.attribute.mandatoryattribute.IValueHolder;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.elementapi.baseapi.IElement;

public interface IAttachingAttribute extends IElement, IValueHolder<INode<?>> {

  String getTag();

  boolean hasTag();

  boolean hasTag(Enum<?> tag);

  IAttachingAttribute withValue(String value);
}
