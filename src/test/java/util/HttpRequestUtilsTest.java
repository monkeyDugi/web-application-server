package util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import util.HttpRequestUtils.Pair;

public class HttpRequestUtilsTest {
    @Test
    public void parseQueryString() {
        String queryString = "userId=javajigi";
        Map<String, String> parameters = HttpRequestUtils.parseQueryString(queryString);
        assertThat(parameters.get("userId"), is("javajigi"));
        assertThat(parameters.get("password"), is(nullValue()));

        queryString = "userId=javajigi&password=password2";
        parameters = HttpRequestUtils.parseQueryString(queryString);
        assertThat(parameters.get("userId"), is("javajigi"));
        assertThat(parameters.get("password"), is("password2"));
    }

    @Test
    public void parseQueryString_null() {
        Map<String, String> parameters = HttpRequestUtils.parseQueryString(null);
        assertThat(parameters.isEmpty(), is(true));

        parameters = HttpRequestUtils.parseQueryString("");
        assertThat(parameters.isEmpty(), is(true));

        parameters = HttpRequestUtils.parseQueryString(" ");
        assertThat(parameters.isEmpty(), is(true));
    }

    @Test
    public void parseQueryString_invalid() {
        String queryString = "userId=javajigi&password";
        Map<String, String> parameters = HttpRequestUtils.parseQueryString(queryString);
        assertThat(parameters.get("userId"), is("javajigi"));
        assertThat(parameters.get("password"), is(nullValue()));
    }

    @Test
    public void parseCookies() {
        String cookies = "logined=true; JSessionId=1234";
        Map<String, String> parameters = HttpRequestUtils.parseCookies(cookies);
        assertThat(parameters.get("logined"), is("true"));
        assertThat(parameters.get("JSessionId"), is("1234"));
        assertThat(parameters.get("session"), is(nullValue()));
    }

    @Test
    public void getKeyValue() {
        Pair pair = HttpRequestUtils.getKeyValue("userId=javajigi", "=");
        assertThat(pair, is(new Pair("userId", "javajigi")));
    }

    @Test
    public void getKeyValue_invalid() {
        Pair pair = HttpRequestUtils.getKeyValue("userId", "=");
        assertThat(pair, is(nullValue()));
    }

    @Test
    public void parseHeader() {
        String header = "Content-Length: 59";
        Pair pair = HttpRequestUtils.parseHeader(header);
        assertThat(pair, is(new Pair("Content-Length", "59")));
    }

    @Test
    public void parsePath_no_paramemter() {
        String generalPart = "GET /index.html HTTP/1.1";

        String path = HttpRequestUtils.parsePath(generalPart);

        assertThat(path, is("/index.html"));
    }

    @Test
    public void parsePath_parameter() {
        String generalPart = "GET /user/create?userId=lbd4946&password=1234&name=duck HTTP/1.1";

        String path = HttpRequestUtils.parsePath(generalPart);

        assertThat(path, is("/user/create"));
    }

    @Test
    public void parseParameter() {
        String startLine = "GET /user/create?userId=lbd4946&password=1234&name=duck HTTP/1.1";

        String queryString = HttpRequestUtils.parseParameter(startLine);

        assertThat(queryString, is("userId=lbd4946&password=1234&name=duck"));
    }

    @Test
    public void parseMethod() {
        String generalPart = "GET /user/create?userId=lbd4946&password=1234&name=duck HTTP/1.1";

        String method = HttpRequestUtils.parseMethod(generalPart);

        assertThat(method, is("GET"));
    }
}
