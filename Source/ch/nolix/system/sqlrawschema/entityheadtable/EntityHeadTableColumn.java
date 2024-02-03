//package declaration
package ch.nolix.system.sqlrawschema.entityheadtable;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//enum
public enum EntityHeadTableColumn implements INameHolder {
  TABLE_ID("TableId"),
  ENTITY_ID("EntityId");

  //attribute
  private final String name;

  //constructor
  EntityHeadTableColumn(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();

    this.name = name;
  }

  //method
  @Override
  public String getName() {
    return name;
  }
}
