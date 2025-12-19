package ch.nolix.systemapi.webatomiccontrol.link;

import ch.nolix.coreapi.web.htmlattribute.LinkTarget;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public interface ILink extends IControl<ILink, ILinkStyle> {
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
