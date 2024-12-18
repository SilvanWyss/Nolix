package ch.nolix.systemapi.rawdataapi.datadto;

import java.util.Optional;

public record ContentFieldDto(String columnName, Optional<String> optionalContent) {
}
