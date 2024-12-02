package org.hong;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSender {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;

    public UDPSender(String host, int port) throws IOException {
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