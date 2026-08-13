/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.structure.EntityCache;

/**
 * Of the {@link ReferenceHelper} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class ReferenceHelper {
  private ReferenceHelper() {
  }

  public static <E extends IEntity> EntityCache<E> createEntityCacheFromIdAndTableId(
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
