package ch.nolix.systemapi.rawschemaapi.databaseproperty;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ILabelHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public enum DatabaseProperty implements ILabelHolder {
  SCHEMA_TIMESTAMP("SchemaTimestamp");

  private final String label;

  DatabaseProperty(final String label) {

    GlobalValidator.assertThat(label).thatIsNamed(LowerCaseVariableCatalogue.LABEL).isNotBlank();

    this.label = label;
  }

  @Override
  public final String getLabel() {
    return label;
  }
}
