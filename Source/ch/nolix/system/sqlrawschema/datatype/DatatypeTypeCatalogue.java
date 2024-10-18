package ch.nolix.system.sqlrawschema.datatype;

import ch.nolix.system.sqlschema.schemadto.DataTypeDto;

public final class DatatypeTypeCatalogue {

  public static final DataTypeDto INTEGER = new DataTypeDto("INT");

  public static final DataTypeDto TEXT = new DataTypeDto("NVARCHAR", "MAX");

  private DatatypeTypeCatalogue() {
  }
}
