package ch.nolix.systemapi.sqlrawschemaapi.databasestructure;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;

public enum TableType {
  META_DATA_TABLE(TableNameQualifyingPrefix.S),
  SCHEMA_TABLE(TableNameQualifyingPrefix.S),
  INDEX_TABLE(TableNameQualifyingPrefix.S),
  ENTITY_TABLE(TableNameQualifyingPrefix.E),
  MULTI_ENTRY_TABLE(TableNameQualifyingPrefix.S);

  private final TableNameQualifyingPrefix tableNameQualifyingPrefix;

  TableType(final TableNameQualifyingPrefix tableNameQualifyingPrefix) {

    GlobalValidator.assertThat(tableNameQualifyingPrefix).thatIsNamed(TableNameQualifyingPrefix.class).isNotNull();

    this.tableNameQualifyingPrefix = tableNameQualifyingPrefix;
  }

  public String getTableNameQualifyingPrefix() {
    return tableNameQualifyingPrefix.name();
  }
}
