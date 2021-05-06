package com.renovation.common.config;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @param <O> 实体类对应的模型对象，如VO、DTO、BO等
 * @param <E> 实体类对象
 *            <p>
 *            MapStruct是用于生成类型安全的bean映射类的Java注解处理器
 *            在编译过程中，MapStruct将生成该接口的实现。此实现使用纯Java的方法调用源对象和目标对象之间进行映射，并非Java反射机制
 *            <p>
 *            在实体类和模型转换的时候，只需要每个MapStruct接口都继承此接口即可，此接口包含最基本的四个转换方法
 *            每个要继承的接口上面都加一行注解，@Mapper(componentModel = "spring")，注解含义：生成的实现类上面会自动添加一个@Component注解，可以通过Spring的 @Autowired方式进行注入
 *            当然可以不使用上面的注解，那么每个接口中就必须自己定义一个成员变量，目的是让外部可以访问接口的实现，如：UserMapStruct INSTANCE = Mappers.getMapper(UserMapStruct.class);
 * @ClassName: BaseMapStructMapper
 * @Description: TODO 基础MapStruct接口
 * @Author: SAKURA
 * @Date: 2020/11/27 15:54
 */
public interface BaseMapStruct<O, E> {

    /**
     * 将模型转为实体类
     *
     * @param o 模型对象
     * @return 实体对象
     */
    @Mappings({})
    @InheritConfiguration
    E toEntity(O o);

    /**
     * 将模型集合转为实体类集合
     *
     * @param list 模型对象集合
     * @return 实体对象集合
     */
    @InheritConfiguration
    List<E> toEntityList(List<O> list);

    /**
     * 将实体转为模型
     *
     * @param e 实体对象
     * @return 模型对象
     */
    @InheritInverseConfiguration
    O toModel(E e);

    /**
     * 将实体集合转为模型集合
     *
     * @param list 实体对象集合
     * @return 模型对象集合
     */
    @InheritInverseConfiguration
    List<O> toModelList(List<E> list);
}
