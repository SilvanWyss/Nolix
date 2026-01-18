/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.statementcreator;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.middata.model.ValueStringFieldDto;

/**
 * @author Silvan Wyss
 */
public interface ISqlValueAssignmentMapper {
  IContainer<String> mapValueStringFieldDtoToSqlValueAssignemnts(ValueStringFieldDto valueStringFieldDto);
}
