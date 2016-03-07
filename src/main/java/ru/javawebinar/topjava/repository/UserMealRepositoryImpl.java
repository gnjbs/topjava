package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.*;
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
        repository.remove(id);
    }

    @Override
    public UserMeal get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<UserMeal> getAll() {
        List<UserMeal> userMealList = new ArrayList<UserMeal>(repository.values());
        //sort by time
        Collections.sort(userMealList, new Comparator<UserMeal>() {
            @Override
            public int compare(UserMeal o1, UserMeal o2) {
                return o1.getDateTime().compareTo(o2.getDateTime());
            }
        });
        return userMealList;
    }
}
