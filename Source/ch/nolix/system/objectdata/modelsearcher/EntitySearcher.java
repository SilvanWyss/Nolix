package ch.nolix.system.objectdata.modelsearcher;

import java.util.Optional;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.modelexaminer.FieldExaminer;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractBackReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectdataapi.modelexaminerapi.IFieldExaminer;
import ch.nolix.systemapi.objectdataapi.modelsearcherapi.IEntitySearcher;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public final class EntitySearcher implements IEntitySearcher {

  private static final IFieldExaminer FIELD_EXAMINER = new FieldExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public Optional<IAbstractBackReference<IEntity>> //
  getOptionalStoredAbstractBackReferenceThatCanBackReferenceAbstractReference(
    final IEntity entity,
    final IAbstractReference<? extends IEntity> baseReference) {

    if (entity != null && baseReference != null) {

      final var fields = entity.internalGetStoredFields();

      for (final var f : fields) {
        if (FIELD_EXAMINER.canReferenceBackAbstractReference(f, baseReference)) {
          return Optional.of((IAbstractBackReference<IEntity>) f);
        }
      }
    }

    return Optional.empty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<IAbstractBackReference<IEntity>> getStoredAbstractBackReferencesThatReferencesBackEntity(
    final IEntity entity) {

    if (entity == null) {
      return ImmutableList.createEmpty();
    }

    final var fields = entity.internalGetStoredFields();

    return fields.toMultiples(IField::getStoredAbstractBackReferencesThatReferencesBackThis);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<? extends IField> getStoredEditedFields(final IEntity entity) {
    return entity.internalGetStoredFields().getStoredSelected(IField::isEdited);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IField getStoredFieldByName(final IEntity entity, final String name) {

    final var fields = entity.internalGetStoredFields();

    return fields.getStoredFirst(f -> f.hasName(name));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<IAbstractReference<IEntity>> getStoredFieldsThatAreBackReferencedFrom(final IEntity entity) {

    if (entity == null) {
      return ImmutableList.createEmpty();
    }

    final var fields = entity.internalGetStoredFields();

    return fields.toMultiples(IField::getStoredAbstractReferencesThatAreBackReferencedFromThis);
  }
}
