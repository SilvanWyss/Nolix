package ch.nolix.systemapi.elementapi.baseapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

/**
 * @author Silvan Wyss
 * @version 2024-01-05
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
