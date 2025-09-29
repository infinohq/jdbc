/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.internal.util;

import static org.junit.jupiter.api.Assertions.*;

import org.infino.jdbc.config.HostConnectionProperty;
import org.infino.jdbc.config.PasswordConnectionProperty;
import org.infino.jdbc.config.PathConnectionProperty;
import org.infino.jdbc.config.PortConnectionProperty;
import org.infino.jdbc.config.UseSSLConnectionProperty;
import org.infino.jdbc.config.UserConnectionProperty;
import org.infino.jdbc.internal.util.UrlParser;
import org.infino.jdbc.test.KeyValuePairs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.URISyntaxException;
import java.util.Properties;

class UrlParserTests {

    @ParameterizedTest
    @ValueSource(strings = {
            "jdbc:infino://host:9200",
            "jdbc:infino://host:9200/path",
            "jdbc:infino://host:9200/path/",
            "jdbc:infino://host:9200/path?option=value",
            "jdbc:infino://host:9200/path?option=value&option2=value2",
            "jdbc:infino://host/path",
            "jdbc:infino://host/path/",
            "jdbc:infino://host/path?option=value&option2=value2",
    })
    void testIsAcceptable(String url) {
        assertTrue(UrlParser.isAcceptable(url), () -> url + " was not accepted");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "jdbc:Infino:/",
            "Infino://host:9200/path",
            "jdbc:Infino:",
            "jdbc:Infino",
            "jdbc://host:9200/"
    })
    void testIsNotAcceptable(String url) {
        assertFalse(UrlParser.isAcceptable(url), () -> url + " was accepted");
    }

    @Test
    void testNullNotAcceptable() {
        assertFalse(UrlParser.isAcceptable(null), "null was accepted");
    }

    @Test
    void testPropertiesFromURL() throws URISyntaxException {

        propertiesFromUrl("jdbc:infino://")
                .match(); // empty properties

        propertiesFromUrl("jdbc:infino://https://localhost:9200/")
                .match(
                        KeyValuePairs.skvp(HostConnectionProperty.KEY, "localhost"),
                        KeyValuePairs.skvp(PortConnectionProperty.KEY, "9200"),
                        KeyValuePairs.skvp(UseSSLConnectionProperty.KEY, "true"),
                        KeyValuePairs.skvp(PathConnectionProperty.KEY, "/"));

        propertiesFromUrl("jdbc:infino://localhost:9200")
                .match(
                        KeyValuePairs.skvp(HostConnectionProperty.KEY, "localhost"),
                        KeyValuePairs.skvp(PortConnectionProperty.KEY, "9200"),
                        KeyValuePairs.skvp(UseSSLConnectionProperty.KEY, "false"));

        propertiesFromUrl("jdbc:infino://es-domain-name.sub.hostname.com:1080")
                .match(
                        KeyValuePairs.skvp(HostConnectionProperty.KEY, "es-domain-name.sub.hostname.com"),
                        KeyValuePairs.skvp(PortConnectionProperty.KEY, "1080"),
                        KeyValuePairs.skvp(UseSSLConnectionProperty.KEY, "false"));

        propertiesFromUrl("jdbc:infino://es-domain-name.sub.hostname.com:1090/")
                .match(
                        KeyValuePairs.skvp(HostConnectionProperty.KEY, "es-domain-name.sub.hostname.com"),
                        KeyValuePairs.skvp(PortConnectionProperty.KEY, "1090"),
                        KeyValuePairs.skvp(UseSSLConnectionProperty.KEY, "false"),
                        KeyValuePairs.skvp(PathConnectionProperty.KEY, "/"));

    }

    @Test
    public void testPropertiesFromLongUrl() {
        propertiesFromUrl(
                "jdbc:infino://search-Infino-es23-dedm-za-1-edmwao5g64rlo3hcohapy2jpru.us-east-1.es.a9.com")
                .match(
                        KeyValuePairs.skvp(HostConnectionProperty.KEY,
                                "search-Infino-es23-dedm-za-1-edmwao5g64rlo3hcohapy2jpru.us-east-1.es.a9.com"),
                        KeyValuePairs.skvp(UseSSLConnectionProperty.KEY, "false"));
    }

    @Test
    public void testPropertiesFromUrlInvalidPrefix() {
        String url = "jdbc:unknown://https://localhost:9200/";

        URISyntaxException ex = assertThrows(URISyntaxException.class, () -> UrlParser.parseProperties(url));
        assertTrue(ex.getMessage().contains(UrlParser.URL_PREFIX));
    }

    @Test
    public void testPropertiesFromUrlInvalidScheme() {
        String url = "jdbc:infino://tcp://domain-name.sub-domain.com:9023";

        URISyntaxException ex = assertThrows(URISyntaxException.class, () -> UrlParser.parseProperties(url));
        assertTrue(ex.getMessage().contains("Invalid scheme:tcp"));
    }

    @Test
    public void testPropertiesFromUrlHttpsScheme() {
        String url = "jdbc:infino://https://domain-name.sub-domain.com:9023";

        propertiesFromUrl("jdbc:infino://https://domain-name.sub-domain.com:9023")
                .match(
                        KeyValuePairs.skvp(HostConnectionProperty.KEY, "domain-name.sub-domain.com"),
                        KeyValuePairs.skvp(PortConnectionProperty.KEY, "9023"),
                        KeyValuePairs.skvp(UseSSLConnectionProperty.KEY, "true"));
    }

    @Test
    public void testPropertiesFromUrlHttpsSchemeAndPath() {
        propertiesFromUrl("jdbc:infino://https://domain-name.sub-domain.com:9023/context/path")
                .match(
                        KeyValuePairs.skvp(HostConnectionProperty.KEY, "domain-name.sub-domain.com"),
                        KeyValuePairs.skvp(PortConnectionProperty.KEY, "9023"),
                        KeyValuePairs.skvp(UseSSLConnectionProperty.KEY, "true"),
                        KeyValuePairs.skvp(PathConnectionProperty.KEY, "/context/path"));
    }

    @Test
    public void testPropertiesFromUrlAndQueryString() {
        propertiesFromUrl("jdbc:infino://https://domain-name.sub-domain.com:9023/context/path?" +
                "user=username123&password=pass@$!w0rd")
                .match(
                        KeyValuePairs.skvp(HostConnectionProperty.KEY, "domain-name.sub-domain.com"),
                        KeyValuePairs.skvp(PortConnectionProperty.KEY, "9023"),
                        KeyValuePairs.skvp(UseSSLConnectionProperty.KEY, "true"),
                        KeyValuePairs.skvp(PathConnectionProperty.KEY, "/context/path"),
                        KeyValuePairs.skvp(UserConnectionProperty.KEY, "username123"),
                        KeyValuePairs.skvp(PasswordConnectionProperty.KEY, "pass@$!w0rd"));
    }

    @Test
    public void testPropertiesFromUrlWithInvalidQueryString() {
        final String url = "jdbc:infino://https://domain-name.sub-domain.com:9023/context/path?prop=value=3";

        URISyntaxException ex = assertThrows(URISyntaxException.class, () -> UrlParser.parseProperties(url));
        assertTrue(ex.getMessage().contains("Expected key=value pairs"));
    }

    private ConnectionPropertyMatcher propertiesFromUrl(String url) {
        Properties props = Assertions.assertDoesNotThrow(() -> UrlParser.parseProperties(url),
                () -> "Exception occurred when parsing URL: " + url);
        return new ConnectionPropertyMatcher(props);
    }

    private class ConnectionPropertyMatcher {
        Properties properties;

        public ConnectionPropertyMatcher(Properties props) {
            this.properties = props;
        }

        public void match(KeyValuePairs.StringKvp... keyValuePairs) {
            assertEquals(KeyValuePairs.toProperties(keyValuePairs), properties);
        }
    }
}
