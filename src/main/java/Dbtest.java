import com.cx.DBACTION.EntityAction;
import com.cx.DBENTITY.Entity;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dbtest {
    public static void main(String[] args) {

        EntityAction entityAction = new EntityAction();

        //获取全部记录到List
        List<Entity> entityList = new ArrayList<Entity>();
        try {
            entityList = entityAction.queryall();
            for (Entity entity : entityList) {
                System.out.println(entity.getName() + " " + entity.getPwd());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //开启事务
            entityAction.opencommit();
            //删除一条记录
            entityAction.deletebyid(2);

            //增加一条记录
            Entity e = new Entity();
            e.setId(2);
            e.setName("chenxing8");
            e.setPwd("123456");
            entityAction.add(e);
            //修改一条记录
            entityAction.editbyid(e, 1);
            //提交事务
            entityAction.commit();
            //关闭操作
            entityAction.close();
        } catch (Exception e) {
            try {
                entityAction.rollback();
                entityAction.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
