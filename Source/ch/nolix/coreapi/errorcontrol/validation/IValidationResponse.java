package ch.nolix.coreapi.errorcontrol.validation;

public interface IValidationResponse {
  String getMessage();

  boolean isOk();
}
