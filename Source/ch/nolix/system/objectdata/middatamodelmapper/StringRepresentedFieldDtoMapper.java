/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.middatamodelmapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.middata.model.ValueStringFieldDto;
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
 */
public final class StringRepresentedFieldDtoMapper implements IStringRepresentedFieldDtoMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<ValueStringFieldDto> mapFieldsToStringRepresentedFieldDtos(
    final IContainer<? extends IField> fields) {
    return fields.to(this::mapFieldToStringRepresentedFieldDto);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ValueStringFieldDto mapFieldToStringRepresentedFieldDto(final IField field) {
    if (field instanceof final IValueField<?> value) {
      return //
      new ValueStringFieldDto(value.getName(), value.getStoredValue().toString(), null);
    }

    if (field instanceof final IOptionalValueField<?> optionalValue) {
      return StringRepresentedFieldDtoMapperHelper.mapOptionalValueToStringContentFieldDto(optionalValue);
    }

    if (field instanceof final IMultiValueField<?> multiValue) {
      return new ValueStringFieldDto(multiValue.getName(), null, null);
    }

    if (field instanceof final IReference<?> reference) {
      return //

      new ValueStringFieldDto(reference.getName(), reference.getReferencedEntityId(),
        reference.getReferencedTableId());
    }

    if (field instanceof final IOptionalReference<?> optionalReference) {
      return StringRepresentedFieldDtoMapperHelper.mapOptionalReferenceToStringContentFieldDto(optionalReference);
    }

    if (field instanceof final IMultiReference<?> multiReference) {
      return new ValueStringFieldDto(multiReference.getName(), null, null);
    }

    if (field instanceof final IBackReference<?> backReference) {
      return //

      new ValueStringFieldDto(backReference.getName(), backReference.getBackReferencedEntityId(),
        backReference.getBackReferencedTableId());
    }

    if (field instanceof final IOptionalBackReference<?> optionalBackReference) {
      return //
      StringRepresentedFieldDtoMapperHelper.mapOptionalBackReferenceToStringContentFieldDto(optionalBackReference);
    }

    if (field instanceof final IMultiBackReference<?> multiBackReference) {
      return new ValueStringFieldDto(multiBackReference.getName(), null, null);
    }

    throw InvalidArgumentException.forArgument(field);
  }
}
