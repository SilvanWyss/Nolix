/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.sqlmapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.middata.model.ValueStringFieldDto;

/**
 * @author Silvan Wyss
 */
public interface ISqlPartsMapper {
  IContainer<String> mapValueStringFieldDtoToColumnNames(ValueStringFieldDto valueStringFieldDto);

  IContainer<String> mapValueStringFieldDtoToSqlValueLiterals(ValueStringFieldDto valueStringFieldDto);
}
