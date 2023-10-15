//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.FluentHeaderable;
import ch.nolix.systemapi.guiapi.processapi.Selectable;
import ch.nolix.systemapi.webguiapi.mainapi.IRootControlOwner;

//interface
public interface IAccordionTab extends FluentHeaderable<IAccordionTab>, IRootControlOwner<IAccordionTab>, Selectable {

  // method declaration
  boolean belongsToAccordion();

  // method declaration
  IAccordion getParentAccordion();
}
