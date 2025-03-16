package ch.nolix.systemapi.sqlmidschemaapi.datatypeapi;

import ch.nolix.systemapi.sqlschemaapi.modelapi.DataTypeDto;

public final class DataTypeTypeCatalog {

  public static final DataTypeDto INTEGER = DataTypeDto.withName("INT");

  public static final DataTypeDto TEXT = DataTypeDto.withNameAndParameter("NVARCHAR", "MAX");

  private DataTypeTypeCatalog() {
  }
}
