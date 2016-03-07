package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ArturDS on 07.03.2016.
 */
public class UserMealRepositoryImpl implements UserMealRepository {
//TODO Добавить ID и реализовать методы
    Map<Integer,UserMeal> repository = new ConcurrentHashMap<>();


    @Override
    public UserMeal save(UserMeal userMeal) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public UserMeal get(int id) {
        return null;
    }

    @Override
    public Collection<UserMeal> getAll() {
        return null;
    }
}
