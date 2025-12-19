package ch.nolix.coreapi.attribute.mutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.ITitleHolder;

/**
 * A {@link IMutableTitleHolder} is a {@link ITitleHolder} whose title can be
 * set programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableTitleHolder extends ITitleHolder {
  /**
   * Sets the title of the current {@link IMutableTitleHolder}.
   * 
   * @param title
   * @throws RuntimeException if the given title is null.
   * @throws RuntimeException if the given title is blank.
   */
  void setTitle(String title);
}
