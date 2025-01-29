package ch.nolix.systemapi.rawdataapi.modelapi;

import java.util.Optional;

public record StringContentFieldDto(String columnName, Optional<String> optionalContent) {

  public static StringContentFieldDto withColumnName(final String columnName) {
    return new StringContentFieldDto(columnName, Optional.empty());
  }

  public static StringContentFieldDto withColumnNameAndContent(final String columnName, String content) {
    return new StringContentFieldDto(columnName, Optional.of(content));
  }
}
