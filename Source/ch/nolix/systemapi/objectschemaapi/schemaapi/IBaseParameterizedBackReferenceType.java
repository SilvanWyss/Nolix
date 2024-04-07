//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

//interface
public interface IBaseParameterizedBackReferenceType extends IParameterizedFieldType {

  //method declaration
  IColumn getBackReferencedColumn();
}
