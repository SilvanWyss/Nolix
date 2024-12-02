package ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelabasepi;

import ch.nolix.coreapi.datamodelapi.entityrequestapi.AbstractnessRequestable;

public interface Abstractable<A extends Abstractable<A>> extends AbstractnessRequestable {

  A setAsAbstract();

  A setAsConcrete();
}
