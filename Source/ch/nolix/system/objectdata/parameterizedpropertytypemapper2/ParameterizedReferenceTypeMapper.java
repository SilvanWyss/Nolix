//package declaration
package ch.nolix.system.objectdata.parameterizedpropertytypemapper2;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.parameterizedpropertytype.ParameterizedReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;
import ch.nolix.systemapi.objectdataapi.parameterizedpropertytypemapper2api.IParameterizedPropertyTypeMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ParameterizedReferenceTypeMapper implements IParameterizedPropertyTypeMapper<IReference<IEntity>> {

  //method
  @Override
  public IParameterizedFieldType createParameterizedPropertyTypeFromProperty(
    final IReference<IEntity> property,
    final IContainer<ITable> referencedTables) {

    final var referencedTableName = property.getReferencedTableName();
    final var referencedTable = referencedTables.getStoredFirst(t -> t.hasName(referencedTableName));

    return ParameterizedReferenceType.forReferencedTable(referencedTable);
  }
}
