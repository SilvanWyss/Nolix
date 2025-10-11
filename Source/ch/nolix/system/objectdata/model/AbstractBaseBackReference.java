package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectdata.model.IBaseBackReference;
import ch.nolix.systemapi.objectdata.model.IEntity;

public abstract class AbstractBaseBackReference<E extends IEntity>
extends AbstractField
implements IBaseBackReference {
  private final ImmutableList<String> backReferenceableTableNames;

  private final String backReferencedFieldName;

  protected AbstractBaseBackReference(
    final IContainer<String> backReferenceableTableNames,
    final String backReferencedFieldName) {

    Validator.assertThatTheStrings(backReferenceableTableNames).areNotBlank();

    Validator
      .assertThat(backReferencedFieldName)
      .thatIsNamed("back referenced field name")
      .isNotBlank();

    this.backReferenceableTableNames = ImmutableList.forIterable(backReferenceableTableNames);
    this.backReferencedFieldName = backReferencedFieldName;
  }

  @Override
  public final IContainer<String> getBackReferenceableTableNames() {
    return backReferenceableTableNames;
  }

  @Override
  public final String getBackReferencedFieldName() {
    return backReferencedFieldName;
  }

  @Override
  public final IContainer<IBaseBackReference> getStoredBaseBackReferencesWhoReferencesBackThis() {
    return ImmutableList.createEmpty();
  }

  @Override
  public final boolean referencesEntity(final IEntity entity) {
    return false;
  }

  @Override
  public final boolean referencesUninsertedEntity() {
    return false;
  }

  @Override
  protected final void noteInsertIntoDatabase() {
    //Does nothing.
  }
}
