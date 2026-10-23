/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.html;

/**
 * @author Silvan Wyss
 */
public interface IHtmlElementEvent {
  /**
   * @return the id of the HTML element of the current {@link IHtmlElementEvent}
   */
  String getHtmlElementId();

  /**
   * @return the HTML event of the current {@link IHtmlElementEvent}
   */
  String getHtmlEvent();
}
