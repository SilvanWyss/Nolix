//package declaration
package ch.nolix.systemapi.webguiapi.controlstyleapi;

//interface
public interface IControlStyle<ECS extends IControlStyle<ECS>>
extends IBackgroundStyle<ECS>, IBorderControlStyle<ECS>, IBaseControlStyle<ECS>, ISizeStyle<ECS> {}
