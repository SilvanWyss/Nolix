//package declaration
package ch.nolix.systemapi.webguiapi.itemmenuapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;
import ch.nolix.coreapi.generalstateapi.staterequestapi.BlanknessRequestable;
import ch.nolix.systemapi.elementapi.mutableelementapi.IMutableElement;
import ch.nolix.systemapi.guiapi.selectionapi.Selectable;

//interface
public interface IItemMenuItem<IMI extends IItemMenuItem<IMI>>
extends BlanknessRequestable, IIdHolder, IMutableElement, Selectable {

  //method declaration
  boolean belongsToMenu();

  //method declaration
  String getText();

  //method declaration
  void internalSetParentMenu(IItemMenu<?, ?> parentMenu);
}
