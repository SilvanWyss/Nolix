/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.entitytool;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.middata.model.FieldDto;
import ch.nolix.systemapi.objectdata.entitytool.IEntityContentFieldsFiller;
import ch.nolix.systemapi.objectdata.model.IEntity;

/**
 * @author Silvan Wyss
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

    for (final var f : contentFieldDtos) {
      final var entityField = entityFields.getStoredFirst(f2 -> f2.hasName(f.columnName()));

      entityField.internalSetNullableValue(f.nullableValue(), f.nullableAdditionalValue());
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
