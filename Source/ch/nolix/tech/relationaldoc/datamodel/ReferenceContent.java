package ch.nolix.tech.relationaldoc.datamodel;

import ch.nolix.coreapi.datamodelapi.fieldproperty.ContentType;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IReferenceContent;

public abstract class ReferenceContent extends Content implements IReferenceContent {

  @Override
  public final ContentType getContentType() {
    return ContentType.REFERENCE;
  }

  @Override
  public final boolean isForValues() {
    return false;
  }
}
