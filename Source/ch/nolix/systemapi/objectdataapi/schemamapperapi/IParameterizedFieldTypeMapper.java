package ch.nolix.systemapi.objectdataapi.schemamapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public interface IParameterizedFieldTypeMapper {

  IContentModel createParameterizedFieldTypeFromField(
    IField field,
    IContainer<ITable> referencedTables);
}
