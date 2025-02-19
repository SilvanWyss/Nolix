package ch.nolix.systemapi.rawdataapi.modelapi;

public record ContentFieldDto<C>(String columnName, C optionalContent) {
}
