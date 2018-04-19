package edu.example.study.mapper;

import edu.example.study.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Taxz on 2018/4/18.
 */
@Mapper
@Repository
public interface ProductDao {
    Product select( long id);

    Integer update(Product product);

    Integer insert(Product product);

    Integer delete(long id);

    List<Product> getAllProduct();
}
