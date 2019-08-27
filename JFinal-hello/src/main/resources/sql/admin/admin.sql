#sql("list")
    select
    *
    from
    admin_user
#end

#sql("getById")
    select
        *
    from
    admin_user
    WHERE
        id=#para(id)
#end