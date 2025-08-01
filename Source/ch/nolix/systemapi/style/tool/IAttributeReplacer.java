package ch.nolix.systemapi.style.tool;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datastructure.pair.IPair;
import ch.nolix.systemapi.style.model.IAttachingAttribute;

public interface IAttributeReplacer {

  IContainer<IAttachingAttribute> getReplacedAttributesFromAttributesAndAttributeReplacements(
    IContainer<? extends IAttachingAttribute> attributes,
    IContainer<IPair<String, String>> attributeReplacements);

  IContainer<IAttachingAttribute> getReplacedTaggedAttributesFromAttributesAndAttributeReplacements(
    IContainer<? extends IAttachingAttribute> attributes,
    IContainer<IPair<Enum<?>, String>> attributeReplacements);
}
