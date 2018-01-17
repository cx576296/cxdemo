import com.cx.DBENTITY.Entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.MessageFormat;


public class SerializeEntity {

    public static void main(String[] args) throws Exception {
        Serialize();//序列化对象
        Entity p = Deserialize();//反序列对象
        System.out.println(MessageFormat.format("id={0},name={1},pwd={2}",
                p.getId(), p.getName(), p.getPwd()));
    }

    /**
     * MethodName: Serialize
     * Description: 序列化对象
     */
    private static void Serialize() throws FileNotFoundException,
            IOException {
        Entity person = new Entity();
        person.setId(1);
        person.setName("cx");
        person.setPwd("1223333");
        // ObjectOutputStream 对象输出流，将Person对象存储到D盘的文件中，完成对Person对象的序列化操作
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File("D:/2.txt")));
        oo.writeObject(person);
        System.out.println("对象序列化成功！");
        oo.close();
    }

    /**
     * MethodName: DeserializePerson
     * Description: 反序列对象
     */
    private static Entity Deserialize() throws Exception, IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                new File("D:/2.txt")));
        Entity person = (Entity) ois.readObject();
        System.out.println("对象反序列化成功！");
        return person;
    }

}