package ch.nolix.systemapi.objectdataapi.modelapi;

import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;

public interface IMultiValueEntry<V> extends IDatabaseObject {

  IMultiValue<V> getStoredParentMultiValue();

  V getStoredValue();
}
