package top.lrshuai.generator.common;

import com.jfinal.kit.Kv;
import com.jfinal.kit.PathKit;
import com.jfinal.template.Engine;
import com.jfinal.template.source.ClassPathSourceFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/***
 * jfinal enjoy 模板引擎
 *
 * @author rstyro
 */
public class JfEnjoy {

    /**
     * 根据具体魔板生成文件
     * @param templateFileName  模板文件名称
     * @param kv                渲染参数
     * @param filePath          输出目录
     * @return 
     */
    public boolean render(String templateFileName, Kv kv, StringBuilder filePath)  {
        BufferedWriter output = null;
        try {
            File file = new File(filePath.toString());
            File path = new File(file.getParent());
            if ( ! path.exists() ) {
                path.mkdirs();
            }
            output = new BufferedWriter(new FileWriter(file));
            System.out.println("templateFileName="+templateFileName);
            System.out.println("filePath="+filePath);
            Engine.use()
                    .setBaseTemplatePath("template")
                    .setToClassPathSourceFactory()
                    .getTemplate(templateFileName)
                    .render(kv, output);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally{
            try { if( output != null ) output.close(); } catch (IOException e) {}
        }
    }

}
