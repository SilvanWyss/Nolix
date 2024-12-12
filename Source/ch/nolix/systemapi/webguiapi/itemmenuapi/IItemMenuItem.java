package ch.nolix.systemapi.webguiapi.itemmenuapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;
import ch.nolix.coreapi.stateapi.staterequestapi.BlanknessRequestable;
import ch.nolix.systemapi.elementapi.mutableelementapi.IMutableElement;
import ch.nolix.systemapi.guiapi.selectionapi.Selectable;

public interface IItemMenuItem<I extends IItemMenuItem<I>>
extends BlanknessRequestable, IIdHolder, IMutableElement, Selectable {

  boolean belongsToMenu();

  String getText();

  void internalSetParentMenu(IItemMenu<?, ?> parentMenu);
}
