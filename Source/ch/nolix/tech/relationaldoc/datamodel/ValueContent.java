package ch.nolix.tech.relationaldoc.datamodel;

import ch.nolix.coreapi.datamodelapi.fieldproperty.ContentType;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IValueContent;

public abstract class ValueContent extends Content implements IValueContent {

  @Override
  public final ContentType getContentType() {
    return ContentType.VALUE;
  }

  @Override
  public final boolean isForValues() {
    return true;
  }
}
