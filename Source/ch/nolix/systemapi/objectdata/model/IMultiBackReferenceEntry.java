/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.objectcomposition.datamodelcomponent.DatabaseComponent;
import ch.nolix.baseapi.objectcomposition.datamodelcomponent.TableComponent;
import ch.nolix.systemapi.database.databaseobject.DatabaseObject;

/**
 * @author Silvan Wyss
 * @param <E> the type of the {@link Entity} a {@link IMultiBackReferenceEntry}
 *            references back.
 */
public interface IMultiBackReferenceEntry<E extends Entity>
extends DatabaseComponent<IDatabase>, DatabaseObject, TableComponent<ITable<? extends Entity>> {
  /**
   * @return the id of the {@link Entity} the current
   *         {@link IMultiBackReferenceEntry} references back
   */
  String getBackReferencedEntityId();

  /**
   * @return the id of the {@link ITable} of the {@link Entity} the current
   *         {@link IMultiBackReferenceEntry} references back
   */
  String getBackReferencedTableId();

  /**
   * @return the {@link Entity} the current {@link IMultiBackReferenceEntry}
   *         references back
   */
  E getStoredBackReferencedEntity();

  /**
   * @return the {@link ITable} of the {@link Entity} the current
   *         {@link IMultiBackReferenceEntry} references back
   */
  ITable<E> getStoredBackReferencedTable();

  /**
   * @return the parent {@link IMultiBackReference} of the current
   *         {@link IMultiBackReferenceEntry}
   */
  IMultiBackReference<E> getStoredParentMultiBackReference();
}
