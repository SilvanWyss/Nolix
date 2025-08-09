package ch.nolix.coreapi.objectcomposition.linking;

public interface Linkable extends LinkedRequestable {

  void linkTo(Object object);
}
