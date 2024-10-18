package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;

public interface IFieldTool extends IDatabaseObjectTool {

  boolean belongsToEntity(IField field);

  boolean belongsToLoadedEntity(IField field);

  Class<?> getDataType(IField field);

  boolean isForMultiContent(IField field);

  boolean isForSingleContent(IField field);

  boolean isMandatoryAndEmptyBoth(IField field);

  boolean isSetForCaseIsNewOrEditedAndMandatory(IField field);
}
