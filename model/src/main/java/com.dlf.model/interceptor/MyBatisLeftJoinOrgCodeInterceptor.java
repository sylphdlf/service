//package com.dlf.com.dlf.model.interceptor;
//
//import com.dlf.common.utils.comm.ReflectUtil;
//import com.dlf.common.utils.user.UserUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.ibatis.cache.CacheKey;
//import org.apache.ibatis.executor.Executor;
//import org.apache.ibatis.executor.statement.RoutingStatementHandler;
//import org.apache.ibatis.executor.statement.StatementHandler;
//import org.apache.ibatis.mapping.BoundSql;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.mapping.SqlSource;
//import org.apache.ibatis.plugin.*;
//import org.apache.ibatis.reflection.DefaultReflectorFactory;
//import org.apache.ibatis.reflection.MetaObject;
//import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
//import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
//import org.apache.ibatis.session.ResultHandler;
//import org.apache.ibatis.session.RowBounds;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Properties;
//@Intercepts(
//        {
////                @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class}),
////                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
//                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
//                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
//        }
//    )
////@Component
//public class MyBatisLeftJoinOrgCodeInterceptor implements Interceptor {
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
////        //是通过Interceptor的plugin方法进行包裹的，所以我们这里拦截到的目标对象肯定是RoutingStatementHandler对象。
////        RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
////        //通过反射获取到当前RoutingStatementHandler对象的delegate属性
////        StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
////        //获取到当前StatementHandler的 boundSql，这里不管是调用handler.getBoundSql()还是直接调用delegate.getBoundSql()结果是一样的，因为之前已经说过了
////        //RoutingStatementHandler实现的所有StatementHandler接口方法里面都是调用的delegate对应的方法。
////        BoundSql boundSql = delegate.getBoundSql();
////        //拿到当前绑定Sql的参数对象，就是我们在调用对应的Mapper映射语句时所传入的参数对象
////        Object obj = boundSql.getParameterObject();
////        String sql = boundSql.getSql();
////        System.out.println(sql);
////        String newSql = sql;
////        ReflectUtil.setFieldValue(boundSql, "sql", newSql);
////        return invocation.proceed();
//        Object[] args = invocation.getArgs();
//        MappedStatement ms = (MappedStatement) args[0];
//        Object parameter = args[1];
//        RowBounds rowBounds = (RowBounds) args[2];
//        ResultHandler resultHandler = (ResultHandler) args[3];
//        Executor executor = (Executor) invocation.getTarget();
//        CacheKey cacheKey;
//        BoundSql boundSql;
//        //由于逻辑关系，只会进入一次
//        if(args.length == 4){
//            //4 个参数时
//            boundSql = ms.getBoundSql(parameter);
//            cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
//        } else {
//            //6 个参数时
//            cacheKey = (CacheKey) args[4];
//            boundSql = (BoundSql) args[5];
//        }
//        System.out.println(boundSql.getSql());
//        //TODO 自己要进行的各种处理
//        //注：下面的方法可以根据自己的逻辑调用多次，在分页插件中，count 和 page 各调用了一次
//        return executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
//    }
//
//    @Override
//    public Object plugin(Object o) {
//        return Plugin.wrap(o, this);
//    }
//
//    @Override
//    public void setProperties(Properties properties) {
//
//    }
//
//
//    /**
//     * 获取sql语句
//     * @param invocation
//     * @return
//     */
//    private String getSqlByInvocation(Invocation invocation) {
//        final Object[] args = invocation.getArgs();
//        MappedStatement ms = (MappedStatement) args[0];
//        Object parameterObject = args[1];
//        BoundSql boundSql = ms.getBoundSql(parameterObject);
//        return boundSql.getSql();
//    }
//    /**
//     * 包装sql后，重置到invocation中
//     * @param invocation
//     * @param sql
//     * @throws SQLException
//     */
//    private void resetSql2Invocation(Invocation invocation, String sql) throws SQLException {
//        final Object[] args = invocation.getArgs();
//        MappedStatement statement = (MappedStatement) args[0];
//        Object parameterObject = args[1];
//        BoundSql boundSql = statement.getBoundSql(parameterObject);
//        MappedStatement newStatement = newMappedStatement(statement, new BoundSqlSqlSource(boundSql));
//        MetaObject msObject =  MetaObject.forObject(newStatement, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(),new DefaultReflectorFactory());
//        msObject.setValue("sqlSource.boundSql.sql", sql);
//        args[0] = newStatement;
//    }
//
//    //    定义一个内部辅助类，作用是包装sq
//    class BoundSqlSqlSource implements SqlSource {
//        private BoundSql boundSql;
//
//        public BoundSqlSqlSource(BoundSql boundSql) {
//            this.boundSql = boundSql;
//        }
//        @Override
//        public BoundSql getBoundSql(Object parameterObject) {
//            return boundSql;
//        }
//    }
//
//    private MappedStatement newMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
//        MappedStatement.Builder builder =
//                new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
//        builder.resource(ms.getResource());
//        builder.fetchSize(ms.getFetchSize());
//        builder.statementType(ms.getStatementType());
//        builder.keyGenerator(ms.getKeyGenerator());
//        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
//            StringBuilder keyProperties = new StringBuilder();
//            for (String keyProperty : ms.getKeyProperties()) {
//                keyProperties.append(keyProperty).append(",");
//            }
//            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
//            builder.keyProperty(keyProperties.toString());
//        }
//        builder.timeout(ms.getTimeout());
//        builder.parameterMap(ms.getParameterMap());
//        builder.resultMaps(ms.getResultMaps());
//        builder.resultSetType(ms.getResultSetType());
//        builder.cache(ms.getCache());
//        builder.flushCacheRequired(ms.isFlushCacheRequired());
//        builder.useCache(ms.isUseCache());
//
//        return builder.build();
//    }
//}
