package ch.nolix.systemapi.element.base;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;

/**
 * @author Silvan Wyss
 */
public interface IStructureElement extends IElement {
  /**
   * @return the child {@link IStructureElement}s of the current
   *         {@link IStructureElement}.
   */
  IContainer<? extends IStructureElement> getChildStructureElements();

  /**
   * @return the structure specification of the current {@link IStructureElement}.
   *         A structure specification of a {@link IStructureElement} contains
   *         only the attributes of the {@link IStructureElement} that are
   *         {@link IStructureElement}s themselves.
   */
  INode<?> getStructureSpecification();
}
