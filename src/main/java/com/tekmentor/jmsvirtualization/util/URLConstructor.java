package com.tekmentor.jmsvirtualization.util;

public class URLConstructor {
    private final static  String SCHEME ="http://";

    public final static String constructUrl(int serverPort, String qName){
        return SCHEME+"localhost:"+serverPort+"/323232232"+"/"+qName;
    }
}

//return SCHEME+"sqs."+region+".amazonaws.com/"+accountId+"/"+qName;