package ch.nolix.systemapi.objectdata.perstistence;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IMultiValueField;

/**
 * @author Silvan Wyss
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
