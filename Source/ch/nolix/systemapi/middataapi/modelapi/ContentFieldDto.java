package ch.nolix.systemapi.middataapi.modelapi;

public record ContentFieldDto<C>(String columnName, C optionalContent) {
}
