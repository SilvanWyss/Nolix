//package declaration
package ch.nolix.systemapi.guiapi.widgetapi;

//own imports
import ch.nolix.core.griduniversalapi.TopLeftPositionedRecangular;
import ch.nolix.core.requestuniversalapi.EnablingRequestable;
import ch.nolix.core.requestuniversalapi.ExpansionRequestable;
import ch.nolix.core.skilluniversalapi.Recalculable;
import ch.nolix.systemapi.elementapi.configurationapi.IConfigurableElement;
import ch.nolix.systemapi.guiapi.baseapi.IInputActionManager;
import ch.nolix.systemapi.guiapi.inputapi.IInputTaker;

//interface
public interface IWidget<W extends IWidget<W>>
extends
EnablingRequestable,
ExpansionRequestable,
IConfigurableElement<W>,
IInputActionManager<W>,
IInputTaker,
Recalculable,
TopLeftPositionedRecangular {}
