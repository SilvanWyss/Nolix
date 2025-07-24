package ch.nolix.systemapi.objectdataapi.modelapi;

import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;

public interface IMultiValueFieldEntry<V> extends IDatabaseObject {

  IMultiValueField<V> getStoredParentMultiValue();

  V getStoredValue();
}
