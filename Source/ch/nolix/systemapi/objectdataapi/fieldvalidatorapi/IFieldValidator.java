package ch.nolix.systemapi.objectdataapi.fieldvalidatorapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IField;

public interface IFieldValidator {

  void assertBelongsToEntity(IField field);

  void assertDoesNotBelongToEntity(IField field);

  void assertIsNotEmpty(IField field);

  void assertIsNotMandatoryAndEmptyBoth(IField field);

  void assertKnowsParentColumn(IField field);
}
