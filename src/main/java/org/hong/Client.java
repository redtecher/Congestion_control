package org.hong;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;


    public static void main(String[] args) {
        try {
            Client udps = new Client("localhost",5555);
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

    public Client(String host, int port) throws IOException {
            this.socket = new DatagramSocket();
            this.address = InetAddress.getByName(host);
            this.port = port;
    }

    public void send(String message) throws IOException {
        byte[] buf = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
        socket.send(packet);
    }

    public void close() {
        socket.close();
    }
}

