package org.hong;
import java.net.*;
import java.io.*;

public class Packet {
    // 类中必须囊括序号、以及检验和
    Integer seqNo;
    Integer checksum;
    // 发送的内容
    public String content;

    // 初始化的packet 的seqNo=1,
    public Packet() {
        seqNo = 1;
    }

    // 这个方法教给发送者调用，可以将读取的信息封装到 packet当中
    public void createPacket(String pContent,int seqNo) {
        content = pContent;
        // 为内容设置校验和
        checksum = generateChecksum(content);
        // 在rdt3.0中，序号只可能是0或者1，由发送者控制序号的变化
        this.seqNo = seqNo;
    }
    // 帮助发送者生成统一格式的信息
    public String generateMessage() {
        return seqNo+ " " + checksum + " " + content;
    }

    // 生成检验和的函数，原本算法就是将前面3个16位相加，但是这里没有源端口和目的端口
    // 因此，我们对发送的信息做一个处理，将其转换为ascii码作为检验和
    public Integer generateChecksum(String s) {
        int asciiInt;
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            asciiInt = (int) s.charAt(i);
            sum = sum + asciiInt;
        }
        return sum;
    }

    // 这个方法是帮助接收者解析从套接字获取的data
    public void parseMessage(String pcontent) {
        String[] splited = pcontent.split("\\s+");
        for (int i = 0; i < splited.length; i++) {
            // 依次赋值给seqNo 和 checksum
            seqNo = Integer.parseInt(splited[0]);
            checksum = Integer.parseInt(splited[1]);
            // 最后一个元素才是 要发送的内容
            content = splited[2];
        }
    }

    // 模拟包出错，可以将checksum+1
    public void corruptChecksum() {
        checksum = checksum + 1;
    }

    // 这个方法是帮助接收者判断包裹的信息是否出错的，也就是将计算得到的checksum和发送来的checksum作比较
    public int validateMessage() {
        Integer newChecksum = generateChecksum(content);
        if (newChecksum.equals(checksum))
            return  seqNo;
            //如果两者不相等，说明包损坏了，给发送者发送相反的ACK，这样发送者就知道出错了，需要重发
        else {
            seqNo = 1-seqNo;
            return seqNo;
        }
    }
}