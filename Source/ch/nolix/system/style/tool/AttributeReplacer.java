package ch.nolix.system.style.tool;

import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.core.datastructure.pair.Pair;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.IArrayList;
import ch.nolix.coreapi.datastructure.pair.IPair;
import ch.nolix.systemapi.style.model.IAttachingAttribute;
import ch.nolix.systemapi.style.tool.IAttributeReplacer;

public final class AttributeReplacer implements IAttributeReplacer {
  @Override
  public IContainer<IAttachingAttribute> getReplacedAttributesFromAttributesAndAttributeReplacements(
    final IContainer<? extends IAttachingAttribute> attributes,
    final IContainer<IPair<String, String>> attributeReplacements) {
    final IArrayList<IAttachingAttribute> replacedAttributes = //
    ArrayList.withInitialCapacityFromSizeOfContainer(attributes);

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
    final IArrayList<IAttachingAttribute> replacedAttributes = //
    ArrayList.withInitialCapacityFromSizeOfContainer(attributes);

    final var localAttributeReplacements = //
    attributeReplacements.to(r -> new Pair<>(r.getStoredElement1().toString(), r.getStoredElement2()));

    for (final var a : attributes) {
      if (a.hasTag()) {
        final var attributeTag = a.getTag();

        final var attributeReplacement = //
        localAttributeReplacements.getOptionalStoredFirst(r -> r.getStoredElement1().equals(attributeTag));

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
