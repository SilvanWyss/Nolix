/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.property;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.element.mutableelement.IRespondingMutableElement;
import ch.nolix.systemapi.element.property.IProperty;

public abstract class AbstractExtensionElement<E extends IRespondingMutableElement<E>> implements IProperty {
  private E memberInternalExtensionElement;

  protected AbstractExtensionElement(final E internalExtensionElement) {
    internalSetExtensionElement(internalExtensionElement);
  }

  public final E getExtensionElement() {
    return memberInternalExtensionElement;
  }

  public abstract boolean isExchangable();

  @Override
  public final boolean addedOrChangedAttribute(final INode<?> attribute) {
    return memberInternalExtensionElement.addedOrChangedAttribute(attribute);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void fillUpAttributesInto(ILinkedList<INode<?>> list) {
    list.addAtEnd(memberInternalExtensionElement.getAttributes());
  }

  protected final void internalSetExtensionElement(final E internalExtensionElement) {
    Validator.assertThat(internalExtensionElement).thatIsNamed("extension element").isNotNull();

    if (memberInternalExtensionElement != null && !isExchangable()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not exchangable");
    }

    memberInternalExtensionElement = internalExtensionElement;
  }
}
