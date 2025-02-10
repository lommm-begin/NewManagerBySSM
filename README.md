# NewManagerBySSM
---
*  将之前的javaweb项目换成了SSM框架
*  前端模版使用的是`Thymeleaf`，所以springmvc解析器也是`Thymeleaf`视图解析器
* 有两种数据源可以切换，具体操作是在`DataSourceConfig`类的
  `dynamicDataSource.setDefaultTargetDataSource(druidDataSourceByMysql);`默认是`mysql`的，另一个是`oracle`
* 分支的也是完整的版本，添加可以修改dao，有两个dao，一个是`mybatis`，一个是Spring的`jdbcTemplate`
  可以在`Dao.properties`属性文件里面通过修改默认值来制定使用哪一个，默认是`mybatis`
* 前端不怎么漂亮（改了一天，然后撤回）
* 后续可能会在前端添加分页，但是pom文件已经加进了分页的依赖`Pagehelper`
