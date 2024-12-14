package ch.nolix.systemapi.objectdataapi.parameterizedfieldtypemapper2api;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public interface IParameterizedFieldTypeMapper<F extends IField> {

  IContentModel createParameterizedFieldTypeFromField(
    F field,
    IContainer<ITable> referencedTables);
}
