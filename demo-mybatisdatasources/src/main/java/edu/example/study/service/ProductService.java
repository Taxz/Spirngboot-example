package edu.example.study.service;

import edu.example.study.entity.Product;
import edu.example.study.mapper.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

/**
 * Created by Taxz on 2018/4/18.
 */
@Service
public class ProductService {
    @Autowired
    ProductDao productDao;

    public Product select(long id) throws Exception {
        Product product = productDao.select(id);
        if (product == null) {
            throw new Exception("product 不存在id"+id);
        }
        return product;
    }

    @Transactional(rollbackFor = DataAccessException.class)
    public Product update(Product product) throws Exception {
        int num = productDao.update(product);
        if (num != 1) {
            throw new Exception(product.getId() + "更新失败");
        }
        return product;
    }

    @Transactional(rollbackFor = DataAccessException.class)
    public boolean add(Product product) throws Exception {
        int i = productDao.insert(product);
        if (i != 1) {
            throw new Exception(product.getId() + "add fail");
        }
        return true;
    }

    @Transactional(rollbackFor = DataAccessException.class)
    public boolean delete(Long id) throws Exception {
        int num = productDao.delete(id);
        if (num != 1) {
            throw new Exception(id + "删除失败");
        }
        return true;
    }

    public List<Product> getAll(){
        List<Product> products = productDao.getAllProduct();
        return products;
    }
}
