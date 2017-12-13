/**
 * This class file was automatically generated by jASN1 v1.9.0 (http://www.openmuc.org)
 */

package org.openmuc.josistack.internal.acse.asn1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import org.openmuc.jasn1.ber.BerLength;
import org.openmuc.jasn1.ber.BerTag;
import org.openmuc.jasn1.ber.ReverseByteArrayOutputStream;

public class RLRQApdu implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final BerTag tag = new BerTag(BerTag.APPLICATION_CLASS, BerTag.CONSTRUCTED, 2);

    public byte[] code = null;
    private ReleaseRequestReason reason = null;
    private AssociationInformation userInformation = null;

    public RLRQApdu() {
    }

    public RLRQApdu(byte[] code) {
        this.code = code;
    }

    public void setReason(ReleaseRequestReason reason) {
        this.reason = reason;
    }

    public ReleaseRequestReason getReason() {
        return reason;
    }

    public void setUserInformation(AssociationInformation userInformation) {
        this.userInformation = userInformation;
    }

    public AssociationInformation getUserInformation() {
        return userInformation;
    }

    public int encode(OutputStream os) throws IOException {
        return encode(os, true);
    }

    public int encode(OutputStream os, boolean withTag) throws IOException {

        if (code != null) {
            for (int i = code.length - 1; i >= 0; i--) {
                os.write(code[i]);
            }
            if (withTag) {
                return tag.encode(os) + code.length;
            }
            return code.length;
        }

        int codeLength = 0;
        if (userInformation != null) {
            codeLength += userInformation.encode(os, false);
            // write tag: CONTEXT_CLASS, CONSTRUCTED, 30
            os.write(0xBE);
            codeLength += 1;
        }

        if (reason != null) {
            codeLength += reason.encode(os, false);
            // write tag: CONTEXT_CLASS, PRIMITIVE, 0
            os.write(0x80);
            codeLength += 1;
        }

        codeLength += BerLength.encodeLength(os, codeLength);

        if (withTag) {
            codeLength += tag.encode(os);
        }

        return codeLength;

    }

    public int decode(InputStream is) throws IOException {
        return decode(is, true);
    }

    public int decode(InputStream is, boolean withTag) throws IOException {
        int codeLength = 0;
        int subCodeLength = 0;
        BerTag berTag = new BerTag();

        if (withTag) {
            codeLength += tag.decodeAndCheck(is);
        }

        BerLength length = new BerLength();
        codeLength += length.decode(is);

        int totalLength = length.val;
        codeLength += totalLength;

        if (totalLength == 0) {
            return codeLength;
        }
        subCodeLength += berTag.decode(is);
        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
            reason = new ReleaseRequestReason();
            subCodeLength += reason.decode(is, false);
            if (subCodeLength == totalLength) {
                return codeLength;
            }
            subCodeLength += berTag.decode(is);
        }

        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 30)) {
            userInformation = new AssociationInformation();
            subCodeLength += userInformation.decode(is, false);
            if (subCodeLength == totalLength) {
                return codeLength;
            }
        }
        throw new IOException("Unexpected end of sequence, length tag: " + totalLength + ", actual sequence length: "
                + subCodeLength);

    }

    public void encodeAndSave(int encodingSizeGuess) throws IOException {
        ReverseByteArrayOutputStream os = new ReverseByteArrayOutputStream(encodingSizeGuess);
        encode(os, false);
        code = os.getArray();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendAsString(sb, 0);
        return sb.toString();
    }

    public void appendAsString(StringBuilder sb, int indentLevel) {

        sb.append("{");
        boolean firstSelectedElement = true;
        if (reason != null) {
            sb.append("\n");
            for (int i = 0; i < indentLevel + 1; i++) {
                sb.append("\t");
            }
            sb.append("reason: ").append(reason);
            firstSelectedElement = false;
        }

        if (userInformation != null) {
            if (!firstSelectedElement) {
                sb.append(",\n");
            }
            for (int i = 0; i < indentLevel + 1; i++) {
                sb.append("\t");
            }
            sb.append("userInformation: ");
            userInformation.appendAsString(sb, indentLevel + 1);
            firstSelectedElement = false;
        }

        sb.append("\n");
        for (int i = 0; i < indentLevel; i++) {
            sb.append("\t");
        }
        sb.append("}");
    }

}
