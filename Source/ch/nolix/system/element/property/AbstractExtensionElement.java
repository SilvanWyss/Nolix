package ch.nolix.system.element.property;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.element.mutableelement.IRespondingMutableElement;
import ch.nolix.systemapi.element.property.IProperty;

public abstract class AbstractExtensionElement<E extends IRespondingMutableElement<E>> implements IProperty {

  private E internalExtensionElement;

  protected AbstractExtensionElement(final E internalExtensionElement) {
    internalSetExtensionElement(internalExtensionElement);
  }

  public final E getExtensionElement() {
    return internalExtensionElement;
  }

  public abstract boolean isExchangable();

  @Override
  public final boolean addedOrChangedAttribute(final INode<?> attribute) {
    return internalExtensionElement.addedOrChangedAttribute(attribute);
  }

  @Override
  public final void fillUpAttributesInto(ILinkedList<INode<?>> list) {
    list.addAtEnd(internalExtensionElement.getAttributes());
  }

  protected final void internalSetExtensionElement(final E internalExtensionElement) {

    Validator.assertThat(internalExtensionElement).thatIsNamed("extension element").isNotNull();

    if (this.internalExtensionElement != null && !isExchangable()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not exchangable");
    }

    this.internalExtensionElement = internalExtensionElement;
  }
}
