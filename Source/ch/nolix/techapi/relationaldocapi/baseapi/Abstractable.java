package ch.nolix.techapi.relationaldocapi.baseapi;

import ch.nolix.coreapi.datamodelapi.entityrequestapi.AbstractnessRequestable;

public interface Abstractable<A extends Abstractable<A>> extends AbstractnessRequestable {

  A setAsAbstract();

  A setAsConcrete();
}
