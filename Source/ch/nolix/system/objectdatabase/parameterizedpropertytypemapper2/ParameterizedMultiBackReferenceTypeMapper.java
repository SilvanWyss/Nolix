//package declaration
package ch.nolix.system.objectdatabase.parameterizedpropertytypemapper2;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.parameterizedpropertytype.ParameterizedMultiBackReferenceType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiBackReference;
import ch.nolix.systemapi.objectdatabaseapi.parameterizedpropertytypemapper2api.IParameterizedPropertyTypeMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ParameterizedMultiBackReferenceTypeMapper
implements IParameterizedPropertyTypeMapper<IMultiBackReference<IEntity>> {

  //method
  @Override
  public IParameterizedPropertyType createParameterizedPropertyTypeFromProperty(
    final IMultiBackReference<IEntity> property,
    final IContainer<ITable> referencedTables) {

    final var backReferencedTableName = property.getBackReferencedTableName();
    final var backReferencedTable = referencedTables.getStoredFirst(t -> t.hasName(backReferencedTableName));
    final var backReferencedColumnName = property.getBackReferencedPropertyName();
    final var columns = backReferencedTable.getStoredColumns();
    final var backReferencedColumn = columns.getStoredFirst(c -> c.hasName(backReferencedColumnName));

    return ParameterizedMultiBackReferenceType.forBackReferencedColumn(backReferencedColumn);
  }
}
