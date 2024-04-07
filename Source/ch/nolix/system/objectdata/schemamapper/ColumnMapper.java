//package declaration
package ch.nolix.system.objectdata.schemamapper;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.schema.Column;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.IColumnMapper;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.IParameterizedPropertyTypeMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ColumnMapper implements IColumnMapper {

  //constant
  private static final EntityCreator ENTITY_CREATOR = new EntityCreator();

  //constant
  private static final IParameterizedPropertyTypeMapper PARAMETERIZED_PROPERTY_TYPE_MAPPER = //
  new ParameterizedPropertyTypeMapper();

  //method
  @Override
  public IColumn createColumnFromGivenPropertyUsingGivenReferencableTables(
    final IField field,
    final IContainer<ITable> referencableTables) {
    return new Column(
      field.getName(),
      PARAMETERIZED_PROPERTY_TYPE_MAPPER.createParameterizedPropertyTypeFromProperty(
        field,
        referencableTables));
  }

  //method
  @Override
  public <E extends IEntity> IContainer<IColumn> createColumnsFromGivenEntityTypeUsingGivenReferencableTables(
    final Class<E> entityType,
    final IContainer<ITable> referencableTables) {
    return createColumnsFromGivenEntityUsingGivenReferencableTables(
      ENTITY_CREATOR.createEmptyEntityOf(entityType),
      referencableTables);
  }

  //method
  @Override
  public IContainer<IColumn> createColumnsFromGivenEntityUsingGivenReferencableTables(
    final IEntity entity,
    final IContainer<ITable> referencableTables) {
    return entity
      .internalGetStoredFields()
      .to(p -> createColumnFromGivenPropertyUsingGivenReferencableTables(p, referencableTables));
  }
}
