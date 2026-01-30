/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlschema.statementcreator;

import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;
import ch.nolix.systemapi.sqlschema.statementcreator.IStatementCreator;

/**
 * @author Silvan Wyss
 */
public final class StatementCreator implements IStatementCreator {
  @Override
  public String createStatementToAddColumn(final String tableName, final ColumnDto column) {
    return ("ALTER TABLE " + tableName + " ADD " + StatementCreatorHelper.getColumnAsSql(column) + ";");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToAddTable(TableDto table) {
    return //
    "CREATE TABLE "
    + table.name()
    + " (" + table.columns().getViewOf(StatementCreatorHelper::getColumnAsSql).toStringWithSeparator(", ")
    + ");";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToDeleteColumn(final String tableName, final String columnName) {
    return ("ALTER TABLE " + tableName + " DROP COLUMN " + columnName + ";");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToDeleteColumnIfExists(final String tableName, final String columnName) {
    return //
    "IF EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME ='"
    + tableName
    + "' AND COLUMN_NAME = '"
    + columnName
    + "')"
    + "BEGIN ALTER TABLE "
    + tableName
    + " DROP COLUMN "
    + columnName
    + " END;";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToDeleteTable(final String tableName) {
    return ("DROP TABLE " + tableName + ";");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToRenameColumn(
    final String tableName,
    final String columnName,
    final String newColumnName) {
    return ("ALTER TABLE " + tableName + " RENAME COLUMN " + columnName + " TO " + newColumnName + ";");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToRenameColumnIfExists(
    final String tableName,
    final String columnName,
    final String newColumnName) {
    return //
    "IF EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME ='"
    + tableName
    + "' AND COLUMN_NAME = '"
    + columnName
    + "')"
    + "BEGIN ALTER TABLE "
    + tableName
    + " RENAME COLUMN "
    + columnName
    + " TO "
    + newColumnName
    + " END;";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToRenameTable(final String tableName, final String newTableName) {
    return ("ALTER TABLE " + tableName + " RENAME TO " + newTableName + ";");
  }
}
