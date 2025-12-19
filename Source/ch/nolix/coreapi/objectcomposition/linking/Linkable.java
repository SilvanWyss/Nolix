package ch.nolix.coreapi.objectcomposition.linking;

/**
 * @author Silvan Wyss
 */
public interface Linkable extends LinkedRequestable {
  void linkTo(Object object);
}
