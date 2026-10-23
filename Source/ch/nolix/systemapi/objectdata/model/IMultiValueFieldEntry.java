/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.systemapi.database.databaseobject.DatabaseObject;

/**
 * @author Silvan Wyss
 * @param <V> the type of the value a {@link IMultiValueFieldEntry}.
 */
public interface IMultiValueFieldEntry<V> extends DatabaseObject {
  /**
   * @return the parent {@link IMultiValueField} of the current
   *         {@link IMultiValueFieldEntry}
   */
  IMultiValueField<V> getStoredParentMultiValue();

  /**
   * @return the value of the current {@link IMultiValueFieldEntry}
   */
  V getStoredValue();
}
