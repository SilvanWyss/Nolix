//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//class
public abstract class Entity extends BaseEntity {

  //constant
  private static final PropertyFromEntityExtractor PROPERTY_FROM_ENTITY_EXTRACTOR = new PropertyFromEntityExtractor();

  //method
  @Override
  public final String getParentTableName() {
    return getClass().getSimpleName();
  }

  //method
  @Override
  final IContainer<Property> internalLoadProperties() {
    return PROPERTY_FROM_ENTITY_EXTRACTOR.getStoredPropertiesFrom(this);
  }
}
