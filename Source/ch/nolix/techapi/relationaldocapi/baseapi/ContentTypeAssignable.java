package ch.nolix.techapi.relationaldocapi.baseapi;

import ch.nolix.coreapi.datamodelapi.fieldrequestapi.ContentTypeRequestable;

public interface ContentTypeAssignable<CTA extends ContentTypeAssignable<CTA>> extends ContentTypeRequestable {

  CTA setForReferences();

  CTA setForValues();
}
