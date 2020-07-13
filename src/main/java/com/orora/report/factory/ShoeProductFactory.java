package com.orora.report.factory;

import com.orora.report.model.AbstractProduct;
import com.orora.report.model.Shoe;
import com.orora.report.model.ShoeBox;
import com.orora.report.model.ShoeProduct;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ShoeProductFactory implements ProductFactory<ShoeProduct> {

    private final int monthProduction;

    @Getter
    private @NonNull YearMonth yearMonth;

    @Autowired
    private ShoeFactory shoeFactory;

    @Autowired
    private ShoeBoxFactory shoeBoxFactory;

    private List<AbstractProduct> unusedProducts = new ArrayList<>();

    @Override
    public void reset() {
        shoeFactory.reset();
        shoeBoxFactory.reset();
    }

    @Override
    public List<ShoeProduct> getDayProducts(int day) {
        List<ShoeProduct> shoeProducts = new ArrayList<>();
        int daysOfMonth = yearMonth.lengthOfMonth();
        if (day > daysOfMonth) {
            return shoeProducts;
        }

        LocalDate date = yearMonth.atDay(day);
        int remains = monthProduction % daysOfMonth;
        int dayProduction = monthProduction / daysOfMonth;
        if (day <= remains) {
            dayProduction++;
        }

        List<Shoe> shoes = shoeFactory.getDayProducts(day);
        List<ShoeBox> shoeBoxes = shoeBoxFactory.getDayProducts(day);
        for (int i = 0; i < dayProduction; i++) {
            final Shoe shoe = shoes.get(i);
            final ShoeBox shoeBox = shoeBoxes.get(i);
            shoeProducts.add(new ShoeProduct(generateId(day, shoe, shoeBox), date));
        }
        if (shoes.size() > dayProduction) {
            unusedProducts.addAll(shoes.subList(dayProduction, shoes.size()));
        }
        if (shoeBoxes.size() > dayProduction) {
            unusedProducts.addAll(shoeBoxes.subList(dayProduction, shoeBoxes.size()));
        }
        return shoeProducts;
    }

    private String generateId(int day, Shoe shoe, ShoeBox shoeBox) {
        if (day % 2 == 0) {
            return shoe.getId().substring(1, 7) + "-" + shoeBox.getId().substring(1, 7);
        } else {
            return shoe.getId().substring(0, 6) + "-" + shoeBox.getId().substring(0, 6);
        }
    }

    public synchronized List<AbstractProduct> getUnusedProducts() {
        unusedProducts.clear();
        getMonthProducts();

        List<AbstractProduct> remainProducts = new ArrayList<>(unusedProducts);
        unusedProducts.clear();
        return remainProducts;
    }

}
