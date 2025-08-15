package ch.nolix.system.objectdata.model;

import java.util.Optional;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.databaseobject.modelvalidator.DatabaseObjectValidator;
import ch.nolix.systemapi.databaseobject.property.DatabaseObjectState;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.model.IMultiReference;
import ch.nolix.systemapi.objectdata.model.IMultiReferenceEntry;

final class MultiReferenceEntry<E extends IEntity> implements IMultiReferenceEntry<E> {

  private static final DatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

  private final IMultiReference<E> parentMultiReference;

  private DatabaseObjectState state;

  private final String referencedEntityId;

  private final String referencedEntityTableId;

  private MultiReferenceEntry(
    final IMultiReference<E> parentMultiReference,
    final DatabaseObjectState initialState,
    final String referencedEntityId) {

    Validator.assertThat(parentMultiReference).thatIsNamed("parent MultiReference").isNotNull();
    Validator.assertThat(initialState).thatIsNamed("initial state").isNotNull();
    Validator.assertThat(referencedEntityId).thatIsNamed("referenced entity id").isNotBlank();

    this.parentMultiReference = parentMultiReference;
    this.state = initialState;
    this.referencedEntityId = referencedEntityId;
    this.referencedEntityTableId = null;
  }

  private MultiReferenceEntry(
    final IMultiReference<E> parentMultiReference,
    final DatabaseObjectState initialState,
    final String referencedEntityId,
    final String referencedEntityTableId) {

    Validator.assertThat(parentMultiReference).thatIsNamed("parent MultiReference").isNotNull();
    Validator.assertThat(initialState).thatIsNamed("initial state").isNotNull();
    Validator.assertThat(referencedEntityId).thatIsNamed("referenced entity id").isNotBlank();
    Validator.assertThat(referencedEntityTableId).thatIsNamed("referenced entity table id").isNotBlank();

    this.parentMultiReference = parentMultiReference;
    this.state = initialState;
    this.referencedEntityId = referencedEntityId;
    this.referencedEntityTableId = referencedEntityId;
  }

  public static <E2 extends IEntity> MultiReferenceEntry<E2> //
  createLoadedEntryForMultiReferenceAndReferencedEntityIdAndReferencedEntityTableId(
    final IMultiReference<E2> multiReference,
    final String referencedEntityId,
    final String referencedEntityTableId) {
    return //
    new MultiReferenceEntry<>(
      multiReference,
      DatabaseObjectState.UNEDITED,
      referencedEntityId,
      referencedEntityTableId);
  }

  public static <E2 extends IEntity> MultiReferenceEntry<E2> createNewEntryForMultiReferenceAndReferencedEntityId(
    final IMultiReference<E2> multiReference,
    final String referencedEntityId) {
    return //
    new MultiReferenceEntry<>(multiReference, DatabaseObjectState.NEW, referencedEntityId);
  }

  @Override
  public Optional<? extends IField> getOptionalStoredBaseBackReferenceWhoReferencesBackTheParentMultiReferenceOfThis() {
    return //
    getStoredReferencedEntity()
      .internalGetStoredFields()
      .getOptionalStoredFirst(p -> p.referencesBackField(getStoredParentMultiReference()));
  }

  @Override
  public IMultiReference<E> getStoredParentMultiReference() {
    return parentMultiReference;
  }

  @Override
  public DatabaseObjectState getState() {
    return switch (getStoredParentMultiReference().getState()) {
      case NEW ->
        DatabaseObjectState.NEW;
      case DELETED ->
        DatabaseObjectState.DELETED;
      case CLOSED ->
        DatabaseObjectState.CLOSED;
      default ->
        state;
    };
  }

  @Override
  public String getReferencedEntityId() {
    return referencedEntityId;
  }

  @Override
  public E getStoredReferencedEntity() {
    return getStoredParentMultiReference().getStoredReferencedTable().getStoredEntityById(getReferencedEntityId());
  }

  @Override
  public boolean isClosed() {
    return getStoredParentMultiReference().isClosed();
  }

  @Override
  public boolean isDeleted() {
    return getStoredParentMultiReference().isDeleted();
  }

  @Override
  public boolean isEdited() {
    return false;
  }

  @Override
  public boolean isConnectedWithRealDatabase() {
    return getStoredParentMultiReference().isConnectedWithRealDatabase();
  }

  @Override
  public boolean isLoaded() {
    return (getState() == DatabaseObjectState.UNEDITED);
  }

  @Override
  public boolean isNew() {
    return (getState() == DatabaseObjectState.NEW);
  }

  void internalSetDeleted() {

    assertIsLoaded();

    state = DatabaseObjectState.DELETED;
  }

  private void assertIsLoaded() {
    DATABASE_OBJECT_VALIDATOR.assertIsLoaded(this);
  }
}
