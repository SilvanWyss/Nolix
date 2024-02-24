//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.databaseobject.databaseobjecttool.DatabaseObjectTool;
import ch.nolix.system.objectdata.propertytool.PropertyTool;
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

  //constant
  private static final PropertyTool PROPERTY_TOOL = new PropertyTool();

  //attribute
  private boolean loadedAllPersistedBackReferencedEntityIds;

  //multi-attribute
  private final LinkedList<MultiBackReferenceEntry<E>> localEntries = new LinkedList<>();

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

    updateStateLoadingAllPersistedBackReferencedEntityIdsIfNotLoaded();

    return localEntries
      .getStoredSelected(DATABASE_OBJECT_TOOL::isNewOrLoadedOrEdited)
      .to(IMultiBackReferenceEntry::getBackReferencedEntityId);
  }

  //method
  @Override
  public IContainer<E> getAllStoredBackReferencedEntities() {

    updateStateLoadingAllPersistedBackReferencedEntityIdsIfNotLoaded();

    return localEntries
      .getStoredSelected(DATABASE_OBJECT_TOOL::isNewOrLoadedOrEdited)
      .to(IMultiBackReferenceEntry::getStoredBackReferencedEntity);
  }

  //method
  @Override
  public IContainer<? extends IMultiBackReferenceEntry<E>> getStoredNewAndDeletedEntries() {
    return localEntries.getStoredSelected(DATABASE_OBJECT_TOOL::isNewOrDeleted);
  }

  //method
  @Override
  public IContainer<IProperty> getStoredReferencingProperties() {

    final var referencingProperties = new LinkedList<IProperty>();
    final var backReferencedBaseReferenceName = getBackReferencedPropertyName();

    for (final var e : getAllStoredBackReferencedEntities()) {
      for (final var p : e.internalGetStoredProperties()) {
        if (p.hasName(backReferencedBaseReferenceName)) {
          referencingProperties.addAtEnd(p);
          break;
        }
      }
    }

    return referencingProperties;
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
    return localEntries.isEmpty()
    && isEmptyWhenDoesNotHaveLocalEntries();
  }

  //method
  @Override
  public boolean isMandatory() {
    return false;
  }

  //method
  @Override
  public boolean loadedAllPersistedReferencedEntityIds() {
    return loadedAllPersistedBackReferencedEntityIds;
  }

  //method
  @Override
  public boolean referencesBackEntity(final IEntity entity) {

    final var backReferencedBaseReferenceName = getBackReferencedPropertyName();

    for (final var p : entity.internalGetStoredProperties()) {
      if (p.hasName(backReferencedBaseReferenceName)) {
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
  void internalDeleteBackReferencedEntityId(final String backReferencedEntityId) {

    final var entry = localEntries.getStoredFirst(e -> e.getBackReferencedEntityId().equals(backReferencedEntityId));

    entry.internalDelete();
  }

  //method
  @Override
  void internalSetOrClearFromContent(final Object content) {
    GlobalValidator.assertThat(content).thatIsNamed(LowerCaseVariableCatalogue.CONTENT).isNull();
  }

  //method
  private boolean isEmptyWhenDoesNotHaveLocalEntries() {
    return getAllStoredBackReferencedEntities().isEmpty();
  }

  //method
  private IContainer<MultiBackReferenceEntry<E>> loadAllPersistedBackReferencedEntityIds() {

    final var entity = getStoredParentEntity();

    return internalGetRefDataAndSchemaAdapter().loadMultiBackReferenceEntries(
      entity.getParentTableName(),
      entity.getId(),
      getName())
      .to(e -> MultiBackReferenceEntry.loadedEntryForMultiBackReferenceAndReferencedEntityId(this, e));
  }

  //method
  private boolean needsToLoadAllPersistedBackReferencedEntityIds() {
    return !loadedAllPersistedReferencedEntityIds()
    && PROPERTY_TOOL.belongsToLoadedEntity(this);
  }

  //method
  private void updateStateLoadingAllPersistedBackReferencedEntityIds() {

    loadedAllPersistedBackReferencedEntityIds = true;

    localEntries.addAtEnd(loadAllPersistedBackReferencedEntityIds());
  }

  //method
  private void updateStateLoadingAllPersistedBackReferencedEntityIdsIfNotLoaded() {
    if (needsToLoadAllPersistedBackReferencedEntityIds()) {
      updateStateLoadingAllPersistedBackReferencedEntityIds();
    }
  }
}
