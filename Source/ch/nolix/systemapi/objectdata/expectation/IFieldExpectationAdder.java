/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.expectation;

import ch.nolix.systemapi.middata.adapter.DataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.Field;
import ch.nolix.systemapi.objectdata.model.IMultiReference;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;
import ch.nolix.systemapi.objectdata.model.IReference;

/**
 * @author Silvan Wyss
 */
public interface IFieldExpectationAdder {
  /**
   * Adds expectation to the given field that its newly referenced entities
   * actually exist.
   * 
   * @param field
   * @param dataAndSchemaAdapter
   */
  void addExpectationThatNewlyReferencedEntitiesExist(Field field, DataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Adds expectation to the given multiReference that its newly referenced
   * entities actually exist.
   * 
   * @param multiReference
   * @param dataAndSchemaAdapter
   */
  void addExpectationThatNewlyReferencedEntitiesExist(
    IMultiReference<? extends IEntity> multiReference,
    DataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Adds expectation to the given optionalReference that its newly referenced
   * entities actually exist.
   * 
   * @param optionalReference
   * @param dataAndSchemaAdapter
   */
  void addExpectationThatNewlyReferencedEntitiesExist(
    IOptionalReference<? extends IEntity> optionalReference,
    DataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Adds expectation to the given reference that its newly referenced entities
   * actually exist.
   * 
   * @param reference
   * @param dataAndSchemaAdapter
   */
  void addExpectationThatNewlyReferencedEntitiesExist(
    IReference<? extends IEntity> reference,
    DataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Adds expectation to the given field that its newly referenced entities
   * actually exist if the given field is new or edited.
   * 
   * @param field
   * @param dataAndSchemaAdapter
   */
  void addExpectationThatNewlyReferencedEntitiesExistIfFieldIsNewOrEdited(
    Field field,
    DataAdapterAndSchemaReader dataAndSchemaAdapter);
}
