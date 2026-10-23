/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.link;

import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface ILink extends Control<ILink, ILinkStyle> {
  /**
   * @return the display text of the current {@link ILink}
   */
  String getDisplayText();

  /**
   * @return the {@link LinkTarget} of the current {@link ILink}
   */
  LinkTarget getTarget();

  /**
   * @return the URL of the current {@link ILink}
   * @throws RuntimeException if the current {@link ILink} does not have a URL
   */
  String getUrl();

  /**
   * @return true if the current {@link ILink} has a URL, false otherwise
   */
  boolean hasUrl();

  /**
   * Removes the URL of the current {@link ILink}.
   */
  void removeUrl();

  /**
   * Sets the given displayText to the current {@link ILink}.
   * 
   * @param displayText
   * @return the current {@link ILink}
   */
  ILink setDisplayText(String displayText);

  /**
   * Sets the given target to the current {@link ILink}.
   * 
   * @param target
   * @return the current {@link ILink}
   */
  ILink setTarget(LinkTarget target);

  /**
   * Sets the given url to the current {@link ILink}.
   * 
   * @param url
   * @return the current {@link ILink}
   */
  ILink setUrl(String url);

  /**
   * Sets the given url to the current {@link ILink} and uses the given url as
   * display text of the current {@link ILink}.
   * 
   * @param url
   * @return the current {@link ILink}
   */
  ILink setUrlAndDisplayTextFromIt(String url);
}
