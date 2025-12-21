package ch.nolix.systemapi.objectdata.model;

import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;

/**
 * @author Silvan Wyss
 * @param <V> is the type of the value a {@link IMultiValueFieldEntry}.
 */
public interface IMultiValueFieldEntry<V> extends IDatabaseObject {
  IMultiValueField<V> getStoredParentMultiValue();

  V getStoredValue();
}
