package com.github.onionjake.doh;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jake on 11/28/15.
 */
public class Options implements Parcelable {
    private byte[] password;
    private String domain;
    private String sequence;
    private String salt;
    private int length;

    public Options() {

    }

    public Options(byte[] password, String domain, String sequence, String salt) {
        this.password = password;
        this.domain = domain;
        this.sequence = sequence;
        this.salt = salt;
    }


    protected Options(Parcel in) {
        password = in.createByteArray();
        domain = in.readString();
        sequence = in.readString();
        salt = in.readString();
        length = in.readInt();
    }

    public static final Creator<Options> CREATOR = new Creator<Options>() {
        @Override
        public Options createFromParcel(Parcel in) {
            return new Options(in);
        }

        @Override
        public Options[] newArray(int size) {
            return new Options[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByteArray(password);
        dest.writeString(domain);
        dest.writeString(sequence);
        dest.writeString(salt);
        dest.writeInt(length);
    }


    // region Getter & Setter

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
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
