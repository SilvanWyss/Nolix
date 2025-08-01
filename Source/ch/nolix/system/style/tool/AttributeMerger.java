package ch.nolix.system.style.tool;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.system.style.model.AttachingAttribute;
import ch.nolix.systemapi.style.model.IAttachingAttribute;
import ch.nolix.systemapi.style.tool.IAttributeMerger;

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
