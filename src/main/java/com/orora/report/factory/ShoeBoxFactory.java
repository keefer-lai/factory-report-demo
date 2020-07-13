package com.orora.report.factory;

import com.orora.report.model.ShoeBox;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.YearMonth;

public class ShoeBoxFactory extends AbstractProductFactory<ShoeBox> {

    public ShoeBoxFactory(int monthProduction, @NonNull YearMonth yearMonth) {
        super(monthProduction, yearMonth);
    }

    @Override
    protected ShoeBox newProduct(String id, LocalDate date) {
        return new ShoeBox(id, date);
    }

    @Override
    protected String getProductCode() {
        return "SHBX";
    }

}
