package ch.nolix.system.element.property;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.mutableelementapi.IMutableElement;
import ch.nolix.systemapi.elementapi.propertyapi.IProperty;

public abstract class AbstractSubElement<E extends IMutableElement> implements IProperty {

  private final String attributePrefix;

  private E internalSubElement;

  protected AbstractSubElement(
    final String attributePrefix,
    final E internalSubElement) {

    GlobalValidator.assertThat(attributePrefix).thatIsNamed("attribute prefix").isNotBlank();

    this.attributePrefix = attributePrefix;
    internalSetSubElement(internalSubElement);
  }

  public String getAttributePrefix() {
    return attributePrefix;
  }

  public E getSubElement() {
    return internalSubElement;
  }

  public abstract boolean isExchangable();

  @Override
  public final boolean addedOrChangedAttribute(final INode<?> attribute) {

    if (attribute.hasHeader() && attribute.getHeader().startsWith(attributePrefix)) {

      internalSubElement.addOrChangeAttribute(
        Node.withHeaderAndChildNodes(
          attribute.getHeader().substring(attributePrefix.length()),
          attribute.getStoredChildNodes()));

      return true;
    }

    return false;
  }

  @Override
  public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
    for (final var a : internalSubElement.getAttributes()) {
      list.addAtEnd(
        Node.withHeaderAndChildNodes(attributePrefix + a.getHeader(), a.getStoredChildNodes()));
    }
  }

  protected final void internalSetSubElement(final E internalSubElement) {

    GlobalValidator.assertThat(internalSubElement).thatIsNamed("sub element").isNotNull();

    if (this.internalSubElement != null && !isExchangable()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not exchangable");
    }

    this.internalSubElement = internalSubElement;
  }
}
