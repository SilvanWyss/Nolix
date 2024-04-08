//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//class
public final class FieldFromTableMapper {

  //constant
  private static final FieldFromColumnMapper FIELD_FROM_COLUMN_MAPPER = new FieldFromColumnMapper();

  //method
  public IContainer<Field> createPropertiesFromTable(final ITable<? extends IEntity> table) {
    return table.getStoredColumns().to(FIELD_FROM_COLUMN_MAPPER::createFieldFromColumn);
  }
}
