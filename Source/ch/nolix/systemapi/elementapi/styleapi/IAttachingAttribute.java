package ch.nolix.systemapi.elementapi.styleapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IValueHolder;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

public interface IAttachingAttribute extends IValueHolder<INode<?>> {

  Enum<?> getTag();

  boolean hasTag();

  boolean hasTag(Enum<?> tag);
}
