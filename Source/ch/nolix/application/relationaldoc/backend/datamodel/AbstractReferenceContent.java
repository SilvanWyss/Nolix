package ch.nolix.application.relationaldoc.backend.datamodel;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IReferenceContent;
import ch.nolix.coreapi.datamodelapi.fieldproperty.ContentType;

public abstract class AbstractReferenceContent extends AbstractContent implements IReferenceContent {

  @Override
  public final ContentType getContentType() {
    return ContentType.REFERENCE;
  }

  @Override
  public final boolean isForValues() {
    return false;
  }
}
