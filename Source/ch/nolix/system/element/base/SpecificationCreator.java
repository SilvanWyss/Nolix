//package declaration
package ch.nolix.system.element.base;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.baseapi.IElement;

//class
public final class SpecificationCreator {

  //method
  public INode<?> getSpecificationOf(final IElement element) {
    return Node.withHeaderAndChildNodes(getSpecificationHeaderOf(element), element.getAttributes());
  }

  //method
  private String getSpecificationHeaderOf(final IElement element) {
    return element.getClass().getSimpleName();
  }
}
