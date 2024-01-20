//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//interface
public interface IBaseParameterizedBackReferenceType<C extends IColumn> extends IParameterizedPropertyType {

  //method declaration
  C getBackReferencedColumn();
}
