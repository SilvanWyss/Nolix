package ch.nolix.systemapi.objectdata.expectation;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.model.IMultiReference;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;
import ch.nolix.systemapi.objectdata.model.IReference;

/**
 * @author Silvan Wyss
 * @version 2025-11-05
 */
public interface IFieldExpectationAdder {
  /**
   * Adds expectation to the given field that its newly referenced entities
   * actually exist.
   * 
   * @param field
   * @param dataAndSchemaAdapter
   */
  void addExpectationThatNewlyReferencedEntitiesExist(IField field, IDataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Adds expectation to the given multiReference that its newly referenced
   * entities actually exist.
   * 
   * @param multiReference
   * @param dataAndSchemaAdapter
   */
  void addExpectationThatNewlyReferencedEntitiesExist(
    IMultiReference<IEntity> multiReference,
    IDataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Adds expectation to the given optionalReference that its newly referenced
   * entities actually exist.
   * 
   * @param optionalReference
   * @param dataAndSchemaAdapter
   */
  void addExpectationThatNewlyReferencedEntitiesExist(
    IOptionalReference<IEntity> optionalReference,
    IDataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Adds expectation to the given reference that its newly referenced entities
   * actually exist.
   * 
   * @param reference
   * @param dataAndSchemaAdapter
   */
  void addExpectationThatNewlyReferencedEntitiesExist(
    IReference<IEntity> reference,
    IDataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Adds expectation to the given field that its newly referenced entities
   * actually exist if the given field is new or edited.
   * 
   * @param field
   * @param dataAndSchemaAdapter
   */
  void addExpectationThatNewlyReferencedEntitiesExistIfFieldIsNewOrEdited(
    IField field,
    IDataAdapterAndSchemaReader dataAndSchemaAdapter);
}
