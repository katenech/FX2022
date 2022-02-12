package com.example.fx2022;

public class Scheme {
    private Vector profile;

    public  Scheme(String text, int k, int d){
        int n = text.length();

        double[] freq = new double[d];
        for(int i = 0;i<n-k+1; i++){
            String kgram =  text.substring(i,i+k);

            int hash = kgram.hashCode();

            freq[Math.abs(hash % d)] +=1;

        }
        Vector vector = new Vector(freq);
        profile = vector.direction();
    }

    //! сравнивает пару документов
    public double similarTo(Scheme other){
        return profile.dot(other.profile);
    }

    public  String toString(){
        return "" + profile;
    }



}
