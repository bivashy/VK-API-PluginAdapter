package com.ubivashka.vk.http.proxy;

public enum DefaultSystemProxyApplier implements ProxyApplier {
    HTTP("http.proxyHost", "http.proxyPort"), HTTPS("https.proxyHost", "https.proxyPort"), FTP("ftp.proxyHost", "ftp.proxyPort"), SOCKS("socksProxyHost",
            "socksProxyPort"), NONE {
        @Override
        public void apply(String host, int port) {
        }
    };

    private final String PROXY_SET_KEY = "proxySet";
    private final String PROXY_SET_VALUE = "true";

    private String hostKey, portKey;

    DefaultSystemProxyApplier() {
    }

    DefaultSystemProxyApplier(String hostKey, String portKey) {
        this.hostKey = hostKey;
        this.portKey = portKey;
    }

    @Override
    public void apply(String host, int port) {
        System.setProperty(PROXY_SET_KEY, PROXY_SET_VALUE);
        System.setProperty(hostKey, host);
        System.setProperty(portKey, Integer.toString(port));
    }
}
