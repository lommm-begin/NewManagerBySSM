# NewManagerBySSM
---
*  将之前的javaweb项目换成了SSM框架 
* 有两种数据源可以切换，具体操作是在DataSourceConfig类的，
  `dynamicDataSource.setDefaultTargetDataSource(druidDataSourceByMysql);`默认是mysql的，另一个是oracle
* 分支的也是完整的版本，添加可以修改dao，有两个dao，一个是mybatis，一个是Spring的jdbcTemplate
  可以在Dao.properties属性文件里面通过修改默认值来制定使用哪一个，默认是mybatis
