package com.lutshe.emenu.model;

import org.simpleframework.xml.Element;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@Element(name = "server")
public class Server {

    @Element
    String url;

    @Element
    String login;

    @Element
    String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
