//package declaration
package ch.nolix.system.sqlrawschema.columntable;

//class
public record ParameterizedFieldTypeSqlRecord(
String fieldTypeValue,
String dataTypeValue,
String referencedTableIdValue,
String backReferencedColumnIdValue) { //NOSONAR: The left curly brace is at the right place.

  //method
  public String getBackReferencedColumnIdValue() {
    return backReferencedColumnIdValue;
  }

  //method
  public String getDataTypeValue() {
    return dataTypeValue;
  }

  //method
  public String getFieldTypeValue() {
    return fieldTypeValue;
  }

  //method
  public String getReferencedTableIdValue() {
    return referencedTableIdValue;
  }
}
