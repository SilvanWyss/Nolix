package ch.nolix.system.sqlrawschema.datatype;

import ch.nolix.systemapi.sqlschemaapi.dto.DataTypeDto;

public final class DatatypeTypeCatalog {

  public static final DataTypeDto INTEGER = DataTypeDto.withName("INT");

  public static final DataTypeDto TEXT = DataTypeDto.withNameAndParameter("NVARCHAR", "MAX");

  private DatatypeTypeCatalog() {
  }
}
