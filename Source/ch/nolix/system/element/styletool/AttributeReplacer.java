package ch.nolix.system.element.styletool;

import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.IArrayList;
import ch.nolix.coreapi.containerapi.pairapi.IPair;
import ch.nolix.systemapi.elementapi.styleapi.IAttachingAttribute;
import ch.nolix.systemapi.elementapi.styletoolapi.IAttributeReplacer;

public final class AttributeReplacer implements IAttributeReplacer {

  @Override
  public IContainer<IAttachingAttribute> getReplacedAttributesFromAttributesAndAttributeReplacements(
    final IContainer<? extends IAttachingAttribute> attributes,
    final IContainer<IPair<String, String>> attributeReplacements) {

    final IArrayList<IAttachingAttribute> replacedAttributes = ArrayList.withInitialCapacity(attributes.getCount());

    for (final var a : attributes) {

      final var attributeValue = a.getValue().toString();

      final var attributeReplacement = //
      attributeReplacements.getOptionalStoredFirst(ar -> ar.getStoredElement1().equals(attributeValue));

      if (attributeReplacement.isPresent()) {

        final var replacingAttribute = a.withValue(attributeReplacement.get().getStoredElement2());

        replacedAttributes.addAtEnd(replacingAttribute);
      } else {
        replacedAttributes.addAtEnd(a);
      }
    }

    return replacedAttributes;
  }

  @Override
  public IContainer<IAttachingAttribute> getReplacedTaggedAttributesFromAttributesAndAttributeReplacements(
    final IContainer<? extends IAttachingAttribute> attributes,
    final IContainer<IPair<Enum<?>, String>> attributeReplacements) {

    final IArrayList<IAttachingAttribute> replacedAttributes = ArrayList.withInitialCapacity(attributes.getCount());

    for (final var a : attributes) {

      if (a.hasTag()) {

        final var attributeTag = a.getTag();

        final var attributeReplacement = //
        attributeReplacements.getOptionalStoredFirst(ar -> ar.getStoredElement1().equals(attributeTag));

        if (attributeReplacement.isPresent()) {

          final var replacingAttribute = a.withValue(attributeReplacement.get().getStoredElement2());

          replacedAttributes.addAtEnd(replacingAttribute);
        } else {
          replacedAttributes.addAtEnd(a);
        }
      } else {
        replacedAttributes.addAtEnd(a);
      }
    }

    return replacedAttributes;
  }
}
