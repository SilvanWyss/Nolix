package ch.nolix.systemapi.elementapi.styleapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IValueHolder;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.baseapi.IElement;

public interface IAttachingAttribute extends IElement, IValueHolder<INode<?>> {

  String getTag();

  boolean hasTag();

  boolean hasTag(Enum<?> tag);

  IAttachingAttribute withValue(String value);
}
