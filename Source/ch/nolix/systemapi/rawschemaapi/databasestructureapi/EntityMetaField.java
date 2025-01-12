package ch.nolix.systemapi.rawschemaapi.databasestructureapi;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ILabelHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 */
public enum EntityMetaField implements ILabelHolder {
  ID(PascalCaseVariableCatalog.ID),
  SAVE_STAMP(PascalCaseVariableCatalog.SAVE_STAMP),
  VALID_FROM_DATE_TIME(PascalCaseVariableCatalog.VALID_FROM_DATE_TIME),
  VALID_TO_DATE_TIME(PascalCaseVariableCatalog.VALID_TO_DATE_TIME);

  private final String label;

  EntityMetaField(final String label) {

    GlobalValidator.assertThat(label).thatIsNamed(LowerCaseVariableCatalog.LABEL).isNotBlank();

    this.label = label;
  }

  @Override
  public final String getLabel() {
    return label;
  }
}
