package ch.nolix.systemapi.sqlmidschema.datatype;

import ch.nolix.systemapi.sqlschema.model.DataTypeDto;

public final class DataTypeTypeCatalog {

  public static final DataTypeDto INTEGER = new DataTypeDto("INT", null);

  public static final DataTypeDto TEXT = new DataTypeDto("NVARCHAR", "MAX");

  private DataTypeTypeCatalog() {
  }
}
