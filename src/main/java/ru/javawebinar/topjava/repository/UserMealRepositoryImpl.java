package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ArturDS on 07.03.2016.
 */
public class UserMealRepositoryImpl implements UserMealRepository {
//TODO Добавить ID и реализовать методы
    Map<Integer,UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);
    {
       initialize();
    }

    @Override
    public void initialize() {
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510));
    }

    @Override
    public void clear() {
        repository.clear();
    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        return repository.put(userMeal.getId(), userMeal);
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
        List<UserMeal> userMeals = new ArrayList<UserMeal>(repository.values());
        //sort by time
        Collections.sort(userMeals, new Comparator<UserMeal>() {
            @Override
            public int compare(UserMeal o1, UserMeal o2) {
                return o1.getDateTime().compareTo(o2.getDateTime());
            }
        });
        return userMeals;
    }
}
