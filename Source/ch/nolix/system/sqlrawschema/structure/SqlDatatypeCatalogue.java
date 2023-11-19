//package declaration
package ch.nolix.system.sqlrawschema.structure;

import ch.nolix.system.sqlschema.schemadto.DataTypeDto;

//class
public final class SqlDatatypeCatalogue {

  //constant
  public static final DataTypeDto INTEGER = new DataTypeDto("INT");

  //constant
  public static final DataTypeDto TEXT = new DataTypeDto("NVARCHAR", "MAX");

  //constructor
  private SqlDatatypeCatalogue() {
  }
}
