package ch.nolix.systemapi.objectschemaapi.schemaapi;

public interface IBaseParameterizedBackReferenceType extends IParameterizedFieldType {

  IColumn getBackReferencedColumn();
}
