//package declaration
package ch.nolix.systemapi.guiapi.controllookapi;

//interface
public interface IExtendedControlLook<ECL extends IExtendedControlLook<ECL>>
extends IBackgroundControlLook<ECL>, IBorderControlLook<ECL>, IControlLook<ECL> {}
