package ch.nolix.system.element.base;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;
import ch.nolix.systemapi.elementapi.baseapi.IElement;

public final class SpecificationCreator {

  public INode<?> getSpecificationOfElement(final IElement element) {
    return Node.withHeaderAndChildNodes(getSpecificationHeaderOfElement(element), element.getAttributes());
  }

  private String getSpecificationHeaderOfElement(final IElement element) {
    return getSpecificationHeaderOfElementClass(element.getClass());
  }

  private String getSpecificationHeaderOfElementClass(final Class<?> elementClass) {

    if (!elementClass.isAnonymousClass()) {
      return elementClass.getSimpleName();
    }

    return PascalCaseVariableCatalog.ELEMENT;
  }
}
