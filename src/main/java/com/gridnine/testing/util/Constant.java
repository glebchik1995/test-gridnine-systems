package com.gridnine.testing.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Constant {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static final LocalDateTime currentDateTime = LocalDateTime.now();

}
