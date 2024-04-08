//package declaration
package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

//own imports
import ch.nolix.systemapi.fieldapi.datatypeapi.DataType;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;

//interface
public interface IParameterizedFieldTypeDto {

  //method declaration
  DataType getDataType();

  //method declaration
  FieldType getFieldType();
}
