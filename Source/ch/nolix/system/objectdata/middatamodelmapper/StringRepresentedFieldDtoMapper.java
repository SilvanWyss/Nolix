package ch.nolix.system.objectdata.middatamodelmapper;

import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.container.base.IContainer;
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
public final class StringRepresentedFieldDtoMapper implements IStringRepresentedFieldDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<StringRepresentedFieldDto> mapFieldsToStringRepresentedFieldDtos(
    final IContainer<? extends IField> fields) {
    return fields.toMultiples(this::mapFieldToStringRepresentedFieldDtos);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<StringRepresentedFieldDto> mapFieldToStringRepresentedFieldDtos(final IField field) {

    if (field instanceof final IValueField<?> value) {
      return //
      SingleContainer
        .withElement(new StringRepresentedFieldDto(value.getName(), value.getStoredValue().toString(), null));
    }

    if (field instanceof final IOptionalValueField<?> optionalValue) {
      return StringRepresentedFieldDtoMapperHelper.mapOptionalValueToStringContentFieldDtos(optionalValue);
    }

    if (field instanceof final IMultiValueField<?> multiValue) {
      return SingleContainer.withElement(new StringRepresentedFieldDto(multiValue.getName(), null, null));
    }

    if (field instanceof final IReference<?> reference) {
      return //
      SingleContainer.withElement(
        new StringRepresentedFieldDto(reference.getName(), reference.getReferencedEntityId(), null));
    }

    if (field instanceof final IOptionalReference<?> optionalReference) {
      return StringRepresentedFieldDtoMapperHelper.mapOptionalReferenceToStringContentFieldDtos(optionalReference);
    }

    if (field instanceof final IMultiReference<?> multiReference) {
      return SingleContainer.withElement(new StringRepresentedFieldDto(multiReference.getName(), null, null));
    }

    if (field instanceof final IBackReference<?> backReference) {
      return //
      SingleContainer.withElement(
        new StringRepresentedFieldDto(backReference.getName(), backReference.getBackReferencedEntityId(), null));
    }

    if (field instanceof final IOptionalBackReference<?> optionalBackReference) {
      return //
      StringRepresentedFieldDtoMapperHelper.mapOptionalBackReferenceToStringContentFieldDtos(optionalBackReference);
    }

    if (field instanceof final IMultiBackReference<?> multiBackReference) {
      return SingleContainer.withElement(new StringRepresentedFieldDto(multiBackReference.getName(), null, null));
    }

    throw InvalidArgumentException.forArgument(field);
  }
}
