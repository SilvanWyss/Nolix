package ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelabasepi;

import ch.nolix.coreapi.datamodelapi.entityrequestapi.AbstractnessRequestable;

public interface CATEGORIZABLE<C extends CATEGORIZABLE<C>> extends AbstractnessRequestable {

  C setAsAbstract();

  C setAsConcrete();
}
