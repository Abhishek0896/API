package com.example.testserver.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class  HttpHelper {

    public static String downloadUrl(RequestPackage requestPackage) throws IOException {
        InputStream inputStream = null;

        String address =requestPackage.getEndpoint();
        String encodedParams = requestPackage.getEncodedParams();

        if(requestPackage.getRequestMethod().equals("GET") && encodedParams.length() >0){
            address =String.format("%s?%s",address,encodedParams);
        }

        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(10000);
            connection.setDoInput(true);
            connection.setRequestMethod(requestPackage.getRequestMethod());

            if(requestPackage.getRequestMethod().equals("POST") &&
                   encodedParams.length()>0 ){
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(requestPackage.getEncodedParams());
                writer.flush();
                writer.close();
            }
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new Exception("Error: Get response Code :" + responseCode);
            }

            inputStream = connection.getInputStream();
            return readStream(inputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(inputStream != null){
                inputStream.close();
            }
        }
        return null;
    }

    private static String readStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        BufferedOutputStream out = null;
        try{
            int length =0;
            out = new BufferedOutputStream(byteArray);
            while ((length = inputStream.read(buffer)) >0 ){
                out.write(buffer,0,length);
            }
            out.flush();
            return  byteArray.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            if(out != null){
                out.close();
            }
        }
    }
}
