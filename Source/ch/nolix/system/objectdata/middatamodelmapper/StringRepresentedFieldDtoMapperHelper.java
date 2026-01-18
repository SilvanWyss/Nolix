/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.middatamodelmapper;

import ch.nolix.systemapi.middata.model.ValueStringFieldDto;
import ch.nolix.systemapi.objectdata.model.IOptionalBackReference;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;
import ch.nolix.systemapi.objectdata.model.IOptionalValueField;

/**
 * Of the {@link StringRepresentedFieldDtoMapperHelper} an instance cannot be
 * created.
 * 
 * @author Silvan Wyss
 */
public final class StringRepresentedFieldDtoMapperHelper {
  /**
   * Prevents that an instance of the
   * {@link StringRepresentedFieldDtoMapperHelper} can be created.
   */
  private StringRepresentedFieldDtoMapperHelper() {
  }

  public static ValueStringFieldDto mapOptionalValueToStringContentFieldDto(
    final IOptionalValueField<?> optionalValue) {
    final var columnName = optionalValue.getName();

    if (optionalValue.isEmpty()) {
      return new ValueStringFieldDto(columnName, null, null);
    }

    return new ValueStringFieldDto(columnName, optionalValue.getStoredValue().toString(), null);
  }

  public static ValueStringFieldDto mapOptionalReferenceToStringContentFieldDto(
    final IOptionalReference<?> optionalReference) {
    final var columnName = optionalReference.getName();

    if (optionalReference.isEmpty()) {
      return new ValueStringFieldDto(columnName, null, null);
    }

    final var value = optionalReference.getReferencedEntityId();
    final var additionalValue = optionalReference.getReferencedTableId();

    return new ValueStringFieldDto(columnName, value, additionalValue);
  }

  public static ValueStringFieldDto mapOptionalBackReferenceToStringContentFieldDto(
    final IOptionalBackReference<?> optionalBackReference) {
    final var columnName = optionalBackReference.getName();

    if (optionalBackReference.isEmpty()) {
      return new ValueStringFieldDto(columnName, null, null);
    }

    final var value = optionalBackReference.getBackReferencedEntityId();
    final var additionalValue = optionalBackReference.getBackReferencedTableId();

    return new ValueStringFieldDto(columnName, value, additionalValue);
  }
}
