package ch.nolix.systemapi.element.styletool;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.pair.IPair;
import ch.nolix.systemapi.element.style.IAttachingAttribute;

public interface IAttributeReplacer {

  IContainer<IAttachingAttribute> getReplacedAttributesFromAttributesAndAttributeReplacements(
    IContainer<? extends IAttachingAttribute> attributes,
    IContainer<IPair<String, String>> attributeReplacements);

  IContainer<IAttachingAttribute> getReplacedTaggedAttributesFromAttributesAndAttributeReplacements(
    IContainer<? extends IAttachingAttribute> attributes,
    IContainer<IPair<Enum<?>, String>> attributeReplacements);
}
