/**
 * This class file was automatically generated by jASN1 v1.9.0 (http://www.openmuc.org)
 */

package org.openmuc.openiec61850.internal.mms.asn1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openmuc.jasn1.ber.BerLength;
import org.openmuc.jasn1.ber.BerTag;
import org.openmuc.jasn1.ber.ReverseByteArrayOutputStream;
import org.openmuc.jasn1.ber.types.BerNull;

public class WriteResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    public static class CHOICE implements Serializable {

        private static final long serialVersionUID = 1L;

        public byte[] code = null;
        private DataAccessError failure = null;
        private BerNull success = null;

        public CHOICE() {
        }

        public CHOICE(byte[] code) {
            this.code = code;
        }

        public void setFailure(DataAccessError failure) {
            this.failure = failure;
        }

        public DataAccessError getFailure() {
            return failure;
        }

        public void setSuccess(BerNull success) {
            this.success = success;
        }

        public BerNull getSuccess() {
            return success;
        }

        public int encode(OutputStream os) throws IOException {

            if (code != null) {
                for (int i = code.length - 1; i >= 0; i--) {
                    os.write(code[i]);
                }
                return code.length;
            }

            int codeLength = 0;
            if (success != null) {
                codeLength += success.encode(os, false);
                // write tag: CONTEXT_CLASS, PRIMITIVE, 1
                os.write(0x81);
                codeLength += 1;
                return codeLength;
            }

            if (failure != null) {
                codeLength += failure.encode(os, false);
                // write tag: CONTEXT_CLASS, PRIMITIVE, 0
                os.write(0x80);
                codeLength += 1;
                return codeLength;
            }

            throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
        }

        public int decode(InputStream is) throws IOException {
            return decode(is, null);
        }

        public int decode(InputStream is, BerTag berTag) throws IOException {

            int codeLength = 0;
            BerTag passedTag = berTag;

            if (berTag == null) {
                berTag = new BerTag();
                codeLength += berTag.decode(is);
            }

            if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
                failure = new DataAccessError();
                codeLength += failure.decode(is, false);
                return codeLength;
            }

            if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
                success = new BerNull();
                codeLength += success.decode(is, false);
                return codeLength;
            }

            if (passedTag != null) {
                return 0;
            }

            throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
        }

        public void encodeAndSave(int encodingSizeGuess) throws IOException {
            ReverseByteArrayOutputStream os = new ReverseByteArrayOutputStream(encodingSizeGuess);
            encode(os);
            code = os.getArray();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            appendAsString(sb, 0);
            return sb.toString();
        }

        public void appendAsString(StringBuilder sb, int indentLevel) {

            if (failure != null) {
                sb.append("failure: ").append(failure);
                return;
            }

            if (success != null) {
                sb.append("success: ").append(success);
                return;
            }

            sb.append("<none>");
        }

    }

    public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
    public byte[] code = null;
    private List<CHOICE> seqOf = null;

    public WriteResponse() {
        seqOf = new ArrayList<>();
    }

    public WriteResponse(byte[] code) {
        this.code = code;
    }

    public List<CHOICE> getCHOICE() {
        if (seqOf == null) {
            seqOf = new ArrayList<>();
        }
        return seqOf;
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
        for (int i = (seqOf.size() - 1); i >= 0; i--) {
            codeLength += seqOf.get(i).encode(os);
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
        if (withTag) {
            codeLength += tag.decodeAndCheck(is);
        }

        BerLength length = new BerLength();
        codeLength += length.decode(is);
        int totalLength = length.val;

        while (subCodeLength < totalLength) {
            CHOICE element = new CHOICE();
            subCodeLength += element.decode(is, null);
            seqOf.add(element);
        }
        if (subCodeLength != totalLength) {
            throw new IOException("Decoded SequenceOf or SetOf has wrong length. Expected " + totalLength + " but has "
                    + subCodeLength);

        }
        codeLength += subCodeLength;

        return codeLength;
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

        sb.append("{\n");
        for (int i = 0; i < indentLevel + 1; i++) {
            sb.append("\t");
        }
        if (seqOf == null) {
            sb.append("null");
        }
        else {
            Iterator<CHOICE> it = seqOf.iterator();
            if (it.hasNext()) {
                it.next().appendAsString(sb, indentLevel + 1);
                while (it.hasNext()) {
                    sb.append(",\n");
                    for (int i = 0; i < indentLevel + 1; i++) {
                        sb.append("\t");
                    }
                    it.next().appendAsString(sb, indentLevel + 1);
                }
            }
        }

        sb.append("\n");
        for (int i = 0; i < indentLevel; i++) {
            sb.append("\t");
        }
        sb.append("}");
    }

}
