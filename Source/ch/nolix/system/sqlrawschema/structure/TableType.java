//package declaration
package ch.nolix.system.sqlrawschema.structure;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//enum
public enum TableType {
  META_DATA_TABLE("M"),
  SCHEMA_TABLE("S"),
  INDEX_TABLE("I"),
  ENTITY_TABLE("E"),
  MULTI_ENTRY_TABLE("T");

  //attribute
  private final String qualifyingPrefix;

  //constructor
  TableType(final String qualifyingPrefix) {

    GlobalValidator.assertThat(qualifyingPrefix).thatIsNamed("qualifying prefix").isNotBlank();

    this.qualifyingPrefix = qualifyingPrefix;
  }

  //method
  public String getQualifyingPrefix() {
    return qualifyingPrefix;
  }
}
