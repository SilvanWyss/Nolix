package ch.nolix.system.objectdata.rawdatamodelmapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
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
import ch.nolix.systemapi.rawdataapi.modelapi.StringContentFieldDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-25
 */
public final class StringContentFieldDtoMapper implements IStringContentFieldDtoMapper {

  private static StringContentFieldDto mapOptionalValueToStringContentFieldDto(IOptionalValue<?> optionalValue) {
    if (optionalValue.isEmpty()) {
      return StringContentFieldDto.withColumnName(optionalValue.getName());
    }

    return //
    StringContentFieldDto.withColumnNameAndContent(
      optionalValue.getName(),
      optionalValue.getStoredValue().toString());
  }

  private static StringContentFieldDto mapOptionalReferenceToStringContentFieldDto(
    IOptionalReference<?> optionalReference) {
    if (optionalReference.isEmpty()) {
      return StringContentFieldDto.withColumnName(optionalReference.getName());
    }

    return //
    StringContentFieldDto.withColumnNameAndContent(
      optionalReference.getName(),
      optionalReference.getReferencedEntityId());
  }

  private static StringContentFieldDto mapOptionalBackReferenceToStringContentFieldDto(
    IOptionalBackReference<?> optionalBackReference) {
    if (optionalBackReference.isEmpty()) {
      return StringContentFieldDto.withColumnName(optionalBackReference.getName());
    }

    return StringContentFieldDto.withColumnNameAndContent(
      optionalBackReference.getName(),
      optionalBackReference.getBackReferencedEntityId());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public StringContentFieldDto mapFieldToStringContentFieldDto(final IField field) {

    if (field instanceof final IValue<?> value) {
      return StringContentFieldDto.withColumnNameAndContent(value.getName(), value.getStoredValue().toString());
    }

    if (field instanceof final IOptionalValue<?> optionalValue) {
      return mapOptionalValueToStringContentFieldDto(optionalValue);
    }

    if (field instanceof final IMultiValue<?> multiValue) {
      return StringContentFieldDto.withColumnName(multiValue.getName());
    }

    if (field instanceof final IReference<?> reference) {
      return StringContentFieldDto.withColumnNameAndContent(reference.getName(), reference.getReferencedEntityId());
    }

    if (field instanceof final IOptionalReference<?> optionalReference) {
      return mapOptionalReferenceToStringContentFieldDto(optionalReference);
    }

    if (field instanceof final IMultiReference<?> multiReference) {
      return StringContentFieldDto.withColumnName(multiReference.getName());
    }

    if (field instanceof final IBackReference<?> backReference) {
      return //
      StringContentFieldDto.withColumnNameAndContent(
        backReference.getName(),
        backReference.getBackReferencedEntityId());
    }

    if (field instanceof final IOptionalBackReference<?> optionalBackReference) {
      return mapOptionalBackReferenceToStringContentFieldDto(optionalBackReference);
    }

    if (field instanceof final IMultiBackReference<?> multiBackReference) {
      return StringContentFieldDto.withColumnName(multiBackReference.getName());
    }

    throw InvalidArgumentException.forArgument(field);
  }
}
