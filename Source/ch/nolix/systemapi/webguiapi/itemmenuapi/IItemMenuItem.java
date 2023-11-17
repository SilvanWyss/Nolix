//package declaration
package ch.nolix.systemapi.webguiapi.itemmenuapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;
import ch.nolix.coreapi.methodapi.requestapi.BlanknessRequestable;
import ch.nolix.systemapi.elementapi.mainapi.IMutableElement;
import ch.nolix.systemapi.guiapi.processapi.Selectable;

//interface
public interface IItemMenuItem<IMI extends IItemMenuItem<IMI>>
extends BlanknessRequestable, IIdHolder, IMutableElement, Selectable {

  //method declaration
  boolean belongsToMenu();

  //method declaration
  String getText();

  //method declaration
  void technicalSetParentMenu(IItemMenu<?, ?> parentMenu);
}
