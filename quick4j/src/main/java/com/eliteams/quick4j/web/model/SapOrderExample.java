package com.eliteams.quick4j.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SapOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SapOrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdIsNull() {
            addCriterion("product_order_id is null");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdIsNotNull() {
            addCriterion("product_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdEqualTo(String value) {
            addCriterion("product_order_id =", value, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdNotEqualTo(String value) {
            addCriterion("product_order_id <>", value, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdGreaterThan(String value) {
            addCriterion("product_order_id >", value, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("product_order_id >=", value, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdLessThan(String value) {
            addCriterion("product_order_id <", value, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdLessThanOrEqualTo(String value) {
            addCriterion("product_order_id <=", value, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdLike(String value) {
            addCriterion("product_order_id like", value, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdNotLike(String value) {
            addCriterion("product_order_id not like", value, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdIn(List<String> values) {
            addCriterion("product_order_id in", values, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdNotIn(List<String> values) {
            addCriterion("product_order_id not in", values, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdBetween(String value1, String value2) {
            addCriterion("product_order_id between", value1, value2, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdNotBetween(String value1, String value2) {
            addCriterion("product_order_id not between", value1, value2, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderTypeIsNull() {
            addCriterion("product_order_type is null");
            return (Criteria) this;
        }

        public Criteria andProductOrderTypeIsNotNull() {
            addCriterion("product_order_type is not null");
            return (Criteria) this;
        }

        public Criteria andProductOrderTypeEqualTo(String value) {
            addCriterion("product_order_type =", value, "productOrderType");
            return (Criteria) this;
        }

        public Criteria andProductOrderTypeNotEqualTo(String value) {
            addCriterion("product_order_type <>", value, "productOrderType");
            return (Criteria) this;
        }

        public Criteria andProductOrderTypeGreaterThan(String value) {
            addCriterion("product_order_type >", value, "productOrderType");
            return (Criteria) this;
        }

        public Criteria andProductOrderTypeGreaterThanOrEqualTo(String value) {
            addCriterion("product_order_type >=", value, "productOrderType");
            return (Criteria) this;
        }

        public Criteria andProductOrderTypeLessThan(String value) {
            addCriterion("product_order_type <", value, "productOrderType");
            return (Criteria) this;
        }

        public Criteria andProductOrderTypeLessThanOrEqualTo(String value) {
            addCriterion("product_order_type <=", value, "productOrderType");
            return (Criteria) this;
        }

        public Criteria andProductOrderTypeLike(String value) {
            addCriterion("product_order_type like", value, "productOrderType");
            return (Criteria) this;
        }

        public Criteria andProductOrderTypeNotLike(String value) {
            addCriterion("product_order_type not like", value, "productOrderType");
            return (Criteria) this;
        }

        public Criteria andProductOrderTypeIn(List<String> values) {
            addCriterion("product_order_type in", values, "productOrderType");
            return (Criteria) this;
        }

        public Criteria andProductOrderTypeNotIn(List<String> values) {
            addCriterion("product_order_type not in", values, "productOrderType");
            return (Criteria) this;
        }

        public Criteria andProductOrderTypeBetween(String value1, String value2) {
            addCriterion("product_order_type between", value1, value2, "productOrderType");
            return (Criteria) this;
        }

        public Criteria andProductOrderTypeNotBetween(String value1, String value2) {
            addCriterion("product_order_type not between", value1, value2, "productOrderType");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdIsNull() {
            addCriterion("sale_order_id is null");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdIsNotNull() {
            addCriterion("sale_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdEqualTo(String value) {
            addCriterion("sale_order_id =", value, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdNotEqualTo(String value) {
            addCriterion("sale_order_id <>", value, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdGreaterThan(String value) {
            addCriterion("sale_order_id >", value, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("sale_order_id >=", value, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdLessThan(String value) {
            addCriterion("sale_order_id <", value, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdLessThanOrEqualTo(String value) {
            addCriterion("sale_order_id <=", value, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdLike(String value) {
            addCriterion("sale_order_id like", value, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdNotLike(String value) {
            addCriterion("sale_order_id not like", value, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdIn(List<String> values) {
            addCriterion("sale_order_id in", values, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdNotIn(List<String> values) {
            addCriterion("sale_order_id not in", values, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdBetween(String value1, String value2) {
            addCriterion("sale_order_id between", value1, value2, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdNotBetween(String value1, String value2) {
            addCriterion("sale_order_id not between", value1, value2, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andSaleOrderRowIsNull() {
            addCriterion("sale_order_row is null");
            return (Criteria) this;
        }

        public Criteria andSaleOrderRowIsNotNull() {
            addCriterion("sale_order_row is not null");
            return (Criteria) this;
        }

        public Criteria andSaleOrderRowEqualTo(String value) {
            addCriterion("sale_order_row =", value, "saleOrderRow");
            return (Criteria) this;
        }

        public Criteria andSaleOrderRowNotEqualTo(String value) {
            addCriterion("sale_order_row <>", value, "saleOrderRow");
            return (Criteria) this;
        }

        public Criteria andSaleOrderRowGreaterThan(String value) {
            addCriterion("sale_order_row >", value, "saleOrderRow");
            return (Criteria) this;
        }

        public Criteria andSaleOrderRowGreaterThanOrEqualTo(String value) {
            addCriterion("sale_order_row >=", value, "saleOrderRow");
            return (Criteria) this;
        }

        public Criteria andSaleOrderRowLessThan(String value) {
            addCriterion("sale_order_row <", value, "saleOrderRow");
            return (Criteria) this;
        }

        public Criteria andSaleOrderRowLessThanOrEqualTo(String value) {
            addCriterion("sale_order_row <=", value, "saleOrderRow");
            return (Criteria) this;
        }

        public Criteria andSaleOrderRowLike(String value) {
            addCriterion("sale_order_row like", value, "saleOrderRow");
            return (Criteria) this;
        }

        public Criteria andSaleOrderRowNotLike(String value) {
            addCriterion("sale_order_row not like", value, "saleOrderRow");
            return (Criteria) this;
        }

        public Criteria andSaleOrderRowIn(List<String> values) {
            addCriterion("sale_order_row in", values, "saleOrderRow");
            return (Criteria) this;
        }

        public Criteria andSaleOrderRowNotIn(List<String> values) {
            addCriterion("sale_order_row not in", values, "saleOrderRow");
            return (Criteria) this;
        }

        public Criteria andSaleOrderRowBetween(String value1, String value2) {
            addCriterion("sale_order_row between", value1, value2, "saleOrderRow");
            return (Criteria) this;
        }

        public Criteria andSaleOrderRowNotBetween(String value1, String value2) {
            addCriterion("sale_order_row not between", value1, value2, "saleOrderRow");
            return (Criteria) this;
        }

        public Criteria andUserSimpleNameIsNull() {
            addCriterion("user_simple_name is null");
            return (Criteria) this;
        }

        public Criteria andUserSimpleNameIsNotNull() {
            addCriterion("user_simple_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserSimpleNameEqualTo(String value) {
            addCriterion("user_simple_name =", value, "userSimpleName");
            return (Criteria) this;
        }

        public Criteria andUserSimpleNameNotEqualTo(String value) {
            addCriterion("user_simple_name <>", value, "userSimpleName");
            return (Criteria) this;
        }

        public Criteria andUserSimpleNameGreaterThan(String value) {
            addCriterion("user_simple_name >", value, "userSimpleName");
            return (Criteria) this;
        }

        public Criteria andUserSimpleNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_simple_name >=", value, "userSimpleName");
            return (Criteria) this;
        }

        public Criteria andUserSimpleNameLessThan(String value) {
            addCriterion("user_simple_name <", value, "userSimpleName");
            return (Criteria) this;
        }

        public Criteria andUserSimpleNameLessThanOrEqualTo(String value) {
            addCriterion("user_simple_name <=", value, "userSimpleName");
            return (Criteria) this;
        }

        public Criteria andUserSimpleNameLike(String value) {
            addCriterion("user_simple_name like", value, "userSimpleName");
            return (Criteria) this;
        }

        public Criteria andUserSimpleNameNotLike(String value) {
            addCriterion("user_simple_name not like", value, "userSimpleName");
            return (Criteria) this;
        }

        public Criteria andUserSimpleNameIn(List<String> values) {
            addCriterion("user_simple_name in", values, "userSimpleName");
            return (Criteria) this;
        }

        public Criteria andUserSimpleNameNotIn(List<String> values) {
            addCriterion("user_simple_name not in", values, "userSimpleName");
            return (Criteria) this;
        }

        public Criteria andUserSimpleNameBetween(String value1, String value2) {
            addCriterion("user_simple_name between", value1, value2, "userSimpleName");
            return (Criteria) this;
        }

        public Criteria andUserSimpleNameNotBetween(String value1, String value2) {
            addCriterion("user_simple_name not between", value1, value2, "userSimpleName");
            return (Criteria) this;
        }

        public Criteria andManufactureVersionIsNull() {
            addCriterion("manufacture_version is null");
            return (Criteria) this;
        }

        public Criteria andManufactureVersionIsNotNull() {
            addCriterion("manufacture_version is not null");
            return (Criteria) this;
        }

        public Criteria andManufactureVersionEqualTo(String value) {
            addCriterion("manufacture_version =", value, "manufactureVersion");
            return (Criteria) this;
        }

        public Criteria andManufactureVersionNotEqualTo(String value) {
            addCriterion("manufacture_version <>", value, "manufactureVersion");
            return (Criteria) this;
        }

        public Criteria andManufactureVersionGreaterThan(String value) {
            addCriterion("manufacture_version >", value, "manufactureVersion");
            return (Criteria) this;
        }

        public Criteria andManufactureVersionGreaterThanOrEqualTo(String value) {
            addCriterion("manufacture_version >=", value, "manufactureVersion");
            return (Criteria) this;
        }

        public Criteria andManufactureVersionLessThan(String value) {
            addCriterion("manufacture_version <", value, "manufactureVersion");
            return (Criteria) this;
        }

        public Criteria andManufactureVersionLessThanOrEqualTo(String value) {
            addCriterion("manufacture_version <=", value, "manufactureVersion");
            return (Criteria) this;
        }

        public Criteria andManufactureVersionLike(String value) {
            addCriterion("manufacture_version like", value, "manufactureVersion");
            return (Criteria) this;
        }

        public Criteria andManufactureVersionNotLike(String value) {
            addCriterion("manufacture_version not like", value, "manufactureVersion");
            return (Criteria) this;
        }

        public Criteria andManufactureVersionIn(List<String> values) {
            addCriterion("manufacture_version in", values, "manufactureVersion");
            return (Criteria) this;
        }

        public Criteria andManufactureVersionNotIn(List<String> values) {
            addCriterion("manufacture_version not in", values, "manufactureVersion");
            return (Criteria) this;
        }

        public Criteria andManufactureVersionBetween(String value1, String value2) {
            addCriterion("manufacture_version between", value1, value2, "manufactureVersion");
            return (Criteria) this;
        }

        public Criteria andManufactureVersionNotBetween(String value1, String value2) {
            addCriterion("manufacture_version not between", value1, value2, "manufactureVersion");
            return (Criteria) this;
        }

        public Criteria andMaterialIdIsNull() {
            addCriterion("material_id is null");
            return (Criteria) this;
        }

        public Criteria andMaterialIdIsNotNull() {
            addCriterion("material_id is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialIdEqualTo(String value) {
            addCriterion("material_id =", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdNotEqualTo(String value) {
            addCriterion("material_id <>", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdGreaterThan(String value) {
            addCriterion("material_id >", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdGreaterThanOrEqualTo(String value) {
            addCriterion("material_id >=", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdLessThan(String value) {
            addCriterion("material_id <", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdLessThanOrEqualTo(String value) {
            addCriterion("material_id <=", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdLike(String value) {
            addCriterion("material_id like", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdNotLike(String value) {
            addCriterion("material_id not like", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdIn(List<String> values) {
            addCriterion("material_id in", values, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdNotIn(List<String> values) {
            addCriterion("material_id not in", values, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdBetween(String value1, String value2) {
            addCriterion("material_id between", value1, value2, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdNotBetween(String value1, String value2) {
            addCriterion("material_id not between", value1, value2, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialDescribeIsNull() {
            addCriterion("material_describe is null");
            return (Criteria) this;
        }

        public Criteria andMaterialDescribeIsNotNull() {
            addCriterion("material_describe is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialDescribeEqualTo(String value) {
            addCriterion("material_describe =", value, "materialDescribe");
            return (Criteria) this;
        }

        public Criteria andMaterialDescribeNotEqualTo(String value) {
            addCriterion("material_describe <>", value, "materialDescribe");
            return (Criteria) this;
        }

        public Criteria andMaterialDescribeGreaterThan(String value) {
            addCriterion("material_describe >", value, "materialDescribe");
            return (Criteria) this;
        }

        public Criteria andMaterialDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("material_describe >=", value, "materialDescribe");
            return (Criteria) this;
        }

        public Criteria andMaterialDescribeLessThan(String value) {
            addCriterion("material_describe <", value, "materialDescribe");
            return (Criteria) this;
        }

        public Criteria andMaterialDescribeLessThanOrEqualTo(String value) {
            addCriterion("material_describe <=", value, "materialDescribe");
            return (Criteria) this;
        }

        public Criteria andMaterialDescribeLike(String value) {
            addCriterion("material_describe like", value, "materialDescribe");
            return (Criteria) this;
        }

        public Criteria andMaterialDescribeNotLike(String value) {
            addCriterion("material_describe not like", value, "materialDescribe");
            return (Criteria) this;
        }

        public Criteria andMaterialDescribeIn(List<String> values) {
            addCriterion("material_describe in", values, "materialDescribe");
            return (Criteria) this;
        }

        public Criteria andMaterialDescribeNotIn(List<String> values) {
            addCriterion("material_describe not in", values, "materialDescribe");
            return (Criteria) this;
        }

        public Criteria andMaterialDescribeBetween(String value1, String value2) {
            addCriterion("material_describe between", value1, value2, "materialDescribe");
            return (Criteria) this;
        }

        public Criteria andMaterialDescribeNotBetween(String value1, String value2) {
            addCriterion("material_describe not between", value1, value2, "materialDescribe");
            return (Criteria) this;
        }

        public Criteria andTargetSumIsNull() {
            addCriterion("target_sum is null");
            return (Criteria) this;
        }

        public Criteria andTargetSumIsNotNull() {
            addCriterion("target_sum is not null");
            return (Criteria) this;
        }

        public Criteria andTargetSumEqualTo(Integer value) {
            addCriterion("target_sum =", value, "targetSum");
            return (Criteria) this;
        }

        public Criteria andTargetSumNotEqualTo(Integer value) {
            addCriterion("target_sum <>", value, "targetSum");
            return (Criteria) this;
        }

        public Criteria andTargetSumGreaterThan(Integer value) {
            addCriterion("target_sum >", value, "targetSum");
            return (Criteria) this;
        }

        public Criteria andTargetSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("target_sum >=", value, "targetSum");
            return (Criteria) this;
        }

        public Criteria andTargetSumLessThan(Integer value) {
            addCriterion("target_sum <", value, "targetSum");
            return (Criteria) this;
        }

        public Criteria andTargetSumLessThanOrEqualTo(Integer value) {
            addCriterion("target_sum <=", value, "targetSum");
            return (Criteria) this;
        }

        public Criteria andTargetSumIn(List<Integer> values) {
            addCriterion("target_sum in", values, "targetSum");
            return (Criteria) this;
        }

        public Criteria andTargetSumNotIn(List<Integer> values) {
            addCriterion("target_sum not in", values, "targetSum");
            return (Criteria) this;
        }

        public Criteria andTargetSumBetween(Integer value1, Integer value2) {
            addCriterion("target_sum between", value1, value2, "targetSum");
            return (Criteria) this;
        }

        public Criteria andTargetSumNotBetween(Integer value1, Integer value2) {
            addCriterion("target_sum not between", value1, value2, "targetSum");
            return (Criteria) this;
        }

        public Criteria andFinishedTotalIsNull() {
            addCriterion("finished_total is null");
            return (Criteria) this;
        }

        public Criteria andFinishedTotalIsNotNull() {
            addCriterion("finished_total is not null");
            return (Criteria) this;
        }

        public Criteria andFinishedTotalEqualTo(Integer value) {
            addCriterion("finished_total =", value, "finishedTotal");
            return (Criteria) this;
        }

        public Criteria andFinishedTotalNotEqualTo(Integer value) {
            addCriterion("finished_total <>", value, "finishedTotal");
            return (Criteria) this;
        }

        public Criteria andFinishedTotalGreaterThan(Integer value) {
            addCriterion("finished_total >", value, "finishedTotal");
            return (Criteria) this;
        }

        public Criteria andFinishedTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("finished_total >=", value, "finishedTotal");
            return (Criteria) this;
        }

        public Criteria andFinishedTotalLessThan(Integer value) {
            addCriterion("finished_total <", value, "finishedTotal");
            return (Criteria) this;
        }

        public Criteria andFinishedTotalLessThanOrEqualTo(Integer value) {
            addCriterion("finished_total <=", value, "finishedTotal");
            return (Criteria) this;
        }

        public Criteria andFinishedTotalIn(List<Integer> values) {
            addCriterion("finished_total in", values, "finishedTotal");
            return (Criteria) this;
        }

        public Criteria andFinishedTotalNotIn(List<Integer> values) {
            addCriterion("finished_total not in", values, "finishedTotal");
            return (Criteria) this;
        }

        public Criteria andFinishedTotalBetween(Integer value1, Integer value2) {
            addCriterion("finished_total between", value1, value2, "finishedTotal");
            return (Criteria) this;
        }

        public Criteria andFinishedTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("finished_total not between", value1, value2, "finishedTotal");
            return (Criteria) this;
        }

        public Criteria andWasteTotalIsNull() {
            addCriterion("waste_total is null");
            return (Criteria) this;
        }

        public Criteria andWasteTotalIsNotNull() {
            addCriterion("waste_total is not null");
            return (Criteria) this;
        }

        public Criteria andWasteTotalEqualTo(Integer value) {
            addCriterion("waste_total =", value, "wasteTotal");
            return (Criteria) this;
        }

        public Criteria andWasteTotalNotEqualTo(Integer value) {
            addCriterion("waste_total <>", value, "wasteTotal");
            return (Criteria) this;
        }

        public Criteria andWasteTotalGreaterThan(Integer value) {
            addCriterion("waste_total >", value, "wasteTotal");
            return (Criteria) this;
        }

        public Criteria andWasteTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("waste_total >=", value, "wasteTotal");
            return (Criteria) this;
        }

        public Criteria andWasteTotalLessThan(Integer value) {
            addCriterion("waste_total <", value, "wasteTotal");
            return (Criteria) this;
        }

        public Criteria andWasteTotalLessThanOrEqualTo(Integer value) {
            addCriterion("waste_total <=", value, "wasteTotal");
            return (Criteria) this;
        }

        public Criteria andWasteTotalIn(List<Integer> values) {
            addCriterion("waste_total in", values, "wasteTotal");
            return (Criteria) this;
        }

        public Criteria andWasteTotalNotIn(List<Integer> values) {
            addCriterion("waste_total not in", values, "wasteTotal");
            return (Criteria) this;
        }

        public Criteria andWasteTotalBetween(Integer value1, Integer value2) {
            addCriterion("waste_total between", value1, value2, "wasteTotal");
            return (Criteria) this;
        }

        public Criteria andWasteTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("waste_total not between", value1, value2, "wasteTotal");
            return (Criteria) this;
        }

        public Criteria andRelateScarpIsNull() {
            addCriterion("relate_scarp is null");
            return (Criteria) this;
        }

        public Criteria andRelateScarpIsNotNull() {
            addCriterion("relate_scarp is not null");
            return (Criteria) this;
        }

        public Criteria andRelateScarpEqualTo(Integer value) {
            addCriterion("relate_scarp =", value, "relateScarp");
            return (Criteria) this;
        }

        public Criteria andRelateScarpNotEqualTo(Integer value) {
            addCriterion("relate_scarp <>", value, "relateScarp");
            return (Criteria) this;
        }

        public Criteria andRelateScarpGreaterThan(Integer value) {
            addCriterion("relate_scarp >", value, "relateScarp");
            return (Criteria) this;
        }

        public Criteria andRelateScarpGreaterThanOrEqualTo(Integer value) {
            addCriterion("relate_scarp >=", value, "relateScarp");
            return (Criteria) this;
        }

        public Criteria andRelateScarpLessThan(Integer value) {
            addCriterion("relate_scarp <", value, "relateScarp");
            return (Criteria) this;
        }

        public Criteria andRelateScarpLessThanOrEqualTo(Integer value) {
            addCriterion("relate_scarp <=", value, "relateScarp");
            return (Criteria) this;
        }

        public Criteria andRelateScarpIn(List<Integer> values) {
            addCriterion("relate_scarp in", values, "relateScarp");
            return (Criteria) this;
        }

        public Criteria andRelateScarpNotIn(List<Integer> values) {
            addCriterion("relate_scarp not in", values, "relateScarp");
            return (Criteria) this;
        }

        public Criteria andRelateScarpBetween(Integer value1, Integer value2) {
            addCriterion("relate_scarp between", value1, value2, "relateScarp");
            return (Criteria) this;
        }

        public Criteria andRelateScarpNotBetween(Integer value1, Integer value2) {
            addCriterion("relate_scarp not between", value1, value2, "relateScarp");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("unit like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("unit not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("unit not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateIsNull() {
            addCriterion("plan_start_date is null");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateIsNotNull() {
            addCriterion("plan_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateEqualTo(Date value) {
            addCriterionForJDBCDate("plan_start_date =", value, "planStartDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("plan_start_date <>", value, "planStartDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateGreaterThan(Date value) {
            addCriterionForJDBCDate("plan_start_date >", value, "planStartDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("plan_start_date >=", value, "planStartDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateLessThan(Date value) {
            addCriterionForJDBCDate("plan_start_date <", value, "planStartDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("plan_start_date <=", value, "planStartDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateIn(List<Date> values) {
            addCriterionForJDBCDate("plan_start_date in", values, "planStartDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("plan_start_date not in", values, "planStartDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("plan_start_date between", value1, value2, "planStartDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("plan_start_date not between", value1, value2, "planStartDate");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateIsNull() {
            addCriterion("plan_end_date is null");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateIsNotNull() {
            addCriterion("plan_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("plan_end_date =", value, "planEndDate");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("plan_end_date <>", value, "planEndDate");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("plan_end_date >", value, "planEndDate");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("plan_end_date >=", value, "planEndDate");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateLessThan(Date value) {
            addCriterionForJDBCDate("plan_end_date <", value, "planEndDate");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("plan_end_date <=", value, "planEndDate");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("plan_end_date in", values, "planEndDate");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("plan_end_date not in", values, "planEndDate");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("plan_end_date between", value1, value2, "planEndDate");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("plan_end_date not between", value1, value2, "planEndDate");
            return (Criteria) this;
        }

        public Criteria andDelRemarkIsNull() {
            addCriterion("del_remark is null");
            return (Criteria) this;
        }

        public Criteria andDelRemarkIsNotNull() {
            addCriterion("del_remark is not null");
            return (Criteria) this;
        }

        public Criteria andDelRemarkEqualTo(String value) {
            addCriterion("del_remark =", value, "delRemark");
            return (Criteria) this;
        }

        public Criteria andDelRemarkNotEqualTo(String value) {
            addCriterion("del_remark <>", value, "delRemark");
            return (Criteria) this;
        }

        public Criteria andDelRemarkGreaterThan(String value) {
            addCriterion("del_remark >", value, "delRemark");
            return (Criteria) this;
        }

        public Criteria andDelRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("del_remark >=", value, "delRemark");
            return (Criteria) this;
        }

        public Criteria andDelRemarkLessThan(String value) {
            addCriterion("del_remark <", value, "delRemark");
            return (Criteria) this;
        }

        public Criteria andDelRemarkLessThanOrEqualTo(String value) {
            addCriterion("del_remark <=", value, "delRemark");
            return (Criteria) this;
        }

        public Criteria andDelRemarkLike(String value) {
            addCriterion("del_remark like", value, "delRemark");
            return (Criteria) this;
        }

        public Criteria andDelRemarkNotLike(String value) {
            addCriterion("del_remark not like", value, "delRemark");
            return (Criteria) this;
        }

        public Criteria andDelRemarkIn(List<String> values) {
            addCriterion("del_remark in", values, "delRemark");
            return (Criteria) this;
        }

        public Criteria andDelRemarkNotIn(List<String> values) {
            addCriterion("del_remark not in", values, "delRemark");
            return (Criteria) this;
        }

        public Criteria andDelRemarkBetween(String value1, String value2) {
            addCriterion("del_remark between", value1, value2, "delRemark");
            return (Criteria) this;
        }

        public Criteria andDelRemarkNotBetween(String value1, String value2) {
            addCriterion("del_remark not between", value1, value2, "delRemark");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}