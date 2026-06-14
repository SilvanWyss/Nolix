/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.sqlmapper;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.systemapi.middata.model.ValueStringFieldDto;

/**
 * @author Silvan Wyss
 */
public interface ISqlPartsMapper {
  IWellOrderContainer<String> mapValueStringFieldDtoToColumnNames(ValueStringFieldDto valueStringFieldDto);

  IWellOrderContainer<String> mapValueStringFieldDtoToSqlValueLiterals(ValueStringFieldDto valueStringFieldDto);
}
