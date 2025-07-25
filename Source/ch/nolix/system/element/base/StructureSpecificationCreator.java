package ch.nolix.system.element.base;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.element.base.IStructureElement;

public final class StructureSpecificationCreator {

  public INode<?> getStructureSpecificationOfElement(final IStructureElement element) {

    final var header = getSpecificationHeaderOfElement(element);
    final var attributes = element.getChildStructureElements().to(this::getStructureSpecificationOfElement);

    return Node.withHeaderAndChildNodes(header, attributes);
  }

  private String getSpecificationHeaderOfElement(final IStructureElement element) {
    return element.getClass().getSimpleName();
  }
}
