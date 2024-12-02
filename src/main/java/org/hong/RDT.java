package org.hong;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class RDT {
    public static final int RDT_HEADER_LEN = 8; // 假设头部长度为8字节
    public static final int RDT_DATA_LEN = 1024; // 数据长度为1024字节
    public static final int RDT_PKT_LEN = RDT_HEADER_LEN + RDT_DATA_LEN; // 总包长度

    // 封装RDT数据包
    public static byte[] packRDTPacket(byte[] data, int seqNum, int flag) {
        byte[] packet = new byte[RDT_PKT_LEN];
        // 填充序列号和标志位
        packet[0] = (byte) ((seqNum >> 24) & 0xFF);
        packet[1] = (byte) ((seqNum >> 16) & 0xFF);
        packet[2] = (byte) ((seqNum >> 8) & 0xFF);
        packet[3] = (byte) (seqNum & 0xFF);
        packet[4] = (byte) ((flag >> 24) & 0xFF);
        packet[5] = (byte) ((flag >> 16) & 0xFF);
        packet[6] = (byte) ((flag >> 8) & 0xFF);
        packet[7] = (byte) (flag & 0xFF);
        // 复制数据
        System.arraycopy(data, 0, packet, RDT_HEADER_LEN, data.length);
        return packet;
    }

    // 解封装RDT数据包
    public static int unpackRDTPacket(byte[] packet, byte[] dataBuf, int[] seqNum, int[] flag) {
        // 读取序列号和标志位
        int seq = ((packet[0] & 0xFF) << 24) | ((packet[1] & 0xFF) << 16) | ((packet[2] & 0xFF) << 8) | (packet[3] & 0xFF);
        int flg = ((packet[4] & 0xFF) << 24) | ((packet[5] & 0xFF) << 16) | ((packet[6] & 0xFF) << 8) | (packet[7] & 0xFF);
        seqNum[0] = seq;
        flag[0] = flg;
        // 复制数据
        System.arraycopy(packet, RDT_HEADER_LEN, dataBuf, 0, packet.length - RDT_HEADER_LEN);
        return packet.length - RDT_HEADER_LEN;
    }
}
