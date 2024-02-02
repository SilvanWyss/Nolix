//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.sqlrawdatabase.databasedto.ContentFieldDto;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDto;

//class
public final class MultiBackReference<E extends IEntity>
extends BaseBackReference<E>
implements IMultiBackReference<E> {

  //constructor
  private MultiBackReference(final String backReferencedEntityTypeName, final String backReferencedPropertyName) {
    super(backReferencedEntityTypeName, backReferencedPropertyName);
  }

  //static method
  public static <E2 extends Entity> MultiBackReference<E2> forEntityTypeAndPropertyName(
    final Class<E2> entityType,
    final String propertyName) {

    final var entityTypeName = entityType.getSimpleName();

    return new MultiBackReference<>(entityTypeName, propertyName);
  }

  //static method
  public static MultiBackReference<BaseEntity> forEntityTypeNameAndPropertyName(
    final String entityTypeName,
    final String propertyName) {
    return new MultiBackReference<>(entityTypeName, propertyName);
  }

  //method
  @Override
  public IContainer<String> getBackReferencedEntityIds() {
    return getStoredBackReferencedEntities().to(IEntity::getId);
  }

  //method
  @Override
  public IContainer<E> getStoredBackReferencedEntities() {

    final var backReferencedEntities = new LinkedList<E>();
    final var parentEntity = getStoredParentEntity();

    for (final var e : getStoredBackReferencedTable().getStoredEntities()) {
      for (final var p : e.technicalGetStoredProperties()) {
        if (p.hasName(getBackReferencedPropertyName())) {

          if (p.referencesEntity(parentEntity)) {
            backReferencedEntities.addAtEnd(e);
          }

          break;
        }
      }
    }

    return backReferencedEntities;
  }

  //method
  @Override
  public IContainer<IProperty> getStoredReferencingProperties() {

    final var referencingPropertie = new LinkedList<IProperty>();
    final var parentEntity = getStoredParentEntity();

    for (final var e : getStoredBackReferencedTable().getStoredEntities()) {
      for (final var p : e.technicalGetStoredProperties()) {
        if (p.hasName(getBackReferencedPropertyName())) {

          if (p.referencesEntity(parentEntity)) {
            referencingPropertie.addAtEnd(p);
          }

          break;
        }
      }
    }

    return referencingPropertie;
  }

  //method
  @Override
  public PropertyType getType() {
    return PropertyType.MULTI_BACK_REFERENCE;
  }

  //method
  @Override
  public boolean isEmpty() {

    final var parentEntity = getStoredParentEntity();

    for (final var e : getStoredBackReferencedTable().getStoredEntities()) {
      for (final var p : e.technicalGetStoredProperties()) {
        if (p.hasName(getBackReferencedPropertyName())) {

          if (p.referencesEntity(parentEntity)) {
            return true;
          }

          break;
        }
      }
    }

    return false;
  }

  //method
  @Override
  public boolean isMandatory() {
    return false;
  }

  //method
  @Override
  public boolean referencesBackEntity(final IEntity entity) {

    for (final var p : entity.technicalGetStoredProperties()) {
      if (p.hasName(getBackReferencedPropertyName())) {
        return p.referencesEntity(entity);
      }
    }

    return false;
  }

  //method
  @Override
  public IContentFieldDto technicalToContentField() {
    return new ContentFieldDto(getName());
  }

  //method
  @Override
  protected boolean referencesBackEntityWithId(final String id) {

    final var entity = getStoredBackReferencedTable().getStoredEntityById(id);

    return referencesBackEntity(entity);
  }

  //method
  @Override
  void internalSetOrClearDirectlyFromContent(final Object content) {
    GlobalValidator.assertThat(content).thatIsNamed(LowerCaseVariableCatalogue.CONTENT).isNull();
  }
}
