package ch.nolix.systemapi.objectdata.fieldtool;

import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdata.model.IField;

public interface IFieldTool extends IDatabaseObjectExaminer {

  Class<?> getDataType(IField field);

}
