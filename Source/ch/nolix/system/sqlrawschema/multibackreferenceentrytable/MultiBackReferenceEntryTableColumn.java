package ch.nolix.system.sqlrawschema.multibackreferenceentrytable;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public enum MultiBackReferenceEntryTableColumn implements INameHolder {
  ENTITY_ID("EntityId"),
  MULTI_BACK_REFERENCE_COLUMN_ID("MultiBackReferenceColumnId"),
  BACK_REFERENCED_ENTITY_ID("BackReferencedEntityId");

  private final String name;

  MultiBackReferenceEntryTableColumn(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();

    this.name = name;
  }

  @Override
  public final String getName() {
    return name;
  }
}
