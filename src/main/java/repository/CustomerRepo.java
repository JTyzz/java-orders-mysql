package repository;

import model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByCustname(String name);
    Customer findByCustcode(Long custCode);

    @Query(value = "SELECT Customers.custCode, Customers.custname, Orders.ordnum\n" +
            "FROM Customers\n" +
            "JOIN Orders ON Orders.custCode=Customers.custCode\n", nativeQuery = true)
    List<Object[]> ordersAndCustomers();

    @Query(value = "SELECT Customers.custCode, Customers.custname, Orders.ordnum\n" +
            "FROM Customers\n" +
            "JOIN Orders ON Orders.custCode=Customers.custCode\n" +
            "WHERE Customers.custname = :name", nativeQuery = true)
    List<Object[]> ordersAndCustomersByName(@Param("name") String name);


    @Query(value = "SELECT Customers.custcode, Customers.custname, Orders.ordnum\n" +
            "FROM Customers\n" +
            "JOIN Orders ON Orders.custCode=Customers.custCode\n" +
            "WHERE Customers.custcode = :code", nativeQuery = true)
    List<Object[]> ordersAndCustomersByCode(@Param("code") Long code);
}
