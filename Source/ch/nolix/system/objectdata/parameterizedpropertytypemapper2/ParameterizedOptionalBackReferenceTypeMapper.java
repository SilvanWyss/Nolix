//package declaration
package ch.nolix.system.objectdata.parameterizedpropertytypemapper2;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.parameterizedpropertytype.ParameterizedOptionalBackReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalBackReference;
import ch.nolix.systemapi.objectdataapi.parameterizedpropertytypemapper2api.IParameterizedPropertyTypeMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ParameterizedOptionalBackReferenceTypeMapper
implements IParameterizedPropertyTypeMapper<IOptionalBackReference<IEntity>> {

  //method
  @Override
  public IParameterizedFieldType createParameterizedPropertyTypeFromProperty(
    final IOptionalBackReference<IEntity> property,
    final IContainer<ITable> referencedTables) {

    final var backReferencedTableName = property.getBackReferencedTableName();
    final var backReferencedTable = referencedTables.getStoredFirst(t -> t.hasName(backReferencedTableName));
    final var backReferencedColumnName = property.getBackReferencedPropertyName();
    final var columns = backReferencedTable.getStoredColumns();
    final var backReferencedColumn = columns.getStoredFirst(c -> c.hasName(backReferencedColumnName));

    return ParameterizedOptionalBackReferenceType.forBackReferencedColumn(backReferencedColumn);
  }
}
