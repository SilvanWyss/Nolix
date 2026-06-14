/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.statementcreator;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.systemapi.middata.model.ValueStringFieldDto;

/**
 * @author Silvan Wyss
 */
public interface ISqlValueAssignmentMapper {
  IWellOrderContainer<String> mapValueStringFieldDtoToSqlValueAssignemnts(ValueStringFieldDto valueStringFieldDto);
}
