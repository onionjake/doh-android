package com.github.onionjake.doh;

import android.util.Base64;

import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.spongycastle.crypto.params.KeyParameter;

import java.security.Security;

import javax.crypto.SecretKeyFactory;

/**
 * Created by jake on 11/28/15.
 */
public class DohMethod implements Method{

    static {
        Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);
    }

    @Override
    public String Compute(Options o) {

        int length = ((int) Math.ceil(o.getLength() * 6.0 / 8));
        final int iterations = 2000;

        String real_salt = o.getSequence() + o.getDomain() + o.getSalt();

        PKCS5S2ParametersGenerator generator = new PKCS5S2ParametersGenerator(new SHA256Digest());
        generator.init(o.getPassword().getBytes(),real_salt.getBytes(), iterations);
        KeyParameter key = (KeyParameter)generator.generateDerivedMacParameters(length * 8);

        return Base64.encodeToString(key.getKey(), Base64.NO_PADDING | Base64.NO_WRAP);
    }
}
