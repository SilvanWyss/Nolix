package ch.nolix.system.objectdata.entitytool;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.middata.model.FieldDto;
import ch.nolix.systemapi.objectdata.entitytool.IEntityContentFieldsFiller;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;

/**
 * @author Silvan Wyss
 * @version 2025-08-22
 */
public final class EntityContentFieldsFiller implements IEntityContentFieldsFiller {

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillUpEntityContentFieldsFromContentFieldDtos(
    final IEntity entity,
    final IContainer<FieldDto> contentFieldDtos) {

    final var entityFields = entity.internalGetStoredFields();
    FieldDto previousContentFieldDto = null;
    IField previousEntityField = null;

    for (final var f : contentFieldDtos) {
      if (previousContentFieldDto == null) {

        final var entityField = entityFields.getStoredFirst(f2 -> f2.hasName(f.columnName()));

        switch (entityField.getContentCardinality()) {
          case 1:
            entityField.internalSetNullableContent(f.nullableValue());
            break;
          case 2:
            previousContentFieldDto = f;
            previousEntityField = entityField;
            break;
          default:
            throw InvalidArgumentException.forArgument(f);
        }
      } else {

        if (previousContentFieldDto.nullableValue() == null) {
          previousEntityField.internalSetNullableContent(null);
        } else {

          final var content = ImmutableList.withElement(previousContentFieldDto.nullableValue(), f.nullableValue());

          previousEntityField.internalSetNullableContent(content);
        }

        previousContentFieldDto = null;
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillUpEntityContentFieldsFromEntityLoadingDto(
    final IEntity entity,
    final EntityLoadingDto entityLoadingDto) {

    final var contentFields = entityLoadingDto.contentFields();

    fillUpEntityContentFieldsFromContentFieldDtos(entity, contentFields);
  }
}
