package com.orora.report.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public abstract class AbstractProduct {

    private String id;

    private LocalDate date;

}
