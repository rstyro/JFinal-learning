package top.lrshuai.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class HelloInteceptor implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        try {
            System.out.println("Before 拦截器 拦截");
            invocation.invoke();
            System.out.println("After 拦截器 结束");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
