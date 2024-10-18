package ch.nolix.system.sqlrawschema.databasepropertytable;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ILabelHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;

public enum DatabasePropertyTableColumn implements ILabelHolder {

  //'Key' is a reserved word in MSSQL databases.
  KEY("ValueKey"),

  VALUE(PascalCaseVariableCatalogue.VALUE);

  private final String label;

  DatabasePropertyTableColumn(final String label) {

    GlobalValidator.assertThat(label).thatIsNamed(LowerCaseVariableCatalogue.LABEL).isNotBlank();

    this.label = label;
  }

  @Override
  public final String getLabel() {
    return label;
  }
}
