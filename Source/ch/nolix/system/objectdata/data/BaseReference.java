//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//class
public abstract class BaseReference<E extends IEntity> extends Property
implements IBaseReference<E> {

  //attribute
  private final String referencedTableName;

  //optional attribute
  private Table<E> referencedTable;

  //constructor
  protected BaseReference(final String referencedTableName) {

    GlobalValidator.assertThat(referencedTableName).thatIsNamed("referenced table name").isNotBlank();

    this.referencedTableName = referencedTableName;
  }

  //method
  @Override
  public final ITable<E> getReferencedTable() {

    extractReferencedTableIfNotExtracted();

    return referencedTable;
  }

  //method
  @Override
  public final String getReferencedTableName() {
    return referencedTableName;
  }

  //method
  @Override
  public final IContainer<IProperty> getStoredReferencingProperties() {
    return new ImmutableList<>();
  }

  //method
  @Override
  public final boolean referencesBackEntity(final IEntity entity) {
    return false;
  }

  //method
  @Override
  public boolean referencesBackProperty(final IProperty property) {
    return false;
  }

  //method
  protected final void updateProbableBackReferenceForSetOrAddedEntity(final E entity) {
    if (isLinkedWithRealDatabase()) {
      for (final var p : entity.technicalGetStoredProperties()) {
        switch (p.getType()) {
          case BACK_REFERENCE:

            final var backReference = (BackReference<?>) p;

            if (backReference.referencesBackProperty(this)) {
              backReference.internalSetDirectlyBackReferencedEntityId(getStoredParentEntity().getId());
            }

            break;

          case OPTIONAL_BACK_REFERENCE:

            final var optionalBackReference = (OptionalBackReference<?>) p;

            if (optionalBackReference.referencesBackProperty(this)) {
              optionalBackReference.internalSetDirectlyBackReferencedEntityId(getStoredParentEntity().getId());
            }

            break;
          default:
            //Does nothing.
        }
      }
    }
  }

  //method
  private boolean extractedReferencedTable() {
    return (referencedTable != null);
  }

  //method
  private void extractReferencedTable() {
    referencedTable = loadReferencedTable();
  }

  //method
  private void extractReferencedTableIfNotExtracted() {
    if (!extractedReferencedTable()) {
      extractReferencedTable();
    }
  }

  //method
  @SuppressWarnings("unchecked")
  private Table<E> loadReferencedTable() {
    return (Table<E>) getStoredParentEntity()
      .getStoredParentTable()
      .getStoredParentDatabase()
      .getStoredTableByName(getReferencedTableName());
  }
}