package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.List;

/**
 * Created by ArturDS on 07.03.2016.
 */
public interface UserMealRepository {
    UserMeal save(UserMeal userMeal);

    void initialize();

    void delete(int id);

    UserMeal get(int id);

    List<UserMeal> getAll();

    void clear();
}
