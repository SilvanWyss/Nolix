//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//class
public final class PropertyFromTableMapper {

  //constant
  private static final PropertyFromColumnMapper PROPERTY_FROM_COLUMN_MAPPER = new PropertyFromColumnMapper();

  //method
  public IContainer<Property> createPropertiesFromTable(final ITable<? extends IEntity> table) {
    return table.getStoredColumns().to(PROPERTY_FROM_COLUMN_MAPPER::createPropertyFromColumn);
  }
}
