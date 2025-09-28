/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc;

import org.infino.jdbc.internal.util.UrlParser;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class InfinoJdbcTest {

    @Test
    public void testInfinoUrlParsing() throws URISyntaxException {
        String url = "jdbc:infino://localhost:9200/account123";

        assertTrue(UrlParser.isAcceptable(url), "URL should be acceptable");

        Properties props = UrlParser.parseProperties(url);

        assertEquals("localhost", props.getProperty("host"));
        assertEquals("9200", props.getProperty("port"));
        assertEquals("/account123", props.getProperty("path"));
        assertEquals("account123", props.getProperty("accountid"));
        assertEquals("false", props.getProperty("usessl"));
    }

    @Test
    public void testInfinoUrlWithHttps() throws URISyntaxException {
        String url = "jdbc:infino://https://example.com:443/myaccount";

        Properties props = UrlParser.parseProperties(url);

        assertEquals("example.com", props.getProperty("host"));
        assertEquals("443", props.getProperty("port"));
        assertEquals("/myaccount", props.getProperty("path"));
        assertEquals("myaccount", props.getProperty("accountid"));
        assertEquals("true", props.getProperty("usessl"));
    }

    @Test
    public void testInfinoUrlWithQueryParams() throws URISyntaxException {
        String url = "jdbc:infino://localhost:9200/account123?auth=aws_sigv4&region=us-east-1";

        Properties props = UrlParser.parseProperties(url);

        assertEquals("localhost", props.getProperty("host"));
        assertEquals("account123", props.getProperty("accountid"));
        assertEquals("aws_sigv4", props.getProperty("auth"));
        assertEquals("us-east-1", props.getProperty("region"));
    }
}
