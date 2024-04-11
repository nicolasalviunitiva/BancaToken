package org.acme.enumer;

public enum Stati {

    ACCETTATA ("ok"), 
    RIFIUTATA ("ko"), 
    LAVORAZIONE ("progress");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private Stati(String value) {
        this.value = value;
    }

    

}
