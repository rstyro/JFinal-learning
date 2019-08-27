package top.lrshuai.jfinal.service;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import top.lrshuai.jfinal.model.Acticle;

import java.util.List;


public class ActicleService {

    public List<Acticle> list(){
        List<Acticle> all = Acticle.dao.findAll();
        return all;
    }

    public Acticle getById(String id){
        Acticle acticle = Acticle.dao.template("act.getById", Kv.by("id", id)).findFirst();

        return acticle;
    }

    public List<Acticle> list2(){
        List<Acticle> all = Acticle.dao.template("act.list").find();
        return all;
    }

}
