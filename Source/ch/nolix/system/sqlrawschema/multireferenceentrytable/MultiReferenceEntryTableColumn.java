//package declaration
package ch.nolix.system.sqlrawschema.multireferenceentrytable;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//enum
public enum MultiReferenceEntryTableColumn implements INameHolder {
  MULTI_REFERENCE_COLUMN_ID("MultiReferenceColumnId"),
  ENTITY_ID("EntityId"),
  REFERENCED_ENTITY_ID("ReferencedEntityId");

  //attribute
  private final String name;

  //constructor
  MultiReferenceEntryTableColumn(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();

    this.name = name;
  }

  //method
  @Override
  public final String getName() {
    return name;
  }
}
