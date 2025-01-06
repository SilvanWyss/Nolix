package ch.nolix.system.sqlrawschema.datatype;

import ch.nolix.systemapi.sqlschemaapi.dto.DataTypeDto;

public final class DatatypeTypeCatalogue {

  public static final DataTypeDto INTEGER = DataTypeDto.withName("INT");

  public static final DataTypeDto TEXT = DataTypeDto.withNameAndParameter("NVARCHAR", "MAX");

  private DatatypeTypeCatalogue() {
  }
}
