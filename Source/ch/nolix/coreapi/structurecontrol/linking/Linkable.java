package ch.nolix.coreapi.structurecontrol.linking;

public interface Linkable extends LinkedRequestable {

  void linkTo(Object object);
}
