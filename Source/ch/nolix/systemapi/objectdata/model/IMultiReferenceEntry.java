/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import java.util.Optional;

import ch.nolix.baseapi.objectcomposition.datamodelcomponent.DatabaseComponent;
import ch.nolix.baseapi.objectcomposition.datamodelcomponent.TableComponent;
import ch.nolix.systemapi.database.databaseobject.DatabaseObject;

/**
 * @author Silvan Wyss
 * @param <E> the type of the {@link Entity} a {@link IMultiReferenceEntry}
 *            references.
 */
public interface IMultiReferenceEntry<E extends Entity>
extends DatabaseComponent<IDatabase>, DatabaseObject, TableComponent<ITable<? extends Entity>> {
  /**
   * @return a {@link Optional} with the {@link BaseBackReference} that references
   *         back the parent {@link IMultiReference} of the current
   *         {@link IMultiReferenceEntry} if there is such a
   *         {@link BaseBackReference}, an empty {@link Optional} otherwise
   */
  Optional<? extends Field> getOptionalStoredBaseBackReferenceWhoReferencesBackTheParentMultiReferenceOfThis();

  /**
   * @return the id of the {@link Entity} the current {@link IMultiReferenceEntry}
   *         references
   */
  String getReferencedEntityId();

  /**
   * @return the id of the {@link ITable} of the {@link Entity} the current
   *         {@link IMultiReferenceEntry} references
   */
  String getReferencedTableId();

  /**
   * @return the name of the {@link ITable} of the {@link Entity} the current
   *         {@link IMultiReferenceEntry} references
   */
  String getReferencedTableName();

  /**
   * @return the parent {@link IMultiReference} of the current
   *         {@link IMultiReferenceEntry}
   */
  IMultiReference<E> getStoredParentMultiReference();

  /**
   * @return the {@link Entity} the current {@link IMultiReferenceEntry}
   *         references
   */
  E getStoredReferencedEntity();

  /**
   * @return the {@link ITable} of the {@link Entity} the current
   *         {@link IMultiReferenceEntry} references
   */
  ITable<E> getStoredReferencedTable();
}
