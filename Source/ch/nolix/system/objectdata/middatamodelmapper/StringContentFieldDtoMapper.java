package ch.nolix.system.objectdata.middatamodelmapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.middata.model.StringValueFieldDto;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IStringContentFieldDtoMapper;
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
public final class StringContentFieldDtoMapper implements IStringContentFieldDtoMapper {

  private static StringValueFieldDto mapOptionalValueToStringContentFieldDto(IOptionalValueField<?> optionalValue) {

    final var columnName = optionalValue.getName();

    if (optionalValue.isEmpty()) {
      return new StringValueFieldDto(columnName, null);
    }

    return new StringValueFieldDto(columnName, optionalValue.getStoredValue().toString());
  }

  private static StringValueFieldDto mapOptionalReferenceToStringContentFieldDto(
    IOptionalReference<?> optionalReference) {

    final var columnName = optionalReference.getName();

    if (optionalReference.isEmpty()) {
      return new StringValueFieldDto(columnName, null);
    }

    return new StringValueFieldDto(columnName, optionalReference.getReferencedEntityId());
  }

  private static StringValueFieldDto mapOptionalBackReferenceToStringContentFieldDto(
    IOptionalBackReference<?> optionalBackReference) {

    final var columnName = optionalBackReference.getName();

    if (optionalBackReference.isEmpty()) {
      return new StringValueFieldDto(columnName, null);
    }

    return new StringValueFieldDto(columnName, optionalBackReference.getBackReferencedEntityId());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public StringValueFieldDto mapFieldToStringContentFieldDto(final IField field) {

    if (field instanceof final IValueField<?> value) {
      return new StringValueFieldDto(value.getName(), value.getStoredValue().toString());
    }

    if (field instanceof final IOptionalValueField<?> optionalValue) {
      return mapOptionalValueToStringContentFieldDto(optionalValue);
    }

    if (field instanceof final IMultiValueField<?> multiValue) {
      return new StringValueFieldDto(multiValue.getName(), null);
    }

    if (field instanceof final IReference<?> reference) {
      return new StringValueFieldDto(reference.getName(), reference.getReferencedEntityId());
    }

    if (field instanceof final IOptionalReference<?> optionalReference) {
      return mapOptionalReferenceToStringContentFieldDto(optionalReference);
    }

    if (field instanceof final IMultiReference<?> multiReference) {
      return new StringValueFieldDto(multiReference.getName(), null);
    }

    if (field instanceof final IBackReference<?> backReference) {
      return new StringValueFieldDto(backReference.getName(), backReference.getBackReferencedEntityId());
    }

    if (field instanceof final IOptionalBackReference<?> optionalBackReference) {
      return mapOptionalBackReferenceToStringContentFieldDto(optionalBackReference);
    }

    if (field instanceof final IMultiBackReference<?> multiBackReference) {
      return new StringValueFieldDto(multiBackReference.getName(), null);
    }

    throw InvalidArgumentException.forArgument(field);
  }
}
