package ch.nolix.systemapi.sqlschemaapi.schemadto;

import java.util.Optional;

public record DataTypeDto(String name, Optional<String> optionalParameter) {
}
