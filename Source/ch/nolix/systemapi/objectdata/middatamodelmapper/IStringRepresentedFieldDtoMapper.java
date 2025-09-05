package ch.nolix.systemapi.objectdata.middatamodelmapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.middata.model.StringRepresentedFieldDto;
import ch.nolix.systemapi.objectdata.model.IField;

/**
 * @author Silvan Wyss
 * @version 2024-12-25
 */
public interface IStringRepresentedFieldDtoMapper {
  /**
   * @param fields
   * @return new {@link StringRepresentedFieldDto} from the given fields.
   * @throws RuntimeException if the given fields is null or one of the given
   *                          fields is null.
   */
  IContainer<StringRepresentedFieldDto> mapFieldsToStringRepresentedFieldDtos(IContainer<? extends IField> fields);

  /**
   * @param field
   * @return a new {@link StringRepresentedFieldDto} from the given field.
   * @throws RuntimeException if the given field is null.
   */
  StringRepresentedFieldDto mapFieldToStringRepresentedFieldDto(IField field);
}
