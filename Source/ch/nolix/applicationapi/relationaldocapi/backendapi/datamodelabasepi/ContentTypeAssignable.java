package ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelabasepi;

import ch.nolix.coreapi.datamodelapi.fieldrequestapi.ContentTypeRequestable;

public interface ContentTypeAssignable<A extends ContentTypeAssignable<A>> extends ContentTypeRequestable {

  A setForReferences();

  A setForValues();
}
