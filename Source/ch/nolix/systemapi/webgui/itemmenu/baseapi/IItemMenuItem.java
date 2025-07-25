package ch.nolix.systemapi.webgui.itemmenu.baseapi;

import ch.nolix.coreapi.attribute.mandatoryattribute.IIdHolder;
import ch.nolix.coreapi.state.staterequest.BlanknessRequestable;
import ch.nolix.systemapi.element.mutableelement.IMutableElement;
import ch.nolix.systemapi.gui.selection.Selectable;

public interface IItemMenuItem<I extends IItemMenuItem<I>>
extends BlanknessRequestable, IIdHolder, IMutableElement, Selectable {

  boolean belongsToMenu();

  String getText();

  void internalSetParentMenu(IItemMenu<?, ?> parentMenu);
}
