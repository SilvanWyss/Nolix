/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.modelexaminer;

import ch.nolix.systemapi.objectschema.model.IColumn;

/**
 * @author Silvan Wyss
 */
public interface IColumnExaminer {
  /**
   * @param column
   * @return true if the given column is a base back reference column, false
   *         otherwise
   */
  boolean isBaseBackReferenceColumn(IColumn column);

  /**
   * @param column
   * @return true if the given column is a base reference column, false otherwise
   */
  boolean isBaseReferenceColumn(IColumn column);

  /**
   * @param column
   * @return true if the given column is a base value column, false otherwise
   */
  boolean isBaseValueColumn(IColumn column);

  /**
   * @param column
   * @return true if the given column is open and empty and not back referenced,
   *         false otherwise
   */
  boolean isOpenAndEmptyAndNotBackReferenced(IColumn column);

  /**
   * @param column
   * @return true if the given column is a valid base back reference column, false
   *         otherwise
   */
  boolean isValidBaseBackReferenceColumn(IColumn column);
}
