package ch.nolix.coreapi.objectstructure.linking;

public interface Linkable extends LinkedRequestable {

  void linkTo(Object object);
}
