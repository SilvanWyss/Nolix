//package declaration
package ch.nolix.systemapi.applicationapi.componentapi;

//own imports
import ch.nolix.coreapi.functionapi.requestapi.AlivenessRequestable;
import ch.nolix.coreapi.programcontrolapi.triggerapi.Refreshable;
import ch.nolix.systemapi.webguiapi.basecontainerapi.IControlGetter;

//interface
public interface IComponent extends AlivenessRequestable, IControlGetter, Refreshable {}
