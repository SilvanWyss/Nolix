/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

/**
 * @author Silvan Wyss
 */
public interface IBaseReference extends IField {
  /**
   * @return the names of the {@link ITable}s the current {@link IBaseReference}
   *         can reference.
   */
  IWellOrderContainer<String> getReferenceableTableNames();
}
