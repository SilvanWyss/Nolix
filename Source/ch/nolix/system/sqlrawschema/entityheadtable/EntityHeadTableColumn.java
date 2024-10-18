package ch.nolix.system.sqlrawschema.entityheadtable;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public enum EntityHeadTableColumn implements INameHolder {
  TABLE_ID("TableId"),
  ENTITY_ID("EntityId");

  private final String name;

  EntityHeadTableColumn(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();

    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
