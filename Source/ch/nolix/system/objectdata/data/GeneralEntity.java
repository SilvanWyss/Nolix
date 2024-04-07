//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//class
public final class GeneralEntity extends BaseEntity {

  //constant
  private static final PropertyFromTableMapper PROPERTY_FROM_TABLE_EXTRACTOR = new PropertyFromTableMapper();

  //attribute
  private final String tableName;

  //constructor
  private GeneralEntity(final Table<GeneralEntity> table) {
    tableName = table.getName();
  }

  //static method
  public static GeneralEntity forTable(final Table<GeneralEntity> table) {
    return new GeneralEntity(table);
  }

  //method
  @Override
  public String getParentTableName() {
    return tableName;
  }

  //method
  @Override
  IContainer<Field> internalLoadProperties() {
    return PROPERTY_FROM_TABLE_EXTRACTOR.createPropertiesFromTable(getStoredParentTable());
  }
}
