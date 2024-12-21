package ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi;

import ch.nolix.coreapi.datamodelapi.entityrequestapi.AbstractnessRequestable;
import ch.nolix.coreapi.datamodelapi.fieldrequestapi.ContentTypeRequestable;
import ch.nolix.coreapi.stateapi.staterequestapi.EmptinessRequestable;

public interface IContent extends AbstractnessRequestable, ContentTypeRequestable, EmptinessRequestable {

  ICategorizableField getStoredParentField();
}
