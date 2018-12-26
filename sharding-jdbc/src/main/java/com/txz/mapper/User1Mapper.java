package com.txz.mapper;

/**
 * Created by Taxz on 2018/12/25.
 */
import com.txz.entity.UserEntity;

import java.util.List;

/**
 * Created by Kane on 2018/1/17.
 */
public interface User1Mapper {

    List<UserEntity> getAll();

    void update(UserEntity user);

}