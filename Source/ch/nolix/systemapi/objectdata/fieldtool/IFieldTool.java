package ch.nolix.systemapi.objectdata.fieldtool;

import ch.nolix.systemapi.objectdata.model.IField;

public interface IFieldTool {

  Class<?> getDataType(IField field);
}
