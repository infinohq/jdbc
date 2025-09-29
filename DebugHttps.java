import java.net.URI;
import java.net.URISyntaxException;

public class DebugHttps {
    public static void main(String[] args) throws URISyntaxException {
        String url = "jdbc:infino://https://example.com:443/myaccount";
        String URL_PREFIX = "jdbc:infino://";
        int URL_PREFIX_LEN = URL_PREFIX.length();
        String SCHEME_DELIM = "://";
        
        System.out.println("Testing HTTPS URL: " + url);
        String trimmedUrl = url.trim();
        int schemeDelimIdx = trimmedUrl.indexOf(SCHEME_DELIM, URL_PREFIX_LEN);
        
        System.out.println("schemeDelimIdx: " + schemeDelimIdx);
        System.out.println("URL_PREFIX_LEN: " + URL_PREFIX_LEN);
        
        URI uri;
        if (schemeDelimIdx != -1) {
            String extracted = trimmedUrl.substring(URL_PREFIX_LEN);
            System.out.println("Has scheme - extracted: " + extracted);
            uri = new URI(extracted);
        } else {
            String extracted = trimmedUrl.substring(URL_PREFIX_LEN);
            String withHttp = "http://" + extracted;
            System.out.println("No scheme - with http: " + withHttp);
            uri = new URI(withHttp);
        }
        
        System.out.println("Final URI: " + uri);
        System.out.println("URI scheme: " + uri.getScheme());
        System.out.println("URI host: " + uri.getHost());
        System.out.println("URI port: " + uri.getPort());
        System.out.println("URI path: " + uri.getPath());
        
        // Test the SSL property logic
        String scheme = uri.getScheme();
        if ("https".equalsIgnoreCase(scheme)) {
            System.out.println("Setting useSSL to true");
        } else if ("http".equalsIgnoreCase(scheme)) {
            System.out.println("Setting useSSL to false");
        } else {
            System.out.println("Unknown scheme: " + scheme);
        }
    }
}
