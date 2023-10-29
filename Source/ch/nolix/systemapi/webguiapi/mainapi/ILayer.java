//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlGetter;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;
import ch.nolix.systemapi.guiapi.canvasapi.ICanvas;
import ch.nolix.systemapi.guiapi.structureproperty.ContentAlignment;

//interface
public interface ILayer<L extends ILayer<L>>
extends
IHtmlGetter,
ICanvas<L>,
IStylableElement<L>,
IRootControlOwner<L> {

  //method declaration
  boolean belongsToGui();

  //method declaration
  ContentAlignment getContentAlignment();

  //method declaration
  ICssRule getCssRule();

  //method declaration
  String getInternalId();

  //method declaration
  double getOpacity();

  //method declaration
  IWebGui<?> getStoredParentGui();

  //method declaration
  LayerRole getRole();

  //method declaration
  boolean hasInternalId(String internalId);

  //method declaration
  boolean hasRole();

  //method declaration
  void removeSelfFromGui();

  //method declaration
  L setContentAlignment(ContentAlignment contentAlignment);

  //method declaration
  L setRole(LayerRole role);

  //method declaration
  L setOpacity(double opacity);

  //method declaration
  void technicalSetParentGui(IWebGui<?> parentGui);
}
