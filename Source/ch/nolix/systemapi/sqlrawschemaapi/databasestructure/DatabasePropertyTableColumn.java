package ch.nolix.systemapi.sqlrawschemaapi.databasestructure;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ILabelHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;

public enum DatabasePropertyTableColumn implements ILabelHolder {

  //'Key' is a reserved word in MSSQL databases.
  KEY("ValueKey"),

  VALUE(PascalCaseVariableCatalog.VALUE);

  private final String label;

  DatabasePropertyTableColumn(final String label) {

    GlobalValidator.assertThat(label).thatIsNamed(LowerCaseVariableCatalog.LABEL).isNotBlank();

    this.label = label;
  }

  @Override
  public final String getLabel() {
    return label;
  }
}
