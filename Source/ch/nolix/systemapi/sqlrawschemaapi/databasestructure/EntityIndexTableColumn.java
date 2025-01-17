package ch.nolix.systemapi.sqlrawschemaapi.databasestructure;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public enum EntityIndexTableColumn implements INameHolder {
  TABLE_ID("TableId"),
  ENTITY_ID("EntityId");

  private final String name;

  EntityIndexTableColumn(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();

    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
