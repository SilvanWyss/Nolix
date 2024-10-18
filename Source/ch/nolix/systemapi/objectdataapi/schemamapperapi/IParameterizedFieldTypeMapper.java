package ch.nolix.systemapi.objectdataapi.schemamapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public interface IParameterizedFieldTypeMapper {

  IParameterizedFieldType createParameterizedFieldTypeFromField(
    IField field,
    IContainer<ITable> referencedTables);
}
