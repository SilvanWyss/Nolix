package ch.nolix.system.element.styletool;

import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.IArrayList;
import ch.nolix.coreapi.containerapi.pairapi.IPair;
import ch.nolix.systemapi.elementapi.styleapi.IAttachingAttribute;

public final class AttributeReplacer {

  public IContainer<IAttachingAttribute> getReplacedAttributesFromAttributesAndAttributeReplacements(
    final IContainer<? extends IAttachingAttribute> attributes,
    final IContainer<IPair<String, String>> attributeReplacements) {

    final IArrayList<IAttachingAttribute> replacedAttributes = ArrayList.withInitialCapacity(attributes.getCount());

    for (final var a : attributes) {

      final var attributeReplacement = //
      attributeReplacements.getOptionalStoredFirst(ar -> ar.getStoredElement1().equals(a));

      if (attributeReplacement.isPresent()) {

        final var replacingAttribute = a.withValue(attributeReplacement.get().getStoredElement2());

        replacedAttributes.addAtEnd(replacingAttribute);
      } else {
        replacedAttributes.addAtEnd(a);
      }
    }

    return replacedAttributes;
  }
}
