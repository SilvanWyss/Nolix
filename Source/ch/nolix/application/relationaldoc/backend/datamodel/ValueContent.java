package ch.nolix.application.relationaldoc.backend.datamodel;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IValueContent;
import ch.nolix.coreapi.datamodelapi.fieldproperty.ContentType;

public abstract class ValueContent extends AbstractContent implements IValueContent {

  @Override
  public final ContentType getContentType() {
    return ContentType.VALUE;
  }

  @Override
  public final boolean isForValues() {
    return true;
  }
}
