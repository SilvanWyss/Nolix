/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.statementcreator;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.middata.model.ValueStringFieldDto;

/**
 * @author Silvan Wyss
 */
public interface ISqlValueAssignmentMapper {
  /**
   * @param valueStringFieldDto
   * @return the SQL value assignments for the given valueStringFieldDto
   */
  ExtendedIterable<String> mapValueStringFieldDtoToSqlValueAssignemnts(ValueStringFieldDto valueStringFieldDto);
}
