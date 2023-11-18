package freek.paintball_v1.Repo;

import freek.paintball_v1.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrdersRepo extends JpaRepository <Orders, Integer> {
    @Query(value = "select * from _orders where order_date = ?1 and playground_id = ?2", nativeQuery = true)
    Orders findByDateAndPlaygroundId(String orderDate, int pg_id);

    @Query(value = "select user_id from _orders where id = ?1",nativeQuery = true)
    int getUserIdById(int id);
}
