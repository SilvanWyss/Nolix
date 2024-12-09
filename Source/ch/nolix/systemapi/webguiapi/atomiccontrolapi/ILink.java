package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

import ch.nolix.coreapi.webapi.webproperty.LinkTarget;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

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
