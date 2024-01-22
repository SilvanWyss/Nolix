//package declaration
package ch.nolix.system.sqlrawschema.datatype;

//own imports
import ch.nolix.system.sqlschema.schemadto.DataTypeDto;

//class
public final class DatatypeTypeCatalogue {

  //constant
  public static final DataTypeDto INTEGER = new DataTypeDto("INT");

  //constant
  public static final DataTypeDto TEXT = new DataTypeDto("NVARCHAR", "MAX");

  //constructor
  private DatatypeTypeCatalogue() {
  }
}
