//package declaration
package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

//own imports
import ch.nolix.coreapi.webapi.webproperty.LinkTarget;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ILink extends IControl<ILink, ILinkStyle> {

  //method declaration
  String getDisplayText();

  //method declaration
  LinkTarget getTarget();

  //method declaration
  String getUrl();

  //method declaration
  boolean hasUrl();

  //method declaration
  void removeUrl();

  //method declaration
  ILink setDisplayText(String displayText);

  //method declaration
  ILink setTarget(LinkTarget target);

  //method declaration
  ILink setUrl(String url);
}
