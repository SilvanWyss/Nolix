//package declaration
package ch.nolix.system.sqlrawschema.tabletype;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//enum
public enum TableType {
  SCHEMA_TABLE("S"),
  META_DATA_TABLE("M"),
  INDEX_TABLE("I"),
  ALL_ENTITY_TABLE("A"),
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
  public final String getQualifyingPrefix() {
    return qualifyingPrefix;
  }
}
