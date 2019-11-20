package ordering.repository.jdbc;

import ordering.domain.Category;
import ordering.domain.Dish;
import ordering.domain.PaginationSupport;
import ordering.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜品内容资源库接口的jdbc实现类
 *
 * @author neilly
 * @version 1.0
 * @since 2019/11/19 11:06
 */
@Repository
public class JdbcDishRepository implements DishRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcDishRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long count() {
        return jdbc.queryForObject("select count(dish_id) from Dish", long.class);
    }

    @Override
    public Dish findById(String dish_id) {
        try {
            return jdbc.queryForObject(SELECT_FROM_DISH + " where dish_id=?", new DishRowMapper(), dish_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Dish> getAll() {
        return jdbc.query(SELECT_FROM_DISH, new DishRowMapper());
    }

    @Override
    public PaginationSupport<Dish> findByPage(int pageNo, int PageSize) {
        int totalCount = (int) count();
        int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, PageSize);
        if (totalCount < 1)
            return new PaginationSupport<>(new ArrayList<>(0), 0);

        List<Dish> items = jdbc.query(SELECT_FROM_DISH_PAGE, new DishRowMapper(), PageSize, startIndex);
        return new PaginationSupport<>(items, totalCount, PageSize, startIndex);
    }

    @Override
    public void deleteDish(String dish_id) {

    }

    @Override
    public Dish save(Dish dish) {
        return null;
    }

    private static final class DishRowMapper implements RowMapper<Dish> {

        @Override
        public Dish mapRow(ResultSet rs, int rowNum) throws SQLException {
            String dish_id = rs.getString("dish_id");
            String dish_name = rs.getString("dish_name");
            String picture_url = rs.getString("picture_url");
            float price = rs.getFloat("price");
            String description = rs.getString("description");
            String category_id = rs.getString("category_id");
            String category_name = rs.getString("category_name");
            Category category = new Category(category_id, category_name);
            return new Dish(dish_id, dish_name, category, picture_url, price, description);
        }
    }

    private static final String SELECT_FROM_DISH = "SELECT v.dish_id, v.dish_name, v.picture_url, v.price, v.description, c.category_id, c.category_name FROM (SELECT * FROM dish d NATURAL JOIN dish_category dc) v NATURAL JOIN category c";
    private static final String SELECT_FROM_DISH_PAGE = SELECT_FROM_DISH + " order by v.dish_id limit ? offset ?";
}
