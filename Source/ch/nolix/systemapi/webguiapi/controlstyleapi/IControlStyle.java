//package declaration
package ch.nolix.systemapi.webguiapi.controlstyleapi;

//interface
public interface IControlStyle<ECS extends IControlStyle<ECS>>
extends IBackgroundStyle<ECS>, IBorderStyle<ECS>, IControlHeadStyle<ECS>, ISizeStyle<ECS> {}
