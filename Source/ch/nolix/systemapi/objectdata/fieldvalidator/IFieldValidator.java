package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.IField;

public interface IFieldValidator {

  void assertBelongsToEntity(IField field);

  void assertDoesNotBelongToEntity(IField field);

  void assertIsNotEmpty(IField field);

  void assertIsNotMandatoryAndEmptyBoth(IField field);

  void assertKnowsParentColumn(IField field);
}
