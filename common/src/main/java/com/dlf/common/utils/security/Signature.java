package com.dlf.common.utils.security;

public class Signature {

    private String bundleName;

    public Signature(String bundleName) {
        this.bundleName = bundleName;
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }
}
