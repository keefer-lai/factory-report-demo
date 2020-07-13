package com.orora.report.factory;

import com.orora.report.model.AbstractProduct;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public interface ProductFactory<T extends AbstractProduct> {

    default List<T> getMonthProducts() {
        List<T> products = new ArrayList<>();

        for (int day = 1; day <= getYearMonth().lengthOfMonth(); day++) {
            products.addAll(getDayProducts(day));
        }

        reset();
        return products;
    }

    void reset();

    List<T> getDayProducts(int day);

    YearMonth getYearMonth();

}
