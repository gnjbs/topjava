package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);
    public static final List<UserMeal> MEAL_LIST = Arrays.asList(
            new UserMeal(LocalDateTime.of(2016, Month.MARCH, 21, 0, 0), "Завтрак", 500),
            new UserMeal(LocalDateTime.of(2016, Month.MARCH, 21, 0, 0), "Обед", 1000),
            new UserMeal(LocalDateTime.of(2016, Month.MARCH, 21, 0, 0), "Ужин", 500),
            new UserMeal(LocalDateTime.of(2016, Month.MARCH, 22, 0, 0), "Завтрак", 1000),
            new UserMeal(LocalDateTime.of(2016, Month.MARCH, 22, 0, 0), "Обед", 500),
            new UserMeal(LocalDateTime.of(2016, Month.MARCH, 22, 0, 0), "Ужин", 510)
    );
}
