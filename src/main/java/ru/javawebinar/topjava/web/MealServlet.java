package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.repository.UserMealRepositoryImpl;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by ArturDS on 09.03.2016.
 */
public class MealServlet extends HttpServlet {

    private static final Logger LOG = getLogger(MealServlet.class);
    private UserMealRepository repository = new UserMealRepositoryImpl();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        UserMeal userMeal = new UserMeal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        LOG.info(userMeal.isNew() ? "Create {}" : "Update {}", userMeal);
        repository.save(userMeal);
        response.sendRedirect("meals");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            LOG.info("getAll");
            request.setAttribute("mealList",
                    UserMealsUtil.getFilteredMealsWithExceededByCycle(repository.getAll(),LocalTime.MIN,LocalTime.MAX, 2000));
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            repository.delete(id);
            response.sendRedirect("meals");

        } else if (action.equals("reinit")){
            LOG.info("ReInit");
            repository.clear();
            repository.initialize();
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
            LOG.info("getAll");
            request.setAttribute("mealList",
                    UserMealsUtil.getFilteredMealsWithExceededByCycle(repository.getAll(),LocalTime.MIN,LocalTime.MAX, 2000));

        }else {
            final UserMeal meal = action.equals("create") ?
                    new UserMeal(LocalDateTime.now(), "", 1000) :
                    repository.get(getId(request));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealEdit.jsp").forward(request, response);
        }
    }
    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
