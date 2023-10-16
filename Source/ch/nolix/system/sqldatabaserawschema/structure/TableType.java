//package declaration
package ch.nolix.system.sqldatabaserawschema.structure;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//enum
public enum TableType {
  SYSTEM_TABLE("S"),
  ENTITY_TABLE("E"),
  MULTI_FIELD_TABLE("M");

  //attribute
  private final String namePrefix;

  //constructor
  TableType(final String namePrefix) {

    GlobalValidator.assertThat(namePrefix).thatIsNamed("name prefix").isNotBlank();

    this.namePrefix = namePrefix;
  }

  //method
  public final String getNamePrefix() {
    return namePrefix;
  }
}
