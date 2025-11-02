package ch.nolix.systemapi.sqlmiddata.statementcreator;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.middata.model.ValueStringFieldDto;

public interface ISqlValueAssignmentMapper {
  IContainer<String> mapValueStringFieldDtoToSqlValueAssignemnts(ValueStringFieldDto valueStringFieldDto);
}
