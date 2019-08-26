package top.lrshuai.jfinal.controller;

import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import top.lrshuai.jfinal.interceptor.HelloInteceptor;
import top.lrshuai.jfinal.model.Acticle;
import top.lrshuai.jfinal.service.ActicleService;

import java.util.List;

public class ActicleController extends Controller {

    @Inject
    private ActicleService acticleService;

    public void index(){
        setAttr("list",acticleService.list());
        render("index.html");
    }


    @Before({HelloInteceptor.class})
    public void list() throws Exception{
        System.out.println("list");
        List<Acticle> list = acticleService.list();
        System.out.println("json-list="+JSON.toJSONString(list));
        System.out.println(acticleService);
        renderJson(list);
        return;
    }

    public void list2() throws Exception{
        System.out.println("list");
        List<Acticle> list = acticleService.list2();
        System.out.println("json-list="+JSON.toJSONString(list));
        renderJson(list);
        return;
    }

    public void getById() throws Exception{
        System.out.println("getById="+getPara("id"));
        renderJson(acticleService.getById(getPara("id")));
        return;
    }


}
