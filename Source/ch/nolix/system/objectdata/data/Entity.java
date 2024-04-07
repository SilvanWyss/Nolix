//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//class
public abstract class Entity extends BaseEntity {

  //constant
  private static final FieldFromEntityMapper FIELD_FROM_ENTITY_MAPPER = new FieldFromEntityMapper();

  //method
  @Override
  public final String getParentTableName() {
    return getClass().getSimpleName();
  }

  //method
  @Override
  final IContainer<Field> internalLoadFields() {
    return FIELD_FROM_ENTITY_MAPPER.getStoredPropertiesFrom(this);
  }
}
