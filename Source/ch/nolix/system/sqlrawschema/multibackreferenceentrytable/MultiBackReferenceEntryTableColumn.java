//package declaration
package ch.nolix.system.sqlrawschema.multibackreferenceentrytable;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//enum
public enum MultiBackReferenceEntryTableColumn implements INameHolder {
  ENTITY_ID("EntityId"),
  MULTI_BACK_REFERENCE_COLUMN_ID("MultiBackReferenceColumnId"),
  BACK_REFERENCED_ENTITY_ID("BackReferencedEntityId");

  //attribute
  private final String name;

  //constructor
  MultiBackReferenceEntryTableColumn(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();

    this.name = name;
  }

  //method
  @Override
  public final String getName() {
    return name;
  }
}
