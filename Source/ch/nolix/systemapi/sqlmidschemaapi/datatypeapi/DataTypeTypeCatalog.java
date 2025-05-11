package ch.nolix.systemapi.sqlmidschemaapi.datatypeapi;

import ch.nolix.systemapi.sqlschemaapi.modelapi.DataTypeDto;

public final class DataTypeTypeCatalog {

  public static final DataTypeDto INTEGER = new DataTypeDto("INT", null);

  public static final DataTypeDto TEXT = new DataTypeDto("NVARCHAR", "MAX");

  private DataTypeTypeCatalog() {
  }
}
