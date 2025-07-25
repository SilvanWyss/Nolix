package ch.nolix.systemapi.elementapi.styletoolapi;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.elementapi.styleapi.IAttachingAttribute;

public interface IAttributeMerger {

  IContainer<IAttachingAttribute> getAttributesMergedWithNewAttributes(
    IContainer<? extends IAttachingAttribute> attributes,
    IContainer<String> newAttributes);
}
