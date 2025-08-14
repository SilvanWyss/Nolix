package ch.nolix.system.objectdata.modelsearcher;

import java.util.Optional;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.objectdata.modelexaminer.FieldExaminer;
import ch.nolix.systemapi.objectdata.model.IBaseBackReference;
import ch.nolix.systemapi.objectdata.model.IAbstractReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.modelexaminer.IFieldExaminer;
import ch.nolix.systemapi.objectdata.modelsearcher.IEntitySearcher;

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
  public Optional<IBaseBackReference<IEntity>> //
  getOptionalStoredAbstractBackReferenceThatCanBackReferenceAbstractReference(
    final IEntity entity,
    final IAbstractReference<? extends IEntity> baseReference) {

    if (entity != null && baseReference != null) {

      final var fields = entity.internalGetStoredFields();

      for (final var f : fields) {
        if (FIELD_EXAMINER.canReferenceBackAbstractReference(f, baseReference)) {
          return Optional.of((IBaseBackReference<IEntity>) f);
        }
      }
    }

    return Optional.empty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<IBaseBackReference<IEntity>> getStoredAbstractBackReferencesThatReferencesBackEntity(
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
