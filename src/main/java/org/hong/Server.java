package org.hong;

import java.io.IOException;

public class Server {
    public static void main(String[] args) {
        try {
            UDPReceiver udpr = new UDPReceiver(5555);
            System.out.println("UDP Receiver started");
            while (true) {
                String gettext = udpr.receive();
                System.out.println("Receive from client:"+gettext);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
