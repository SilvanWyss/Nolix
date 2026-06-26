/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.property.extension;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.misc.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.systemapi.element.mutableelement.IRespondingMutableElement;
import ch.nolix.systemapi.property.exension.IExtension;

/**
 * @author Silvan Wyss
 * @param <E> the type of the extension of a {@link Extension}.
 */
public final class Extension<E extends IRespondingMutableElement<E>> implements IExtension<E> {
  private final E memberExtension;

  /**
   * Creates a new {@link Extension} with the given extension.
   * 
   * @param extension
   * @throws RuntimeException if the given extension is null.
   */
  private Extension(final E extension) {
    Validator.assertThat(extension).thatIsNamed(LowerCaseVariableNameCatalog.EXTENSION).isNotNull();

    this.memberExtension = extension;
  }

  /**
   * @param extension
   * @param <T>       is the type of the extension of the created
   *                  {@link Extension}.
   * @return a new {@link Extension} with the given extension.
   * @throws RuntimeException if the given extension is null.
   */
  public static <T extends IRespondingMutableElement<T>> Extension<T> withExtension(final T extension) {
    return new Extension<>(extension);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean addedOrChangedAttribute(final INode<?> attribute) {
    return memberExtension.addedOrChangedAttribute(attribute);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillUpAttributesIntoList(final ILinkedList<INode<?>> list) {
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
