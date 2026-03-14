/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.level1server;

interface INetEndPointProcessor {
  void sendRawMessage(final String rawMessage);
}
