
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    private  int Port = 8805;
    private  ServerSocket serverSocket=null;
    private  Socket socket = null;
    private  String reply = null;
    public SocketServer() {

    }
    public void run() {
        try {
            serverSocket = new ServerSocket(Port);
            System.out.println("接收数据服务端开始监听：");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            while (true) {
                try {
                    socket = serverSocket.accept();
                    InputStream is = socket.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    while ((reply = br.readLine()) != null) {
                        System.out.println(reply);
                        KafkaProducer kafkaProducer=new KafkaProducer(reply);
                        kafkaProducer.produce();
                    }
                    br.close();
                    is.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
      /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                KafkaComsumer.comsumer();
            }
        }).start();
      */
        SocketServer socketServer=new SocketServer();
        socketServer.run();
    }
}

