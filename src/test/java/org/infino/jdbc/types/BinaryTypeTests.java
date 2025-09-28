/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.types;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.infino.jdbc.types.BinaryType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BinaryTypeTests {

  @ParameterizedTest
  @CsvSource(value = {
      "U29tZSBiaW5hcnkgYmxvYg==, U29tZSBiaW5hcnkgYmxvYg==",
      "TWFuIGlzIGRpc3Rpbmd1aXN, TWFuIGlzIGRpc3Rpbmd1aXN",
      "YW55IGNhcm5hbCBwbGVhc3VyZS4=, YW55IGNhcm5hbCBwbGVhc3VyZS4="
  })
  void testTimeFromString(String inputString, String result) {
    String binary = Assertions.assertDoesNotThrow(
        () -> BinaryType.INSTANCE.fromValue(inputString, null));
    assertEquals(result, binary);
  }

}
