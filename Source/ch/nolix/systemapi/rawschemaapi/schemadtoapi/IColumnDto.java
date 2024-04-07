//package declaration
package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

//interface
public interface IColumnDto {

  //method declaration
  String getId();

  //method declaration
  String getName();

  //method declaration
  IParameterizedFieldTypeDto getParameterizedPropertyType();
}
