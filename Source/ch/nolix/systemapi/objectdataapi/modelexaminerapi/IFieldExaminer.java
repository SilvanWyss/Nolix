package ch.nolix.systemapi.objectdataapi.modelexaminerapi;

import ch.nolix.systemapi.databaseobjectapi.modelexaminerapi.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public interface IFieldExaminer extends IDatabaseObjectExaminer {

  /**
   * @param field
   * @return true if the given field belongs to a {@link IEntity}, false
   *         otherwise.
   */
  boolean belongsToEntity(IField field);

  /**
   * @param field
   * @return true if the given field belongs to a loaded {@link IEntity}, false
   *         otherwise.
   */
  boolean belongsToLoadedEntity(IField field);

  /**
   * @param field
   * @param abstractReference
   * @return true if the given field can reference back the given
   *         abstractReference, false otherwise.
   */
  boolean canReferenceBackAbstractReference(IField field, IAbstractReference<? extends IEntity> abstractReference);

  /**
   * @param field
   * @return true if the given field is for multi content, false otherwise.
   */
  boolean isForMultiContent(IField field);

  /**
   * @param field
   * @return true if the given field is for single content, false otherwise.
   */
  boolean isForSingleContent(IField field);

  /**
   * @param field
   * @return true if the given field is mandatory but empty, false otherwise.
   */
  boolean isMandatoryButEmpty(IField field);

  /**
   * @param field
   * @return true if the given field is new or edited but set, false otherwise.
   */
  boolean isSetForCaseWhenIsMandatoryAndNewOrEdited(IField field);
}