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
  ExtendedIterable<String> mapValueStringFieldDtoToColumnNames(ValueStringFieldDto valueStringFieldDto);

  ExtendedIterable<String> mapValueStringFieldDtoToSqlValueLiterals(ValueStringFieldDto valueStringFieldDto);
}
