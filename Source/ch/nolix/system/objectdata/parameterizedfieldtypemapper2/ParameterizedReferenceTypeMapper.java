package ch.nolix.system.objectdata.parameterizedfieldtypemapper2;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.parameterizedfieldtype.ParameterizedReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;
import ch.nolix.systemapi.objectdataapi.parameterizedfieldtypemapper2api.IParameterizedFieldTypeMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public final class ParameterizedReferenceTypeMapper implements IParameterizedFieldTypeMapper<IReference<IEntity>> {

  @Override
  public IParameterizedFieldType createParameterizedFieldTypeFromField(
    final IReference<IEntity> property,
    final IContainer<ITable> referencedTables) {

    final var referencedTableName = property.getReferencedTableName();
    final var referencedTable = referencedTables.getStoredFirst(t -> t.hasName(referencedTableName));

    return ParameterizedReferenceType.forReferencedTable(referencedTable);
  }
}
