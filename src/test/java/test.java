import com.uloo.parsing.Resources;
import com.uloo.session.SqlSession;
import com.uloo.session.SqlSessionFactory;
import com.uloo.session.SqlSessionFactoryBuilder;
import com.uloo.dao.BudgetAnnualBaseOfferSchemeDao;
import com.uloo.entity.BudgetAnnualBaseOfferScheme;
import sun.misc.ProxyGenerator;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

public class test {
    public static void main(String[] args) throws Exception{

        //加载配置文件
        InputStream in = Resources.getResourceAsStreamer("sqlMapConfig.xml");

        //创建sqlsessin
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        //建造者模式创建创建工厂
        SqlSessionFactory factory = builder.build(in);
        //创建对象
        SqlSession session = factory.openSession();

        //sqlsseion创建对象
        BudgetAnnualBaseOfferSchemeDao budgetAnnualBaseOfferSchemeDao = session.getMapper(BudgetAnnualBaseOfferSchemeDao.class);

        //执行方法z
        List<BudgetAnnualBaseOfferScheme> budgetAnnualBaseOfferSchemes = budgetAnnualBaseOfferSchemeDao.getAllUserByMybatis();

        Class cls = budgetAnnualBaseOfferSchemes.getClass();
        System.out.println(cls.getName());
        byte[] bts = ProxyGenerator.generateProxyClass("$GameProxy", new Class[]{BudgetAnnualBaseOfferSchemeDao.class});
        FileOutputStream fos = new FileOutputStream(new File("D:/$GameProxy.class"));
        fos.write(bts);
        fos.flush();
        fos.close();


        /*for (BudgetAnnualBaseOfferScheme budgetAnnualBaseOfferScheme:budgetAnnualBaseOfferSchemes){
            System.out.println(budgetAnnualBaseOfferScheme);
        }
        List<BudgetAnnualBaseOfferScheme> one = budgetAnnualBaseOfferSchemeDao.findOne();
        System.out.println(one+"======");*/
        session.close();
        in.close();
    }
}