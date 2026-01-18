/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.net.endpoint;

interface INetEndPointProcessor {
  void sendRawMessage(final String rawMessage);
}
