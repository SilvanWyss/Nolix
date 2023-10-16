//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//class
public final class GeneralEntity extends BaseEntity {

  //constant
  private static final PropertyFromTableExtractor PROPERTY_FROM_TABLE_EXTRACTOR = new PropertyFromTableExtractor();

  //static method
  public GeneralEntity forTable(final Table<GeneralEntity> table) {
    return new GeneralEntity(table);
  }

  //attribute
  private final String tableName;

  //constructor
  private GeneralEntity(final Table<GeneralEntity> table) {
    tableName = table.getName();
  }

  //method
  @Override
  public String getParentTableName() {
    return tableName;
  }

  //method
  @Override
  IContainer<Property> internalLoadProperties() {
    return PROPERTY_FROM_TABLE_EXTRACTOR.createPropertiesFromTable(getStoredParentTable());
  }
}
