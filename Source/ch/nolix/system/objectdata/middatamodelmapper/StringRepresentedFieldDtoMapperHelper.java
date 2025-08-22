package ch.nolix.system.objectdata.middatamodelmapper;

import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.coreapi.container.base.IContainer;
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

  public static IContainer<StringRepresentedFieldDto> mapOptionalValueToStringContentFieldDtos(
    final IOptionalValueField<?> optionalValue) {

    final var columnName = optionalValue.getName();

    if (optionalValue.isEmpty()) {
      return SingleContainer.withElement(new StringRepresentedFieldDto(columnName, null));
    }

    return //
    SingleContainer.withElement(new StringRepresentedFieldDto(columnName, optionalValue.getStoredValue().toString()));
  }

  public static IContainer<StringRepresentedFieldDto> mapOptionalReferenceToStringContentFieldDtos(
    final IOptionalReference<?> optionalReference) {

    final var columnName = optionalReference.getName();

    if (optionalReference.isEmpty()) {
      return SingleContainer.withElement(new StringRepresentedFieldDto(columnName, null));
    }

    return //
    SingleContainer.withElement(new StringRepresentedFieldDto(columnName, optionalReference.getReferencedEntityId()));
  }

  public static IContainer<StringRepresentedFieldDto> mapOptionalBackReferenceToStringContentFieldDtos(
    final IOptionalBackReference<?> optionalBackReference) {

    final var columnName = optionalBackReference.getName();

    if (optionalBackReference.isEmpty()) {
      return SingleContainer.withElement(new StringRepresentedFieldDto(columnName, null));
    }

    return //
    SingleContainer.withElement(
      new StringRepresentedFieldDto(columnName, optionalBackReference.getBackReferencedEntityId()));
  }
}
