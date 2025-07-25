package ch.nolix.systemapi.sqlschema.model;

public enum ColumnConstraint {
  NOT_NULL,
  UNIQUE,
  CHECK,
  DEFAULT,
  PRIMARY_KEY,
  FOREIGN_KEY
}
