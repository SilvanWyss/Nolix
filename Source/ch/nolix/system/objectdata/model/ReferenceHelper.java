/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.systemapi.objectdata.model.Entity;
import ch.nolix.systemapi.objectdata.structure.EntityCache;

/**
 * Of the {@link ReferenceHelper} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class ReferenceHelper {
  private ReferenceHelper() {
  }

  /**
   * @param id
   * @param tableId
   * @param <E>     the type of the {@link Entity} the created {@link EntityCache}
   *                is for
   * @return a new {@link EntityCache} with the given id and tableId
   * @throws RuntimeException if the given id is null
   * @throws RuntimeException if the given tableId is null
   * @throws RuntimeException if the given id is not a {@link String}
   */
  public static <E extends Entity> EntityCache<E> createEntityCacheFromIdAndTableId(
    final Object id,
    final String tableId) {

    if (id == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableNameCatalog.ID);
    }

    if (tableId == null) {
      throw ArgumentIsNullException.forArgumentName("table id");
    }

    final var idString = (String) id;

    return new EntityCache<>(idString, tableId, null);
  }
}
