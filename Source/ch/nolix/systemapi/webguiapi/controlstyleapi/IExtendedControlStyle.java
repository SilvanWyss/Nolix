//package declaration
package ch.nolix.systemapi.webguiapi.controlstyleapi;

//interface
public interface IExtendedControlStyle<ECS extends IExtendedControlStyle<ECS>>
extends IBackgroundControlStyle<ECS>, IBorderControlStyle<ECS>, IControlStyle<ECS> {}
