package ch.nolix.systemapi.sqlschemaapi.modelapi;

public enum ColumnConstraint {
  NOT_NULL,
  UNIQUE,
  CHECK,
  DEFAULT,
  PRIMARY_KEY,
  FOREIGN_KEY
}
