#sql("list")
    select
    *
    from
    act_acticle
#end

#sql("getById")
    select
        *
    from
    act_acticle
    WHERE
        id=#para(id)
#end