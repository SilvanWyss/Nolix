package ch.nolix.systemapi.elementapi.styleapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IValueHolder;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.baseapi.IElement;

public interface IAttachingAttribute extends IElement, IValueHolder<INode<?>> {

  Enum<?> getTag();

  boolean hasTag();

  boolean hasTag(Enum<?> tag);
}
