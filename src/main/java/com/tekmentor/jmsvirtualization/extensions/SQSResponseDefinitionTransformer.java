package com.tekmentor.jmsvirtualization.extensions;

import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
public class SQSResponseDefinitionTransformer extends ResponseDefinitionTransformer {

    private static final String CHARACTER_ENCODING = "utf-8" ;
    private static final String MESSAGE_ATTRIBUTE =  "";
    private static final int INTEGER_SIZE_IN_BYTES = 0;
//    private static final byte STRING_TYPE_FIELD_INDEX = ;

    @Override
    public ResponseDefinition transform(Request request, ResponseDefinition responseDefinition, FileSource files, Parameters parameters) {
      log.info(" request : {}",request.getAbsoluteUrl());
//        String xmlString = null;
//        try {
//            xmlString = this.calculateResponse(request);
//        }
        return responseDefinition;
    }

    private String calculateResponse(Request request) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String md5OfBody = "";
        String md5OfAttributes= "";
        String valueDecoded = "";
        MessageDigest md = MessageDigest.getInstance("MD5");
        String[] split = request.getBodyAsString().split("&");
        Map<String, String> map = new HashMap<>();
        Arrays.asList(split).stream().filter( e1 -> e1.startsWith("Message"))
                                     .forEach(e1 -> {
                                         String[] splitAtEqual = e1.split("=");
                                         map.put(splitAtEqual[0],splitAtEqual[1]);
                                     });
        if (map.get("MessageBody") !=null){
            valueDecoded = java.net.URLDecoder.decode(map.get("MessageBody"), CHARACTER_ENCODING);
            md.update(valueDecoded.getBytes());
            md5OfBody = DatatypeConverter.printHexBinary(md.digest());
            md.reset();
            map.remove("MessageBody");
        }

        List<SimplifiedMessageAttribute> simplifiedArrayOfAttributes = new ArrayList<>();
        if (!map.isEmpty() && map.size() % 3 ==0 ){
            int numberOfAttributes = map.size() / 3;
            for (int i = 0; i < numberOfAttributes ; i++) {
                String name = java.net.URLDecoder.decode(map.get(MESSAGE_ATTRIBUTE+i+".Name"),CHARACTER_ENCODING);
                String value = java.net.URLDecoder.decode(map.get(MESSAGE_ATTRIBUTE+i+".Value.StringValue"),CHARACTER_ENCODING);
                String dataType = java.net.URLDecoder.decode(map.get(MESSAGE_ATTRIBUTE+i+".Value.DataType"),CHARACTER_ENCODING);
                simplifiedArrayOfAttributes.add(new SimplifiedMessageAttribute(name,value,dataType));
            }
            simplifiedArrayOfAttributes.sort((o1,o2) -> {
                return o1.getName().compareTo(o2.getName());
            });
            for (SimplifiedMessageAttribute attr : simplifiedArrayOfAttributes){
                updateLengthAndBytes(md, attr.getName());
                updateLengthAndBytes(md, attr.getDataType());
//                md.update(STRING_TYPE_FIELD_INDEX);
                updateLengthAndBytes(md, attr.getValue());
            }

            md5OfAttributes = DatatypeConverter.printHexBinary(md.digest());
        }

        return null;
    }

    private void updateLengthAndBytes(MessageDigest digest, String str) throws UnsupportedEncodingException {
        byte[] utf8Encoded = str.getBytes(CHARACTER_ENCODING);
        ByteBuffer byteBuffer = ByteBuffer.allocate(INTEGER_SIZE_IN_BYTES).putInt(utf8Encoded.length);
        digest.update(byteBuffer.array());
        digest.update(utf8Encoded);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean applyGlobally() {
        return true;
    }
}
