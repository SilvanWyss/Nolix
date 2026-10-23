/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.sqlmapper;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.middata.model.ValueStringFieldDto;

/**
 * @author Silvan Wyss
 */
public interface ISqlPartsMapper {
  /**
   * @param valueStringFieldDto
   * @return the column names for the given valueStringFieldDto
   */
  ExtendedIterable<String> mapValueStringFieldDtoToColumnNames(ValueStringFieldDto valueStringFieldDto);

  /**
   * @param valueStringFieldDto
   * @return the SQL value literals for the given valueStringFieldDto
   */
  ExtendedIterable<String> mapValueStringFieldDtoToSqlValueLiterals(ValueStringFieldDto valueStringFieldDto);
}
