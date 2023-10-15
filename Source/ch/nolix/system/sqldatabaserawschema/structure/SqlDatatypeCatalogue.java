//package declaration
package ch.nolix.system.sqldatabaserawschema.structure;

import ch.nolix.system.sqldatabasebasicschema.schemadto.DataTypeDto;

//class
public final class SqlDatatypeCatalogue {

  // constant
  public static final DataTypeDto INTEGER = new DataTypeDto("INT");

  // constant
  public static final DataTypeDto TEXT = new DataTypeDto("NVARCHAR", "MAX");

  // constructor
  private SqlDatatypeCatalogue() {
  }
}
