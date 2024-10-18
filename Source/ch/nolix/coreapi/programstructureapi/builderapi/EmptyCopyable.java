package ch.nolix.coreapi.programstructureapi.builderapi;

public interface EmptyCopyable<EC extends EmptyCopyable<EC>> {

  EC getEmptyCopy();
}
