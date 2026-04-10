/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.property.extension;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.systemapi.element.mutableelement.IRespondingMutableElement;
import ch.nolix.systemapi.property.exension.IExtensionProperty;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the extension of a {@link ExtensionProperty}.
 */
public final class ExtensionProperty<E extends IRespondingMutableElement<E>> implements IExtensionProperty<E> {
  private E extension;

  /**
   * Creates a new {@link ExtensionProperty} with the given extension.
   * 
   * @param extension
   * @throws RuntimeException if the given extension is null.
   */
  private ExtensionProperty(final E extension) {
    Validator.assertThat(extension).thatIsNamed("extension").isNotNull();

    this.extension = extension;
  }

  /**
   * @param extension
   * @param <T>       is the type of the extension of the created
   *                  {@link ExtensionProperty}.
   * @return a new {@link ExtensionProperty} with the given extension.
   * @throws RuntimeException if the given extension is null.
   */
  public static <T extends IRespondingMutableElement<T>> ExtensionProperty<T> withExtension(final T extension) {
    return new ExtensionProperty<>(extension);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean addedOrChangedAttribute(final INode<?> attribute) {
    return extension.addedOrChangedAttribute(attribute);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillUpAttributesIntoList(final ILinkedList<INode<?>> list) {
    list.addAtEnd(extension.getAttributes());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredExtension() {
    return extension;
  }
}
