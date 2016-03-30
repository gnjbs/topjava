package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.stream.Collectors;

import static junit.framework.Assert.assertEquals;
import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.MealTestData.MEAL_LIST;
import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by ArturDS on 27.03.2016.
 */

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {

    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        UserMeal um = service.get(100002, USER.getId());
        MATCHER.assertEquals(MEAL_LIST.get(0), um);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(100002, USER.getId());
        service.delete(100003, ADMIN.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1, 1);
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        Collection<UserMeal> userMealCollection = service.getBetweenDates(LocalDate.of(2016, Month.MARCH, 21), LocalDate.of(2016, Month.MARCH, 21), USER.getId());
        Collection<UserMeal> userMealsFromMealList = MEAL_LIST.stream()
                .filter(um -> um.getDateTime().toLocalDate().equals(LocalDate.of(2016, Month.MARCH, 21))).collect(Collectors.toList());
        assertEquals(userMealCollection, userMealsFromMealList);
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        Collection<UserMeal> userMealCollection =
                service.getBetweenDateTimes(LocalDateTime.of(2016, Month.MARCH, 21, 9, 0), LocalDateTime.of(2016, Month.MARCH, 21, 19, 0), USER.getId());
        Collection<UserMeal> userMealsFromMealList = MEAL_LIST.stream()
                .filter(um -> um.getDateTime().isBefore(LocalDateTime.of(2016, Month.MARCH, 21, 23, 59))).collect(Collectors.toList());
        assertEquals(userMealCollection, userMealsFromMealList);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<UserMeal> all = service.getAll(USER.getId());
        MATCHER.assertCollectionEquals(MEAL_LIST, all);
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal um = new UserMeal(100002, LocalDateTime.of(2016, Month.MARCH, 21, 9, 30), "Завтрак", 500);
        service.update(um, USER_ID);
        MATCHER.assertEquals(um, service.get(100002, USER_ID));
    }

    @Test
    public void testSave() throws Exception {
        UserMeal um = new UserMeal(LocalDateTime.of(2016, Month.MARCH, 21, 0, 0), "Завтрак", 500);
        service.save(um, USER_ID);
    }
}