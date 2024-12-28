package ch.nolix.systemapi.sqlrawdataapi.databasestructure;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;

public enum TableType {
  META_DATA_TABLE("M"),
  SCHEMA_TABLE("S"),
  INDEX_TABLE("I"),
  ENTITY_TABLE("E"),
  MULTI_ENTRY_TABLE("T");

  private final String qualifyingPrefix;

  TableType(final String qualifyingPrefix) {

    GlobalValidator.assertThat(qualifyingPrefix).thatIsNamed("qualifying prefix").isNotBlank();

    this.qualifyingPrefix = qualifyingPrefix;
  }

  public String getQualifyingPrefix() {
    return qualifyingPrefix;
  }
}
