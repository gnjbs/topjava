package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;

/**
 * Created by ArturDS on 07.03.2016.
 */
public interface UserMealRepository {
    UserMeal save(UserMeal userMeal);

    void delete(int id);

    UserMeal get(int id);

    Collection<UserMeal> getAll();
}
