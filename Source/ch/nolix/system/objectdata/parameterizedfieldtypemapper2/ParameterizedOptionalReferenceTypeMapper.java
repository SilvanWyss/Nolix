//package declaration
package ch.nolix.system.objectdata.parameterizedfieldtypemapper2;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.parameterizedfieldtype.ParameterizedOptionalReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalReference;
import ch.nolix.systemapi.objectdataapi.parameterizedfieldtypemapper2api.IParameterizedFieldTypeMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ParameterizedOptionalReferenceTypeMapper
implements IParameterizedFieldTypeMapper<IOptionalReference<IEntity>> {

  //method
  @Override
  public IParameterizedFieldType createParameterizedFieldTypeFromField(
    final IOptionalReference<IEntity> property,
    final IContainer<ITable> referencedTables) {

    final var referencedTableName = property.getReferencedTableName();
    final var referencedTable = referencedTables.getStoredFirst(t -> t.hasName(referencedTableName));

    return ParameterizedOptionalReferenceType.forReferencedTable(referencedTable);
  }
}
