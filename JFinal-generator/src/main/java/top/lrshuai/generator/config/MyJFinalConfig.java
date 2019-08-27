package top.lrshuai.generator.config;

import com.jfinal.config.*;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.jfinal.template.source.ClassPathSourceFactory;

public class MyJFinalConfig extends JFinalConfig {

    public static Prop prop;

    /**
     * PropKit.useFirstFound(...) 使用参数中从左到右最先被找到的配置文件
     * 从左到右依次去找配置，找到则立即加载并立即返回，后续配置将被忽略
     */
    static void loadDbConfig() {
        if (prop == null) {
            prop = PropKit.useFirstFound("db_config.properties", "db_config.properties-dev.properties");
        }
    }

    /**
     * 此方法用来配置JFinal常量值，如开发模式常量devMode的配置，如下代码配置了JFinal运行在开发模式
     *
     * @param constants
     */
    @Override
    public void configConstant(Constants constants) {
        //在开发模式下，JFinal会对每次请求输出报告，如输出本次请求的URL、Controller、Method以及请求所携带的参数
        constants.setDevMode(true);
        // 开启对 jfinal web 项目组件 Controller、Interceptor、Validator 的注入
        constants.setInjectDependency(true);

        // 开启对超类的注入。不开启时可以在超类中通过 Aop.get(...) 进行注入
        constants.setInjectSuperClass(true);
    }

    @Override
    public void configRoute(Routes routes) {
    }

    @Override
    public void configEngine(Engine engine) {
        engine.setDevMode(true);
        engine.setBaseTemplatePath("template");
        engine.setToClassPathSourceFactory();
    }

    @Override
    public void configPlugin(Plugins plugins) {
        DruidPlugin druidPlugin = createDruidPlugin();
        //添加 DruidPlugin
        plugins.add(druidPlugin);
        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
        //配置MySQL方言
        activeRecordPlugin.setDialect(new MysqlDialect());
        activeRecordPlugin.getEngine()
                .setSourceFactory(new ClassPathSourceFactory());
        activeRecordPlugin.addSqlTemplate("sql/index.sql");
        //是否显示执行的SQL
        activeRecordPlugin.setShowSql(true);
        // 添加Mapper
        //添加 ActiveRecordPlugin
        plugins.add(activeRecordPlugin);

    }


    public static DruidPlugin createDruidPlugin() {
        loadDbConfig();
        String url = prop.get("db.url").trim();
        String username = prop.get("db.username").trim();
        String password = prop.get("db.password").trim();
        DruidPlugin druidPlugin = new DruidPlugin(url,username,password);
        druidPlugin.setFilters("stat,wall,log4j");
        //最大连接池数量，默认为8
        druidPlugin.setMaxActive(20);
        //最小连接池数量
        druidPlugin.setMinIdle(1);
        //初始化时建立物理连接的个数，默认为0
        druidPlugin.setInitialSize(1);
        //获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。
        druidPlugin.setMaxWait(60000);
        //如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。
        druidPlugin.setTimeBetweenEvictionRunsMillis(60000);
        //连接保持空闲而不被驱逐的最小时间
        druidPlugin.setMinEvictableIdleTimeMillis(300000);
        //建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
        druidPlugin.setTestWhileIdle(true);
        //申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。默认为true
        druidPlugin.setTestOnBorrow(false);
        //归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。默认为true
        druidPlugin.setTestOnReturn(false);
        druidPlugin.setValidationQuery("SELECT 1 FROM DUAL");
        druidPlugin.start();
        return druidPlugin;
    }



    @Override
    public void configInterceptor(Interceptors interceptors) {
        //这里可以配置全局拦截器
    }

    @Override
    public void configHandler(Handlers handlers) {
    }

    // 系统启动完成后回调
    public void onStart() {
    }

    // 系统关闭之前回调
    public void onStop() {
    }
}
