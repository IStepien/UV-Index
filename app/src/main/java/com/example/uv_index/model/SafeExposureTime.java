package com.example.uv_index.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SafeExposureTime {

    @SerializedName("st1")
    @Expose
    private Object st1;
    @SerializedName("st2")
    @Expose
    private Object st2;
    @SerializedName("st3")
    @Expose
    private Object st3;
    @SerializedName("st4")
    @Expose
    private Object st4;
    @SerializedName("st5")
    @Expose
    private Object st5;
    @SerializedName("st6")
    @Expose
    private Object st6;

    public Object getSt1() {
        return st1;
    }

    public void setSt1(Object st1) {
        this.st1 = st1;
    }

    public Object getSt2() {
        return st2;
    }

    public void setSt2(Object st2) {
        this.st2 = st2;
    }

    public Object getSt3() {
        return st3;
    }

    public void setSt3(Object st3) {
        this.st3 = st3;
    }

    public Object getSt4() {
        return st4;
    }

    public void setSt4(Object st4) {
        this.st4 = st4;
    }

    public Object getSt5() {
        return st5;
    }

    public void setSt5(Object st5) {
        this.st5 = st5;
    }

    public Object getSt6() {
        return st6;
    }

    public void setSt6(Object st6) {
        this.st6 = st6;
    }

}
