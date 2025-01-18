package ch.nolix.systemapi.sqlschemaapi.modelapi;

import java.util.Optional;

public record DataTypeDto(String name, Optional<String> optionalParameter) {

  public static DataTypeDto withName(final String name) {
    return new DataTypeDto(name, Optional.empty());
  }

  public static DataTypeDto withNameAndParameter(final String name, final String parameter) {
    return new DataTypeDto(name, Optional.of(parameter));
  }
}
