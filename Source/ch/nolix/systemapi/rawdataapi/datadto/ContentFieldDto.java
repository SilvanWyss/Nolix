package ch.nolix.systemapi.rawdataapi.datadto;

import java.util.Optional;

public record ContentFieldDto(String columnName, Optional<String> optionalContent) {

  public static ContentFieldDto withColumnName(final String columnName) {
    return new ContentFieldDto(columnName, Optional.empty());
  }

  public static ContentFieldDto withColumnNameAndContent(final String columnName, String content) {
    return new ContentFieldDto(columnName, Optional.of(content));
  }
}
