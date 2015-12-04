package com.github.onionjake.doh;

/**
 * Created by jake on 11/28/15.
 */
public class Options {
    private String password;
    private String domain;
    private String sequence;
    private String salt;
    private int length;

    public Options() {

    }

    public Options(String password, String domain, String sequence, String salt) {
        this.password = password;
        this.domain = domain;
        this.sequence = sequence;
        this.salt = salt;
    }

    // region Getter & Setter

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


    // endregion
}
