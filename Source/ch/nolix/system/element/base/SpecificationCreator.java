//package declaration
package ch.nolix.system.element.base;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.baseapi.IElement;

//class
public final class SpecificationCreator {

  //method
  public INode<?> getSpecificationOfElement(final IElement element) {
    return Node.withHeaderAndChildNodes(getSpecificationHeaderOfElement(element), element.getAttributes());
  }

  //method
  private String getSpecificationHeaderOfElement(final IElement element) {
    return element.getClass().getSimpleName();
  }
}
