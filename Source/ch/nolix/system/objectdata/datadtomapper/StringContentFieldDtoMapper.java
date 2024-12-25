package ch.nolix.system.objectdata.datadtomapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.objectdataapi.dataapi.IBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.objectdataapi.datadtomapperapi.IStringContentFieldDtoMapper;
import ch.nolix.systemapi.rawdataapi.datadto.StringContentFieldDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-25
 */
public final class StringContentFieldDtoMapper implements IStringContentFieldDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public StringContentFieldDto mapFieldToStringContentFieldDto(final IField field) {

    if (field instanceof final IValue<?> value) {
      return StringContentFieldDto.withColumnNameAndContent(value.getName(), value.getStoredValue().toString());
    }

    if (field instanceof final IOptionalValue<?> optionalValue) {

      if (optionalValue.isEmpty()) {
        return StringContentFieldDto.withColumnName(optionalValue.getName());
      }

      return //
      StringContentFieldDto.withColumnNameAndContent(
        optionalValue.getName(),
        optionalValue.getStoredValue().toString());
    }

    if (field instanceof final IMultiValue<?> multiValue) {
      return StringContentFieldDto.withColumnName(multiValue.getName());
    }

    if (field instanceof final IReference<?> reference) {
      return StringContentFieldDto.withColumnNameAndContent(reference.getName(), reference.getReferencedEntityId());
    }

    if (field instanceof final IOptionalReference<?> optionalReference) {

      if (optionalReference.isEmpty()) {
        return StringContentFieldDto.withColumnName(optionalReference.getName());
      }

      return //
      StringContentFieldDto.withColumnNameAndContent(
        optionalReference.getName(),
        optionalReference.getReferencedEntityId());
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

      if (optionalBackReference.isEmpty()) {
        return StringContentFieldDto.withColumnName(optionalBackReference.getName());
      }

      return StringContentFieldDto.withColumnNameAndContent(
        optionalBackReference.getName(),
        optionalBackReference.getBackReferencedEntityId());
    }

    if (field instanceof final IMultiBackReference<?> multiBackReference) {
      return StringContentFieldDto.withColumnName(multiBackReference.getName());
    }

    throw InvalidArgumentException.forArgument(field);
  }
}
