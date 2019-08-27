package top.lrshuai.generator;

import com.alibaba.fastjson.JSON;
import com.jfinal.plugin.activerecord.generator.TableMeta;
import top.lrshuai.generator.common.JfGenerator;

/**
 * 自定义代码生成器
 * @author rstyro
 */
public class CodeGenerator {
    public static void main(String[] args) {
        JfGenerator jfGenerator = new JfGenerator();
        TableMeta t_test = jfGenerator.getTableMeta("act_acticle");
        System.out.println(JSON.toJSONString(t_test));
        jfGenerator.allRender("Test","act_acticle");
    }
}
