package ch.nolix.systemapi.elementapi.styletoolapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.pairapi.IPair;
import ch.nolix.systemapi.elementapi.styleapi.IAttachingAttribute;

public interface IAttributeReplacer {

  IContainer<IAttachingAttribute> getReplacedAttributesFromAttributesAndAttributeReplacements(
    IContainer<? extends IAttachingAttribute> attributes,
    IContainer<IPair<String, String>> attributeReplacements);

  IContainer<IAttachingAttribute> getReplacedTaggedAttributesFromAttributesAndAttributeReplacements(
    IContainer<? extends IAttachingAttribute> attributes,
    IContainer<IPair<Enum<?>, String>> attributeReplacements);
}
