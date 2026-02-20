/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.element.base;

import ch.nolix.baseapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 */
public interface IStructureElement extends IElement {
  /**
   * @return the child {@link IStructureElement}s of the current
   *         {@link IStructureElement}.
   */
  IContainer<? extends IStructureElement> getChildStructureElements();
}
