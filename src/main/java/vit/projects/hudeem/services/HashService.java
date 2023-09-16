package vit.projects.hudeem.services;

import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class HashService {
    public String getHashFrom(String value) {
        if (value == null || value.isBlank() || value.isEmpty()) return null;
        SHA3Digest digestSHA3 = new SHA3Digest(256);

        byte[] inputBytes = value.getBytes(StandardCharsets.UTF_8);
        digestSHA3.update(inputBytes, 0, inputBytes.length);

        byte[] hash = new byte[digestSHA3.getDigestSize()];
        digestSHA3.doFinal(hash, 0);

        return new String(Hex.encode(hash));
    }
}
