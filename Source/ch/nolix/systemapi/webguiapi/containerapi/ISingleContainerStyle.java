//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

//own imports
import ch.nolix.systemapi.webguiapi.controlstyleapi.IExtendedControlStyle;

//interface
public interface ISingleContainerStyle<SCS extends ISingleContainerStyle<SCS>> extends IExtendedControlStyle<SCS> {}
