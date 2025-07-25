package ch.nolix.systemapi.objectdata.model;

import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;

public interface IMultiValueFieldEntry<V> extends IDatabaseObject {

  IMultiValueField<V> getStoredParentMultiValue();

  V getStoredValue();
}
