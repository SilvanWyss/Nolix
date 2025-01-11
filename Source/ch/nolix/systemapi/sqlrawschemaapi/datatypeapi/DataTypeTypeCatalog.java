package ch.nolix.systemapi.sqlrawschemaapi.datatypeapi;

import ch.nolix.systemapi.sqlschemaapi.dto.DataTypeDto;

public final class DataTypeTypeCatalog {

  public static final DataTypeDto INTEGER = DataTypeDto.withName("INT");

  public static final DataTypeDto TEXT = DataTypeDto.withNameAndParameter("NVARCHAR", "MAX");

  private DataTypeTypeCatalog() {
  }
}
