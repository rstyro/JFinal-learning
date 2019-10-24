package top.lrshuai.generator;

import top.lrshuai.generator.common.JfGenerator;

/**
 * 自定义代码生成器
 * @author rstyro
 */
public class CodeGenerator {
    public static void main(String[] args) {
        JfGenerator jfGenerator = new JfGenerator();
//        jfGenerator.allRender("Contract","contract");
        String className = "UserTradeStatistics";
        jfGenerator
//                .controller(className)
                .htmlRender(className,"user_trade_statistics");
    }
}
