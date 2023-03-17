package com.freedom.common.repository.condition;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 创建 SimpleExpression
public class Restrictions {

    // 前缀
    final static String CHECK_PATTERN = "(NULL|NOTNULL|~=|=|<>|!=|(>=?)|(<=?))";
    // 忽略属性
    static String[] IGNORE_FIELDS = {"seq", "size", "page", "orderBy"};

    /**
     * 将 name，value 转换为 Criterion
     *
     * @param fieldName  字段名称
     * @param fieldValue 字段值
     * @return Criterion
     */
    public static <T> Criterion<T> toCriterion(String fieldName, Object fieldValue) {
        if (fieldValue == null) return null;
        // 排除 Json 中数组类型的值
//        if (Long.class.equals(fieldValue.getClass()) && (Long) fieldValue == 0) {
//            return null;
//        } else if (Integer.class.equals(fieldValue.getClass()) && (Integer) fieldValue == 0) {
//            return null;
//        } else
        if (ArrayList.class.equals(fieldValue.getClass())) {
            return null;
        } else if (Arrays.asList(IGNORE_FIELDS).contains(fieldName)) {
            return null;
        } else if (isEmpty(fieldValue)) {
            return null;
        } else if (String.class.equals(fieldValue.getClass())) {
            // 匹配是否有关键字
            String expression = fieldValue.toString();
            Pattern pattern = Pattern.compile(CHECK_PATTERN);
            Matcher matcher = pattern.matcher(expression);
            //匹配的内容在最前面， matcher.lookingAt() 为 true，表示匹配成功
            if (matcher.lookingAt()) {
                // 获取匹配内容
                String op = matcher.group();
                // 获取值
                String value = expression.substring(matcher.end()).trim();
                return new SimpleExpression<T>(fieldName, value, getOpByName(op));
            }
        }
        // 默认 name = value 的形式
        return new SimpleExpression<T>(fieldName, fieldValue, Criterion.Operator.EQ);
    }

    /**
     * 将运算符转换为
     *
     * @param opName 操作符名称
     * @return 操作符
     */
    static Criterion.Operator getOpByName(String opName) {
        opName = opName.toUpperCase();
        switch (opName) {
            case "~=":
                return Criterion.Operator.LIKE;
            case ">":
                return Criterion.Operator.GT;
            case "<":
                return Criterion.Operator.LT;
            case ">=":
                return Criterion.Operator.GTE;
            case "<=":
                return Criterion.Operator.LTE;
            case "NULL":
                return Criterion.Operator.NULL;
            case "NOTNULL":
                return Criterion.Operator.NOT_NULL;
            case "!=":
            case "<>":
                return Criterion.Operator.NE;
            default:
                return Criterion.Operator.EQ;
        }
    }

    /**
     * 等于 = EQ
     */
    public static <T> SimpleExpression<T> eq(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && isEmpty(value)) {
            return null;
        }
        return new <T>SimpleExpression<T>(fieldName, value, Criterion.Operator.EQ);
    }

    /**
     * 不等于 != <> NE
     */
    public static <T> SimpleExpression<T> ne(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && isEmpty(value)) {
            return null;
        }
        return new SimpleExpression<T>(fieldName, value, Criterion.Operator.NE);
    }

    /**
     * 模糊匹配 LIKE
     */
    public static <T> SimpleExpression<T> like(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && isEmpty(value)) {
            return null;
        }
        return new SimpleExpression<T>(fieldName, value, Criterion.Operator.LIKE);
    }

    /**
     * 大于 > GT
     */
    public static <T> SimpleExpression<T> gt(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && isEmpty(value)) {
            return null;
        }
        return new SimpleExpression<T>(fieldName, value, Criterion.Operator.GT);
    }

    /**
     * 小于 < LT
     */
    public static <T> SimpleExpression<T> lt(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && isEmpty(value)) {
            return null;
        }
        return new SimpleExpression<T>(fieldName, value, Criterion.Operator.LT);
    }

    /**
     * 小于等于 <= LTE
     */
    public static <T> SimpleExpression<T> lte(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && isEmpty(value)) {
            return null;
        }
        return new SimpleExpression<T>(fieldName, value, Criterion.Operator.LTE);
    }

    /**
     * 大于等于 >= GTE
     */
    public static <T> SimpleExpression<T> gte(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && isEmpty(value)) {
            return null;
        }
        return new SimpleExpression<T>(fieldName, value, Criterion.Operator.GTE);
    }

    /**
     * 并且 AND
     */
    public static <T> LogicalExpression<T> and(Criterion<T>... criteria) {
        return new LogicalExpression<T>(criteria, Criterion.Operator.AND);
    }

    /**
     * 或者 OR
     */
    public static <T> LogicalExpression<T> or(Criterion<T>... criteria) {
        return new LogicalExpression<T>(criteria, Criterion.Operator.OR);
    }

    /**
     * 包含于
     */
    public static <T> LogicalExpression<T> in(String fieldName, Collection<?> value, boolean ignoreNull) {
        if (ignoreNull && (value == null || value.isEmpty())) {
            return null;
        }
        SimpleExpression<T>[] expression = new SimpleExpression[value.size()];
        int i = 0;
        for (Object obj : value) {
            expression[i++] = new SimpleExpression<T>(fieldName, obj, Criterion.Operator.EQ);
        }
        return new LogicalExpression<T>(expression, Criterion.Operator.OR);
    }

    /**
     * 集合包含某个元素
     */
    public static <T> SimpleExpression<T> hasMember(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && isEmpty(value)) {
            return null;
        }
        return new SimpleExpression<T>(fieldName, value, Criterion.Operator.IS_MEMBER);
    }

    /**
     * 集合包含某几个元素，譬如可以查询User类中Set<String> set包含"ABC","bcd"的User集合，
     * 或者查询User中Set<Address>的Address的name为"北京"的所有User集合
     * 集合可以为基本类型或者JavaBean，可以是one to many或者是@ElementCollection
     *
     * @param fieldName 列名
     * @param value     集合
     * @return expression
     */
    public static <T> LogicalExpression<T> hasMembers(String fieldName, Object... value) {
        SimpleExpression<T>[] expression = new SimpleExpression[value.length];
        //集合中对象是基本类型，如Set<Long>，List<String>
        Criterion.Operator operator = Criterion.Operator.IS_MEMBER;
        //集合中对象是JavaBean
        if (fieldName.contains(".")) {
            operator = Criterion.Operator.EQ;
        }
        int i = 0;
        for (Object obj : value) {
            expression[i++] = new SimpleExpression<T>(fieldName, obj, operator);
        }
        return new LogicalExpression<T>(expression, Criterion.Operator.OR);
    }

    /**
     * 判断字符串是否为空（ null 或 空字符串 ）
     *
     * @param value 字符串
     * @return 是否为空
     */
    private static boolean isEmpty(Object value) {
        return value == null || value.toString().length() == 0;
    }

}
