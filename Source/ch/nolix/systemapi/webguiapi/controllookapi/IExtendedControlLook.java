//package declaration
package ch.nolix.systemapi.webguiapi.controllookapi;

//interface
public interface IExtendedControlLook<ECL extends IExtendedControlLook<ECL>>
extends IBackgroundControlLook<ECL>, IBorderControlLook<ECL>, IControlLook<ECL> {}
