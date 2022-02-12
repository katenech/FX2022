package com.example.fx2022;

public class Classifier {
    private String files;
    private double class1;
    private double class2;
    private double class3;
    private double class4;
    private double class5;

    public Classifier(String files, double class1, double class2, double class3, double class4, double class5) {
        this.files = files;
        this.class1 = class1;
        this.class2 = class2;
        this.class3 = class3;
        this.class4 = class4;
        this.class5 = class5;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public void setClass1(double class1) {
        this.class1 = class1;
    }

    public double getClass2() {
        return class2;
    }

    public void setClass2(double class2) {
        this.class2 = class2;
    }

    public double getClass3() {
        return class3;
    }

    public void setClass3(double class3) {
        this.class3 = class3;
    }

    public double getClass4() {
        return class4;
    }

    public void setClass4(double class4) {
        this.class4 = class4;
    }

    public double getClass5() {
        return class5;
    }

    public void setClass5(double class5) {
        this.class5 = class5;
    }

    public double getClass1() {
        return class1;
    }
}
