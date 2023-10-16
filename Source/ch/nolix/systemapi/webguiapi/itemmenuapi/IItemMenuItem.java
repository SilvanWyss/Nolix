//package declaration
package ch.nolix.systemapi.webguiapi.itemmenuapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Identified;
import ch.nolix.coreapi.functionapi.requestapi.BlanknessRequestable;
import ch.nolix.systemapi.elementapi.mainapi.IMutableElement;
import ch.nolix.systemapi.guiapi.processapi.Selectable;

//interface
public interface IItemMenuItem<IMI extends IItemMenuItem<IMI>>
    extends BlanknessRequestable, Identified, IMutableElement, Selectable {

  //method declaration
  boolean belongsToMenu();

  //method declaration
  String getText();

  //method declaration
  void technicalSetParentMenu(IItemMenu<?, ?> parentMenu);
}
