//package declaration
package ch.nolix.system.element.specificationtool;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.specificationapi.Specified;

//class
public final class SpecificationCreator {

  //method
  public INode<?> getSpecificationOf(final Specified element) {
    return Node.withHeaderAndChildNodes(getSpecificationHeaderOf(element), element.getAttributes());
  }

  //method
  private String getSpecificationHeaderOf(final Specified element) {
    return element.getClass().getSimpleName();
  }
}
