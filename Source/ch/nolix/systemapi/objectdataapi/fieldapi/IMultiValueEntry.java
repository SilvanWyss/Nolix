package ch.nolix.systemapi.objectdataapi.fieldapi;

import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;

public interface IMultiValueEntry<V> extends IDatabaseObject {

  IMultiValue<V> getStoredParentMultiValue();

  V getStoredValue();
}
