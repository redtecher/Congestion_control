package org.hong;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {

    private DatagramSocket socket;

    public Server(int port) throws IOException {
        socket = new DatagramSocket(port);
    }

    public String receive() throws IOException {
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        return new String(packet.getData(), 0, packet.getLength());
    }

    public void close() {
        socket.close();
    }


    public static void main(String[] args) {
        try {
            Server udpr = new Server(5555);
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
