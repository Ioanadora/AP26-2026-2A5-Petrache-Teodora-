package com.compulsory10.lab10client;
import java.io.BufferedReader;
import java.io.IOException;

public class ServerListener implements Runnable{
    private final BufferedReader in;
    public ServerListener(BufferedReader in){this.in=in;}
    @Override
    public void run(){
        try{
            String response;
            while((response=in.readLine())!=null) System.out.println(response);
        }catch(IOException event){System.out.println("Disconected");
    }

}}
