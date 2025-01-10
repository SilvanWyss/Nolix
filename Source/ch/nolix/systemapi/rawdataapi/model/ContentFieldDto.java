package ch.nolix.systemapi.rawdataapi.model;

import java.util.Optional;

public record ContentFieldDto<C>(String columnName, Optional<C> optionalContent) {

  public static <C2> ContentFieldDto<C2> withColumnName(final String columnName) {
    return new ContentFieldDto<>(columnName, Optional.empty());
  }

  public static <C2> ContentFieldDto<C2> withColumnNameAndContent(final String columnName, C2 content) {
    return new ContentFieldDto<>(columnName, Optional.of(content));
  }
}
