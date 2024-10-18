package ch.nolix.systemapi.objectschemaapi.schemaapi;

public interface IBaseParameterizedReferenceType extends IParameterizedFieldType {

  ITable getReferencedTable();
}
