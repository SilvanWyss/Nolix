package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import ch.nolix.systemapi.databaseobjectapi.modelexaminerapi.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;

public interface IFieldTool extends IDatabaseObjectExaminer {

  boolean belongsToEntity(IField field);

  boolean belongsToLoadedEntity(IField field);

  Class<?> getDataType(IField field);

  boolean isForMultiContent(IField field);

  boolean isForSingleContent(IField field);

  boolean isMandatoryAndEmptyBoth(IField field);

  boolean isSetForCaseIsNewOrEditedAndMandatory(IField field);
}
