//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.systemapi.webguiapi.controlstyleapi.IExtendedControlStyle;

//interface
public interface ILabelStyle<LS extends ILabelStyle<LS>> extends IExtendedControlStyle<LS> {}