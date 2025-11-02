package ch.nolix.systemapi.objectdata.changesetsaver;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IMultiValueField;

/**
 * @author Silvan Wyss
 * @version 2025-11-02
 */
public interface IMultiValueFieldSaver {
  /**
   * Saves the changes of the given multiValueField.
   * 
   * @param multiValueField
   * @param dataAndSchemaAdapter
   */
  void saveMultiValueFieldChanges(
    IMultiValueField<?> multiValueField,
    IDataAdapterAndSchemaReader dataAndSchemaAdapter);
}
