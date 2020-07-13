package com.orora.report.factory;

import com.orora.report.model.AbstractProduct;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractProductFactory<T extends AbstractProduct> implements ProductFactory<T> {

    private final int monthProduction;

    @Getter
    private @NonNull YearMonth yearMonth;

    private int serialNumber;

    @Override
    public void reset() {
        serialNumber = 0;
    }

    public List<T> getDayProducts(int day) {
        List<T> products = new ArrayList<>();
        int daysOfMonth = yearMonth.lengthOfMonth();
        if (day > daysOfMonth) {
            return products;
        }

        LocalDate date = yearMonth.atDay(day);
        int remains = monthProduction % daysOfMonth;
        int dayProduction = monthProduction / daysOfMonth;
        if (day <= remains) {
            dayProduction++;
        }

        for (int i = 0; i < dayProduction; i++) {
            products.add(newProduct(generateId(day), date));
        }
        return products;
    }

    abstract protected T newProduct(String id, LocalDate date);

    private String generateId(int day) {
        serialNumber++;

        return String.format(day % 2 == 0 ? "%04d%s" : "%03d%s", serialNumber, getProductCode());
    }

    abstract protected String getProductCode();

}
