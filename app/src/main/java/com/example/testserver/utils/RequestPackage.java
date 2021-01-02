package com.example.testserver.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class RequestPackage implements Parcelable {

    private String endpoint;
    private String method="GET";
    private Map<String,String> params= new HashMap<>();

    public RequestPackage() {
    }


    protected RequestPackage(Parcel in) {
        endpoint = in.readString();
        method = in.readString();
        int paramsSize = in.readInt();
        this.params = new HashMap<String, String>(paramsSize);
        for (int i = 0; i < paramsSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.params.put(key, value);
        }
    }

    public static final Creator<RequestPackage> CREATOR = new Creator<RequestPackage>() {
        @Override
        public RequestPackage createFromParcel(Parcel in) {
            return new RequestPackage(in);
        }

        @Override
        public RequestPackage[] newArray(int size) {
            return new RequestPackage[size];
        }
    };

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setParams(String key,String value) {
        this.params.put(key,value);
    }

    public Map<String, String> getParams() {
        return params;
    }

    public String getEncodedParams(){
//        this.setParams("province","Sindh");
        StringBuilder sb = new StringBuilder();
        for(String key : params.keySet()){
            String value = null;
            try{
                value =  URLEncoder.encode(params.get(key),"UTF-8");
            }catch (Exception e){
                e.printStackTrace();
            }
            if(sb.length()> 0){
                sb.append("&");
            }
            sb.append(key).append("=").append(value);
        }
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(endpoint);
        dest.writeString(method);
        dest.writeInt(this.params.size());
        for (Map.Entry<String, String> entry : this.params.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
    }
}
