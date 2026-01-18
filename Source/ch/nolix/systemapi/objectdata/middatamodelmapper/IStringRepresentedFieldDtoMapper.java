/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.middatamodelmapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.middata.model.ValueStringFieldDto;
import ch.nolix.systemapi.objectdata.model.IField;

/**
 * @author Silvan Wyss
 */
public interface IStringRepresentedFieldDtoMapper {
  /**
   * @param fields
   * @return new {@link ValueStringFieldDto} from the given fields.
   * @throws RuntimeException if the given fields is null or one of the given
   *                          fields is null.
   */
  IContainer<ValueStringFieldDto> mapFieldsToStringRepresentedFieldDtos(IContainer<? extends IField> fields);

  /**
   * @param field
   * @return a new {@link ValueStringFieldDto} from the given field.
   * @throws RuntimeException if the given field is null.
   */
  ValueStringFieldDto mapFieldToStringRepresentedFieldDto(IField field);
}
