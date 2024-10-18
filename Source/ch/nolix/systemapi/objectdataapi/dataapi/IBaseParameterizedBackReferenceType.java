package ch.nolix.systemapi.objectdataapi.dataapi;

public interface IBaseParameterizedBackReferenceType<C extends IColumn> extends IParameterizedFieldType {

  C getBackReferencedColumn();
}
