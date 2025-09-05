package ch.nolix.systemapi.style.tool;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.style.model.IAttachingAttribute;

public interface IAttributeMerger {
  IContainer<IAttachingAttribute> getAttributesMergedWithNewAttributes(
    IContainer<? extends IAttachingAttribute> attributes,
    IContainer<String> newAttributes);
}
