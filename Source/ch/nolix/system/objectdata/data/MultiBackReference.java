//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.databaseobject.databaseobjecttool.DatabaseObjectTool;
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDto;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiBackReferenceEntry;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDto;

//class
public final class MultiBackReference<E extends IEntity>
extends BaseBackReference<E>
implements IMultiBackReference<E> {

  //constant
  private static final DatabaseObjectTool DATABASE_OBJECT_TOOL = new DatabaseObjectTool();

  //attribute
  private boolean loadedAllPersistedBackReferencedEntityIds;

  //multi-attribute
  private final LinkedList<IMultiBackReferenceEntry<E>> localEntries = new LinkedList<>();

  //constructor
  private MultiBackReference(final String backReferencedTableName, final String backReferencedBaseReferenceName) {
    super(backReferencedTableName, backReferencedBaseReferenceName);
  }

  //static method
  public static <E2 extends Entity> MultiBackReference<E2> forBackReferencedEntityTypeAndBaseReference(
    final Class<E2> backReferencedEntityType,
    final String backReferencedBaseReferenceName) {

    final var entityTypeName = backReferencedEntityType.getSimpleName();

    return forBackReferencedTableAndBaseReference(entityTypeName, backReferencedBaseReferenceName);
  }

  //static method
  public static <E2 extends Entity> MultiBackReference<E2> forBackReferencedTableAndBaseReference(
    final String backReferencedTableName,
    final String backReferencedBaseReference) {
    return new MultiBackReference<>(backReferencedTableName, backReferencedBaseReference);
  }

  //method
  @Override
  public IContainer<String> getAllBackReferencedEntityIds() {
    return getAllStoredBackReferencedEntities().to(IEntity::getId);
  }

  //method
  @Override
  public IContainer<E> getAllStoredBackReferencedEntities() {

    final var backReferencedEntities = new LinkedList<E>();
    final var parentEntity = getStoredParentEntity();

    for (final var e : getStoredBackReferencedTable().getStoredEntities()) {
      for (final var p : e.internalGetStoredProperties()) {
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
  public IContainer<? extends IMultiBackReferenceEntry<E>> getStoredNewAndDeletedEntries() {
    return localEntries.getStoredSelected(DATABASE_OBJECT_TOOL::isNewOrDeleted);
  }

  //method
  @Override
  public IContainer<IProperty> getStoredReferencingProperties() {

    final var referencingPropertie = new LinkedList<IProperty>();
    final var parentEntity = getStoredParentEntity();

    for (final var e : getStoredBackReferencedTable().getStoredEntities()) {
      for (final var p : e.internalGetStoredProperties()) {
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
  public IContentFieldDto internalToContentField() {
    return new ContentFieldDto(getName());
  }

  //method
  @Override
  public boolean isEmpty() {

    final var parentEntity = getStoredParentEntity();

    for (final var e : getStoredBackReferencedTable().getStoredEntities()) {
      for (final var p : e.internalGetStoredProperties()) {
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

    for (final var p : entity.internalGetStoredProperties()) {
      if (p.hasName(getBackReferencedPropertyName())) {
        return p.referencesEntity(entity);
      }
    }

    return false;
  }

  //method
  @Override
  protected boolean referencesBackEntityWithId(final String id) {

    final var entity = getStoredBackReferencedTable().getStoredEntityById(id);

    return referencesBackEntity(entity);
  }

  //method
  void internalAddBackReferencedEntityId(final String backReferencedEntityId) {

    final var newEntry = MultiBackReferenceEntry.newEntryForMultiBackReferenceAndReferencedEntityId(
      this,
      backReferencedEntityId);

    localEntries.addAtEnd(newEntry);
  }

  //method
  @Override
  void internalSetOrClearFromContent(final Object content) {
    GlobalValidator.assertThat(content).thatIsNamed(LowerCaseVariableCatalogue.CONTENT).isNull();
  }
}
