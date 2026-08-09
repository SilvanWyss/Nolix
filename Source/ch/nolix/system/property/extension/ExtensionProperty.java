/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.property.extension;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.systemapi.element.mutableelement.RespondingMutableElement;
import ch.nolix.systemapi.property.exension.IExtensionProperty;

/**
 * @author Silvan Wyss
 * @param <E> the type of the extension of a {@link ExtensionProperty}.
 */
public final class ExtensionProperty<E extends RespondingMutableElement<E>> implements IExtensionProperty<E> {
  private final E memberExtension;

  /**
   * Creates a new {@link ExtensionProperty} with the given extension.
   * 
   * @param extension
   * @throws RuntimeException if the given extension is null
   */
  private ExtensionProperty(final E extension) {
    Validator.assertThat(extension).thatIsNamed(LowerCaseVariableNameCatalog.EXTENSION).isNotNull();

    this.memberExtension = extension;
  }

  /**
   * @param extension
   * @param <T>       the type of the extension of the created
   *                  {@link ExtensionProperty}
   * @return a new {@link ExtensionProperty} with the given extension
   * @throws RuntimeException if the given extension is null
   */
  public static <T extends RespondingMutableElement<T>> ExtensionProperty<T> withExtension(final T extension) {
    return new ExtensionProperty<>(extension);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean addedOrChangedAttribute(final Node<?> attribute) {
    return memberExtension.addedOrChangedAttribute(attribute);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillUpAttributesIntoList(final ILinkedList<Node<?>> list) {
    list.addAtEnd(memberExtension.getAttributes());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredExtension() {
    return memberExtension;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return true;
  }
}
