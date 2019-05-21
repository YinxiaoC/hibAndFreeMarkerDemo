import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: hibAndFreeMarkerDemo
 * @BelongsPackage: PACKAGE_NAME
 * @Author: yinxiucahun
 * @CreateTime: 2019-05-21 11:09
 * @Description: 测试freemarker功能
 */

public class testFreemarker01 {

    //使用步骤：
    //
    //第一步：创建一个 Configuration 对象，直接 new 一个对象。构造方法的参数就是 freemarker的版本号。
    //
    //第二步：设置模板文件所在的路径。
    //
    //第三步：设置模板文件使用的字符集。一般就是 utf-8.
    //
    //第四步：加载一个模板，创建一个模板对象。
    //
    //第五步：创建一个模板使用的数据集，可以是 pojo 也可以是 map。一般是 Map。
    //
    //第六步：创建一个 Writer 对象，一般创建一FileWriter 对象，指定生成的文件名。
    //
    //第七步：调用模板对象的 process 方法输出文件。
    //
    //第八步：关闭流
    @Test
    public void test2() throws IOException, TemplateException {
        //创建一个Configuration对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //设置模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(new File("E:/IDEA WorkSpace/hibAndFreeMarkerDemo/src/templates"));
        //设置模板文件所使用的字符集，一般是utf-8
        configuration.setDefaultEncoding("utf-8");
        //加载一个模板，创建一个模板对象
        Template template = configuration.getTemplate("test.ftl");
        //创建一个模板使用的数据集，可以是pojo也可以是map，一般是map
        Map dataMap = new HashMap<String, String>();
        dataMap.put("name", "xiao");
        dataMap.put("message", "123123");
        //创建一个writer对象，一般创建一个FileWriter对象，指定生成的文件名。
        Writer writer = new FileWriter(new File("D:\\hello.html"));
        //调用模板对象的process方法输出文件
        template.process(dataMap, writer);
        //关闭流
        writer.close();
    }

}
