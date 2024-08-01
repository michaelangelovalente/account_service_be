package com.beije.account_service_be.business.authentication.utils;

import lombok.Getter;

@Getter
public enum AuthDomainConst {
    BEIJE("beije"),
    ACME("acme"),
    GMAIL("gmail");

    private final String domainName;
    private final String extension;

    AuthDomainConst(String domainName, String extension) {
        this.domainName = domainName;
        this.extension = extension;
    }

    AuthDomainConst(String domainName) {
        this(domainName, ".com");
    }


    public String getEmailRegex() {
        return "^[\\w._%+-]+@" + domainName + "\\" + extension + "$";
    }

}
