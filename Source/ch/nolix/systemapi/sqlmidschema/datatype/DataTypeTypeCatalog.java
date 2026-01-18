/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.datatype;

import ch.nolix.systemapi.sqlschema.model.DataTypeDto;

/**
 * @author Silvan Wyss
 */
public final class DataTypeTypeCatalog {
  public static final DataTypeDto INTEGER = new DataTypeDto("INT", null);

  public static final DataTypeDto TEXT = new DataTypeDto("NVARCHAR", "MAX");

  private DataTypeTypeCatalog() {
  }
}
