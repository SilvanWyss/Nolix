/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.document.xml;

import org.junit.jupiter.api.Test;

import ch.nolix.base.document.xml.XmlAttribute;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class XmlAttributeTest extends StandardTest {
  @Test
  void testCase_constructor() {
    // execute
    final var result = XmlAttribute.withNameAndValue("color", "green");

    // verify
    expect(result.getName()).isEqualTo("color");
    expect(result.getValue()).isEqualTo("green");
  }

  @Test
  void testCase_toString() {
    // setup
    final var xmlAttribute = XmlAttribute.withNameAndValue("color", "green");

    // execute
    final var result = xmlAttribute.toString();

    // verify
    expect(result).isEqualTo("color='green'");
  }
}
