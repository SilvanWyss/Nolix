package ch.nolix.system.element.styletool;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.system.element.style.AttachingAttribute;
import ch.nolix.systemapi.elementapi.styleapi.IAttachingAttribute;
import ch.nolix.systemapi.elementapi.styletoolapi.IAttributeMerger;

public final class AttributeMerger implements IAttributeMerger {

  @Override
  public IContainer<IAttachingAttribute> getAttributesMergedWithNewAttributes(
    final IContainer<? extends IAttachingAttribute> attributes,
    final IContainer<String> newAttributes) {

    final ILinkedList<IAttachingAttribute> mergedAttachingAttributes = LinkedList.createEmpty();
    final var additionalAttributes = LinkedList.fromIterable(newAttributes);

    for (final var a : attributes) {

      final var attributeHeader = a.getValue().getHeader();
      final var newAttributeContainer = newAttributes.getOptionalStoredFirst(na -> na.startsWith(attributeHeader));

      if (newAttributeContainer.isPresent()) {

        final var newAttribute = newAttributeContainer.get();

        additionalAttributes.removeFirstOccurrenceOf(newAttribute);
        mergedAttachingAttributes.addAtEnd(a.withValue(newAttribute));
      } else {
        mergedAttachingAttributes.addAtEnd(a);
      }
    }

    mergedAttachingAttributes.addAtEnd(additionalAttributes.to(AttachingAttribute::forValue));

    return mergedAttachingAttributes;
  }
}
