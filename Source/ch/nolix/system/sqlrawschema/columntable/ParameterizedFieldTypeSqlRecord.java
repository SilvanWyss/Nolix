package ch.nolix.system.sqlrawschema.columntable;

public record ParameterizedFieldTypeSqlRecord(
String fieldTypeValue,
String dataTypeValue,
String referencedTableIdValue,
String backReferencedColumnIdValue) { //NOSONAR: The left curly brace is at the right place.

  public String getBackReferencedColumnIdValue() {
    return backReferencedColumnIdValue;
  }

  public String getDataTypeValue() {
    return dataTypeValue;
  }

  public String getFieldTypeValue() {
    return fieldTypeValue;
  }

  public String getReferencedTableIdValue() {
    return referencedTableIdValue;
  }
}
