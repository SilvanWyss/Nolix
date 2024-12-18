package ch.nolix.systemapi.rawdataapi.datadto;

import java.util.Optional;

public record ContentFieldWithContentAsStringDto(String columnName, Optional<String> optionalContent) {

  public static ContentFieldWithContentAsStringDto withColumnName(final String columnName) {
    return new ContentFieldWithContentAsStringDto(columnName, Optional.empty());
  }

  public static ContentFieldWithContentAsStringDto withColumnNameAndContent(final String columnName, String content) {
    return new ContentFieldWithContentAsStringDto(columnName, Optional.of(content));
  }
}
