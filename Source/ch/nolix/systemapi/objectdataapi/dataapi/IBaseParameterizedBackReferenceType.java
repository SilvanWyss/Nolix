//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//interface
public interface IBaseParameterizedBackReferenceType<C extends IColumn> extends IParameterizedFieldType {

  //method declaration
  C getBackReferencedColumn();
}
