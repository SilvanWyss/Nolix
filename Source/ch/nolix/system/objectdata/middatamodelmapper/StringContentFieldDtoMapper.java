package ch.nolix.system.objectdata.middatamodelmapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.middata.model.StringRepresentedFieldDto;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IStringRepresentedFieldDtoMapper;
import ch.nolix.systemapi.objectdata.model.IBackReference;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.model.IMultiBackReference;
import ch.nolix.systemapi.objectdata.model.IMultiReference;
import ch.nolix.systemapi.objectdata.model.IMultiValueField;
import ch.nolix.systemapi.objectdata.model.IOptionalBackReference;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;
import ch.nolix.systemapi.objectdata.model.IOptionalValueField;
import ch.nolix.systemapi.objectdata.model.IReference;
import ch.nolix.systemapi.objectdata.model.IValueField;

/**
 * @author Silvan Wyss
 * @version 2024-12-25
 */
public final class StringContentFieldDtoMapper implements IStringRepresentedFieldDtoMapper {

  private static StringRepresentedFieldDto mapOptionalValueToStringContentFieldDto(IOptionalValueField<?> optionalValue) {

    final var columnName = optionalValue.getName();

    if (optionalValue.isEmpty()) {
      return new StringRepresentedFieldDto(columnName, null);
    }

    return new StringRepresentedFieldDto(columnName, optionalValue.getStoredValue().toString());
  }

  private static StringRepresentedFieldDto mapOptionalReferenceToStringContentFieldDto(
    IOptionalReference<?> optionalReference) {

    final var columnName = optionalReference.getName();

    if (optionalReference.isEmpty()) {
      return new StringRepresentedFieldDto(columnName, null);
    }

    return new StringRepresentedFieldDto(columnName, optionalReference.getReferencedEntityId());
  }

  private static StringRepresentedFieldDto mapOptionalBackReferenceToStringContentFieldDto(
    IOptionalBackReference<?> optionalBackReference) {

    final var columnName = optionalBackReference.getName();

    if (optionalBackReference.isEmpty()) {
      return new StringRepresentedFieldDto(columnName, null);
    }

    return new StringRepresentedFieldDto(columnName, optionalBackReference.getBackReferencedEntityId());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public StringRepresentedFieldDto mapFieldToStringRepresentedFieldDto(final IField field) {

    if (field instanceof final IValueField<?> value) {
      return new StringRepresentedFieldDto(value.getName(), value.getStoredValue().toString());
    }

    if (field instanceof final IOptionalValueField<?> optionalValue) {
      return mapOptionalValueToStringContentFieldDto(optionalValue);
    }

    if (field instanceof final IMultiValueField<?> multiValue) {
      return new StringRepresentedFieldDto(multiValue.getName(), null);
    }

    if (field instanceof final IReference<?> reference) {
      return new StringRepresentedFieldDto(reference.getName(), reference.getReferencedEntityId());
    }

    if (field instanceof final IOptionalReference<?> optionalReference) {
      return mapOptionalReferenceToStringContentFieldDto(optionalReference);
    }

    if (field instanceof final IMultiReference<?> multiReference) {
      return new StringRepresentedFieldDto(multiReference.getName(), null);
    }

    if (field instanceof final IBackReference<?> backReference) {
      return new StringRepresentedFieldDto(backReference.getName(), backReference.getBackReferencedEntityId());
    }

    if (field instanceof final IOptionalBackReference<?> optionalBackReference) {
      return mapOptionalBackReferenceToStringContentFieldDto(optionalBackReference);
    }

    if (field instanceof final IMultiBackReference<?> multiBackReference) {
      return new StringRepresentedFieldDto(multiBackReference.getName(), null);
    }

    throw InvalidArgumentException.forArgument(field);
  }
}
