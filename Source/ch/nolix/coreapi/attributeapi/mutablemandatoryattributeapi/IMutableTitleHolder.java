package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ITitleHolder;

/**
 * A {@link IMutableTitleHolder} is a {@link ITitleHolder} whose title can be
 * set programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-02-09
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
