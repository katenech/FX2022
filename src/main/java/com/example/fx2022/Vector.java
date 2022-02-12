package com.example.fx2022;

public class Vector {

    private int n;
    private double [] data;

    public Vector(int n){
        this.n=n;
        this.data =  new double[n];
    }

    public Vector(double[] data) {
        n = data.length;

        this.data = new double[n];
        for (int i = 0; i < n; i++) {
            this.data[i] = data[i];
        }
    }

    public int length() {
        return n;
    }

    public double cartesian(int i){
        return data[i];
    }

    //скалярное произведение = косинусное расстояние
    public double dot(Vector that){
        if(this.length() != that.length())
            System.out.println("разные размеры векторов");
        double sum = 0.0;
        for(int i = 0;i<n;i++) {
            sum = sum + (this.data[i] * that.data[i]);
        }
        return sum;
    }

    public double magnitude(){
        return Math.sqrt(this.dot(this));
    }

    public double distanceTo(Vector that){
        if(this.length() != that.length())
            System.out.println("разные размеры векторов");
        return this.minus(that).magnitude();
    }

    public Vector minus(Vector that){
        if(this.length() != that.length())
            System.out.println("разные размеры векторов");
        Vector c = new Vector(n);
        for(int i = 0; i <n; i++){
            c.data[i] =  this.data[i] - that.data[i];
        }
        return c;
    }

    public Vector plus(Vector that){
        if(this.length() != that.length())
            System.out.println("разные размеры векторов");
        Vector c = new Vector(n);
        for(int i = 0; i <n; i++){
            c.data[i] =  this.data[i] + that.data[i];
        }
        return c;
    }

    public Vector scale(double factor){
        Vector c =  new Vector(n);
        for(int i =0;i<n;i++)
            c.data[i] = factor * data[i];
        return c;
    }

    public Vector direction(){
        if(this.magnitude() == 0.0)
            System.out.println("разные размеры векторов");
        return this.scale(1.0/this.magnitude());
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append('(');
        for(int i = 0; i<n;i++){
            s.append(data[i]);
            if(i<n-1) s.append(", ");
        }
        s.append(')');
        return s.toString();
    }

    public static void main(String[] args) {
        double [] xdata = { 1.0, 2.0, 3.0, 4.0};
        double [] ydata = { 5.0, 2.0, 4.0, 1.0};

        Vector x = new Vector(xdata);
        Vector y =  new Vector(ydata);

        StdOut.println("x         =          " + x);
        StdOut.println("y         =          " + y);
        StdOut.println("x + y         =          " + x.plus(y));
        StdOut.println("10x         =          " + x.scale(10.0));
        StdOut.println("|x|         =          " + x.magnitude());
        StdOut.println("<x,y>         =          " + x.dot(y));
        StdOut.println("|x - y|         =          " + x.minus(y).magnitude());

    }
}
