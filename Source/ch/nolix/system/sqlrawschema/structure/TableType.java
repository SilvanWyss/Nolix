//package declaration
package ch.nolix.system.sqlrawschema.structure;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//enum
public enum TableType {
  SCHEMA_TABLE("S"),
  META_DATA_TABLE("M"),
  ALL_ENTITY_TABLE("A"),
  ENTITY_TABLE("E"),
  MULTI_ENTRY_TABLE("T");

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
