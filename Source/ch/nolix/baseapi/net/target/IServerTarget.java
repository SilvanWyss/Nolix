/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.target;

import ch.nolix.baseapi.net.netattribute.HostHolder;
import ch.nolix.baseapi.net.netattribute.PortHolder;
import ch.nolix.baseapi.net.netattribute.SecurityModeHolder;

/**
 * @author Silvan Wyss
 */
public interface IServerTarget extends HostHolder, PortHolder, SecurityModeHolder, UrlRepresentable {
  // This interface is a dedicated union of other interfaces.
}
