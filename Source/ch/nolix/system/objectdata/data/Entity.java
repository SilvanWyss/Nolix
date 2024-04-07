//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//class
public abstract class Entity extends BaseEntity {

  //constant
  private static final FieldFromEntityMapper PROPERTY_FROM_ENTITY_EXTRACTOR = new FieldFromEntityMapper();

  //method
  @Override
  public final String getParentTableName() {
    return getClass().getSimpleName();
  }

  //method
  @Override
  final IContainer<Field> internalLoadFields() {
    return PROPERTY_FROM_ENTITY_EXTRACTOR.getStoredPropertiesFrom(this);
  }
}
