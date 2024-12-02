package org.hong;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiver {
    private DatagramSocket socket;

    public UDPReceiver(int port) throws IOException {
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
}