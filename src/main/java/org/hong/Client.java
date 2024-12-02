package org.hong;

import java.io.IOException;

public class Client {
    public static void main(String[] args) {
        try {
            UDPSender udps = new UDPSender("localhost",5555);
            int i =0 ;
            while (true) {
                System.out.println("Client are sending...");
                udps.send("Hello World "+i);
                i++;
            }
//            udps.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
