package ch.nolix.system.element.styletool;

import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.IArrayList;
import ch.nolix.coreapi.containerapi.pairapi.IPair;

public final class AttributeReplacer {

  public IContainer<Node> getReplacedAttributesFromAttributesAndAttributeReplacements(
    final IContainer<String> attributes,
    final IContainer<IPair<String, String>> attributeReplacements) {

    final IArrayList<Node> replacedAttributes = ArrayList.withInitialCapacity(attributes.getCount());

    for (final var a : attributes) {

      final var attributeReplacement = //
      attributeReplacements.getOptionalStoredFirst(ar -> ar.getStoredElement1().equals(a));

      if (attributeReplacement.isPresent()) {
        replacedAttributes.addAtEnd(Node.fromString(attributeReplacement.get().getStoredElement2()));
      } else {
        replacedAttributes.addAtEnd(Node.fromString(a));
      }
    }

    return replacedAttributes;
  }
}
