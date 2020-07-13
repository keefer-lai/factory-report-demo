package com.orora.report.factory;

import com.orora.report.model.Shoe;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.YearMonth;

public class ShoeFactory extends AbstractProductFactory<Shoe> {

    public ShoeFactory(int monthProduction, @NonNull YearMonth yearMonth) {
        super(monthProduction, yearMonth);
    }

    @Override
    protected Shoe newProduct(String id, LocalDate date) {
        return new Shoe(id, date);
    }

    @Override
    protected String getProductCode() {
        return "SHOE";
    }

}
