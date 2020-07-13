package com.orora.report.controller;

import com.orora.report.factory.ShoeBoxFactory;
import com.orora.report.factory.ShoeFactory;
import com.orora.report.factory.ShoeProductFactory;
import com.orora.report.model.AbstractProduct;
import com.orora.report.model.Shoe;
import com.orora.report.model.ShoeBox;
import com.orora.report.model.ShoeProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportController {

    @Autowired
    private ShoeFactory shoeFactory;

    @Autowired
    private ShoeBoxFactory shoeBoxFactory;

    @Autowired
    private ShoeProductFactory shoeProductFactory;

    @GetMapping(value = "/shoes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Shoe> shoes() {
        return shoeFactory.getMonthProducts();
    }

    @GetMapping(value = "/shoeBoxes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ShoeBox> shoeBoxes() {
        return shoeBoxFactory.getMonthProducts();
    }

    @GetMapping(value = "/shoeProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ShoeProduct> shoeProducts() {
        return shoeProductFactory.getMonthProducts();
    }

    @GetMapping(value = "/unusedProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AbstractProduct> unusedProducts() {
        return shoeProductFactory.getUnusedProducts();
    }

}
