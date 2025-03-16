package ch.nolix.system.objectdata.rawdatamodelmapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.middataapi.modelapi.StringContentFieldDto;
import ch.nolix.systemapi.objectdataapi.modelapi.IBackReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiBackReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.modelapi.IOptionalBackReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IOptionalReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IOptionalValue;
import ch.nolix.systemapi.objectdataapi.modelapi.IReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IValue;
import ch.nolix.systemapi.objectdataapi.rawdatamodelmapperapi.IStringContentFieldDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2024-12-25
 */
public final class StringContentFieldDtoMapper implements IStringContentFieldDtoMapper {

  private static StringContentFieldDto mapOptionalValueToStringContentFieldDto(IOptionalValue<?> optionalValue) {

    final var columnName = optionalValue.getName();

    if (optionalValue.isEmpty()) {
      return new StringContentFieldDto(columnName, null);
    }

    return new StringContentFieldDto(columnName, optionalValue.getStoredValue().toString());
  }

  private static StringContentFieldDto mapOptionalReferenceToStringContentFieldDto(
    IOptionalReference<?> optionalReference) {

    final var columnName = optionalReference.getName();

    if (optionalReference.isEmpty()) {
      return new StringContentFieldDto(columnName, null);
    }

    return new StringContentFieldDto(columnName, optionalReference.getReferencedEntityId());
  }

  private static StringContentFieldDto mapOptionalBackReferenceToStringContentFieldDto(
    IOptionalBackReference<?> optionalBackReference) {

    final var columnName = optionalBackReference.getName();

    if (optionalBackReference.isEmpty()) {
      return new StringContentFieldDto(columnName, null);
    }

    return new StringContentFieldDto(columnName, optionalBackReference.getBackReferencedEntityId());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public StringContentFieldDto mapFieldToStringContentFieldDto(final IField field) {

    if (field instanceof final IValue<?> value) {
      return new StringContentFieldDto(value.getName(), value.getStoredValue().toString());
    }

    if (field instanceof final IOptionalValue<?> optionalValue) {
      return mapOptionalValueToStringContentFieldDto(optionalValue);
    }

    if (field instanceof final IMultiValue<?> multiValue) {
      return new StringContentFieldDto(multiValue.getName(), null);
    }

    if (field instanceof final IReference<?> reference) {
      return new StringContentFieldDto(reference.getName(), reference.getReferencedEntityId());
    }

    if (field instanceof final IOptionalReference<?> optionalReference) {
      return mapOptionalReferenceToStringContentFieldDto(optionalReference);
    }

    if (field instanceof final IMultiReference<?> multiReference) {
      return new StringContentFieldDto(multiReference.getName(), null);
    }

    if (field instanceof final IBackReference<?> backReference) {
      return new StringContentFieldDto(backReference.getName(), backReference.getBackReferencedEntityId());
    }

    if (field instanceof final IOptionalBackReference<?> optionalBackReference) {
      return mapOptionalBackReferenceToStringContentFieldDto(optionalBackReference);
    }

    if (field instanceof final IMultiBackReference<?> multiBackReference) {
      return new StringContentFieldDto(multiBackReference.getName(), null);
    }

    throw InvalidArgumentException.forArgument(field);
  }
}
