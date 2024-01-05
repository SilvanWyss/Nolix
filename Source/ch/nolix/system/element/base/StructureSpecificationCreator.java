//package declaration
package ch.nolix.system.element.base;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.baseapi.IStructureElement;

//class
public final class StructureSpecificationCreator {

  //method
  public INode<?> getStructureSpecificationOfElement(final IStructureElement element) {

    final var header = getSpecificationHeaderOfElement(element);
    final var attributes = element.getStoredChildStructureElements().to(this::getStructureSpecificationOfElement);

    return Node.withHeaderAndChildNodes(header, attributes);
  }

  //method
  private String getSpecificationHeaderOfElement(final IStructureElement element) {
    return element.getClass().getSimpleName();
  }
}
