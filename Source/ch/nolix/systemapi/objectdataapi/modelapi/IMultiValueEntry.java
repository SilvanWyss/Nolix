package ch.nolix.systemapi.objectdataapi.modelapi;

import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;

public interface IMultiValueEntry<V> extends IDatabaseObject {

  IMultiValue<V> getStoredParentMultiValue();

  V getStoredValue();
}
