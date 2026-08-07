/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.messageserver;

interface INetEndPointProcessor {
  void sendRawMessage(final String rawMessage);
}
