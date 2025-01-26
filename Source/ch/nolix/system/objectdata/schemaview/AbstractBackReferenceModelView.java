package ch.nolix.system.objectdata.schemaview;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IAbstractBackReferenceModelView;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IAbstractReferenceModelView;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IAbstractValueModelView;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IColumnView;

public abstract class AbstractBackReferenceModelView<

C extends IColumnView>
implements IAbstractBackReferenceModelView<C> {

  private final C backReferencedColumn;

  protected AbstractBackReferenceModelView(final C backReferencedColumn) {

    GlobalValidator.assertThat(backReferencedColumn).thatIsNamed("back referenced column").isNotNull();

    this.backReferencedColumn = backReferencedColumn;
  }

  @Override
  public final IAbstractBackReferenceModelView<C> asAbstractBackReferenceModel() {
    return this;
  }

  @Override
  public final IAbstractReferenceModelView<?> asAbstractReferenceModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedReferenceType");
  }

  @Override
  public final IAbstractValueModelView<?> asAbstractValueModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedValueType");
  }

  @Override
  public final C getBackReferencedColumn() {
    return backReferencedColumn;
  }

  @Override
  public final boolean referencesTable(final Object table) {
    return false;
  }
}
