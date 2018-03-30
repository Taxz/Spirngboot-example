package edu.example.study.repository;

import edu.example.study.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * Created by Taxz on 2018/3/30.
 */
@CacheConfig(cacheNames = "user")
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    /*注意：
        定义方法遵循spring-data-jpa规范
     */

    /**
     * @Cacheable 应用到读取数据的方法上，先从缓存中读取，如果没有再从DB获取数据，然后把数据添加到缓存中
     * unless 表示条件表达式成立的话不放入缓存
     * @param id
     * @return
     */
    @Cacheable(key = "#p0",unless = "#result eq null")
    User findUserById(int id);

    /**
     * @CachePut 应用到写数据的方法上，如新增/修改方法，调用方法时会自动把相应的数据放入缓存
     * @param user
     * @return
     */
    @CachePut(key = "#p0.id",unless = "#user eq null")
    User save(User user);

    /**
     * @CacheEvict 应用到删除数据的方法上，调用方法时会从缓存中删除对应key的数据
     * @param id
     * @return
     */
    @CacheEvict(key = "#p0")
    int deleteById(int id);
}
