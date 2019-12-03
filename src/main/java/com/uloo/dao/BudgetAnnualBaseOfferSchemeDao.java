package com.uloo.dao;



import com.uloo.annotations.Select;
import com.uloo.entity.BudgetAnnualBaseOfferScheme;

import java.util.List;

public interface BudgetAnnualBaseOfferSchemeDao {


    public List<BudgetAnnualBaseOfferScheme> getAllUserByMybatis() throws Exception;

    @Select(value="select * from budget_annual_base_offer_scheme where id=32")
    public  List<BudgetAnnualBaseOfferScheme>  findOne();
}
