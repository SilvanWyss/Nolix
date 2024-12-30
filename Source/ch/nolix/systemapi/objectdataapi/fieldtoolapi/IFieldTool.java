package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import ch.nolix.systemapi.databaseobjectapi.modelexaminerapi.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;

public interface IFieldTool extends IDatabaseObjectExaminer {

  Class<?> getDataType(IField field);

}
