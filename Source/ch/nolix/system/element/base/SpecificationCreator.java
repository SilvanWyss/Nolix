package ch.nolix.system.element.base;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.systemapi.element.base.IElement;

public final class SpecificationCreator {
  private SpecificationCreator() {
  }

  public static INode<?> getSpecificationOfElement(final IElement element) {
    final var header = getSpecificationHeaderOfElement(element);
    final var attributes = element.getAttributes();

    return Node.withHeaderAndChildNodes(header, attributes);
  }

  private static String getSpecificationHeaderOfElement(final IElement element) {
    final var elementClass = element.getClass();

    return getSpecificationHeaderOfElementClass(elementClass);
  }

  private static String getSpecificationHeaderOfElementClass(final Class<?> elementClass) {
    if (!elementClass.isAnonymousClass()) {
      return elementClass.getSimpleName();
    }

    return PascalCaseVariableCatalog.ELEMENT;
  }
}
