package ch.nolix.coreapi.errorcontrol.validation;

public interface IOptionalMediator {
  void containsEqualObject(Object object);

  void containsObject(Object object);

  void containsObjectOfType(Class<Object> type);

  void isEmpty();

  void isPresent();
}
