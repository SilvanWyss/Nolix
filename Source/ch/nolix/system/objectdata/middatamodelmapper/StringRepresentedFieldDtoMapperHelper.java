package ch.nolix.system.objectdata.middatamodelmapper;

import ch.nolix.systemapi.middata.model.StringRepresentedFieldDto;
import ch.nolix.systemapi.objectdata.model.IOptionalBackReference;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;
import ch.nolix.systemapi.objectdata.model.IOptionalValueField;

/**
 * Of the {@link StringRepresentedFieldDtoMapperHelper} an instance cannot be
 * created.
 * 
 * @author Silvan Wyss
 * @version 2025-08-22
 */
public final class StringRepresentedFieldDtoMapperHelper {
  /**
   * Prevents that an instance of the
   * {@link StringRepresentedFieldDtoMapperHelper} can be created.
   */
  private StringRepresentedFieldDtoMapperHelper() {
  }

  public static StringRepresentedFieldDto mapOptionalValueToStringContentFieldDto(
    final IOptionalValueField<?> optionalValue) {
    final var columnName = optionalValue.getName();

    if (optionalValue.isEmpty()) {
      return new StringRepresentedFieldDto(columnName, null, null);
    }

    return new StringRepresentedFieldDto(columnName, optionalValue.getStoredValue().toString(), null);
  }

  public static StringRepresentedFieldDto mapOptionalReferenceToStringContentFieldDto(
    final IOptionalReference<?> optionalReference) {
    final var columnName = optionalReference.getName();

    if (optionalReference.isEmpty()) {
      return new StringRepresentedFieldDto(columnName, null, null);
    }

    final var value = optionalReference.getReferencedEntityId();
    final var additionalValue = optionalReference.getReferencedTableId();

    return new StringRepresentedFieldDto(columnName, value, additionalValue);
  }

  public static StringRepresentedFieldDto mapOptionalBackReferenceToStringContentFieldDto(
    final IOptionalBackReference<?> optionalBackReference) {
    final var columnName = optionalBackReference.getName();

    if (optionalBackReference.isEmpty()) {
      return new StringRepresentedFieldDto(columnName, null, null);
    }

    final var value = optionalBackReference.getBackReferencedEntityId();
    final var additionalValue = optionalBackReference.getBackReferencedTableId();

    return new StringRepresentedFieldDto(columnName, value, additionalValue);
  }
}
