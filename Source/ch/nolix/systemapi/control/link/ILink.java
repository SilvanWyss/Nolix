/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.link;

import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface ILink extends Control<ILink, ILinkStyle> {
  String getDisplayText();

  LinkTarget getTarget();

  String getUrl();

  boolean hasUrl();

  void removeUrl();

  ILink setDisplayText(String displayText);

  ILink setTarget(LinkTarget target);

  ILink setUrl(String url);

  ILink setUrlAndDisplayTextFromIt(String url);
}
