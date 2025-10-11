package ch.nolix.system.objectdata.modelsearcher;

import java.util.Optional;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.objectdata.fieldexaminer.FieldExaminer;
import ch.nolix.systemapi.objectdata.fieldexaminer.IFieldExaminer;
import ch.nolix.systemapi.objectdata.model.IBaseBackReference;
import ch.nolix.systemapi.objectdata.model.IBaseReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;
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
  public Optional<IBaseBackReference> //
  getOptionalStoredBaseBackReferenceWhoCanBackReferenceTheBaseReference(
    final IEntity entity,
    final IBaseReference baseReference) {
    if (entity != null && baseReference != null) {
      for (final var f : entity.internalGetStoredFields()) {
        if (FIELD_EXAMINER.canReferenceBackBaseReference(f, baseReference)) {
          return Optional.of((IBaseBackReference) f);
        }
      }
    }

    return Optional.empty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<IBaseBackReference> getStoredBaseBackReferencesThatReferenceBackEntity(
    final IEntity entity) {
    final var fields = entity.internalGetStoredFields();

    return fields.toMultiples(IField::getStoredBaseBackReferencesWhoReferencesBackThis);
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
  public IContainer<IBaseReference> getStoredFieldsWhoAreBackReferencedFromEntity(final IEntity entity) {
    final var fields = entity.internalGetStoredFields();

    return fields.toMultiples(IField::getStoredBaseReferencesWhoAreBackReferencedFromThis);
  }
}
