import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.io.*;
import java.util.Properties;

public class KafkaProducer {
    //具体实现看以后提供的数据格式
    private static kafka.javaapi.producer.Producer<String, String> producer = null;
    private  String message=null;

    public KafkaProducer(String message) {
        this.message=message;
    }
    public void produce(){
        Properties props = new Properties();
        // 此处配置的是kafka-broker的端口
        //虚拟机
        // props.put("metadata.broker.list", "10.84.3.14:9092");
        //真集群
        props.put("metadata.broker.list", "10.84.3.21:9092,10.84.3.22:9092,10.84.3.24:9092");
        // 配置value的序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        // 配置key的序列化类
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        //是否收到的响应
        //0表示producer无需等待leader的确认，1代表需要leader确认写入它的本地log并立即确认，-1代表所有的备份都完成后确认。 仅仅for sync
        props.put("request.required.acks", "0");
        //指定消息发送是同步还是异步。异步asyc成批发送用kafka.producer.AyncProducer， 同步sync用kafka.producer.SyncProducer
        props.put("producer.type", "async");
        //在producer queue的缓存的数据最大时间，仅仅for asyc
        props.put("queue.buffering.max.ms", "10000");
        //producer 缓存的消息的最大数量，仅仅for asyc
        props.put("queue.buffering.max.messages", "1000");
        //0当queue满时丢掉，负值是queue满时block,正值是queue满时block相应的时间，仅仅for async
        props.put("queue.enqueue.timeout.ms", "-1");
        //一批消息的数量，仅仅for asyc
        props.put("batch.num.messages", "500");
        //在设置了压缩的情况下，可以指定特定的topic压缩，未指定则全部压缩
        props.put("compressed.topics", "shanxitext");
//        props.put("num.partitions",4);                       //写在broker配置里面
        producer = new kafka.javaapi.producer.Producer<String, String>(new ProducerConfig(props));

    /*    BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(System.out));
        System.out.println("请输入任意字母并按回车键：");
        String result =null;
        try {
            while ((result = bufr.readLine())!=null){
                if("exit".equals(result))            //判断输入over，就结束循环
                    break;
                bufw.newLine();                    //换行
                bufw.flush();                      //刷新
                producer.send(new KeyedMessage<String, String>("shanxitest", result));
            }
            bufw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        try {
              producer.send(new KeyedMessage<String, String>("shanxitest", message));
            } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

