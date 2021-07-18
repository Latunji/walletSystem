package com.osm.wallet.api.base.dao;/*
 * Copyright (c) 2020.
 * This code is proprietary to GNL Systems Ltd. All rights reserved.
 */




import com.osm.wallet.api.abstractentities.predicate.CustomPredicate;
import com.osm.wallet.api.abstractentities.predicate.OrderBy;
import com.osm.wallet.api.abstractentities.predicate.PredicateBuilder;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public interface IGenericDao {


    /**
     *
     * @param clazz
     * @param customPredicate - @See <code>com.osm.gnl.ippms.ogsg.abstractentities.predicate.CustomPredicate</code>
     * @param order
     * @param <T>
     * @return
     */
    <T> List<T> loadAllObjectsUsingRestrictions(Class<T> clazz, final List<CustomPredicate> customPredicate, String order);

    /**
     *
     * @param predicateBuilder consists of one or more custom predicates which defines search criteria
     * @param clazz
     * @param <T>
     * @return
     */
    <T> int countObjectsUsingPredicateBuilder(PredicateBuilder predicateBuilder, Class<T> clazz);


    <T> List<T> loadObjectsUsingPredicateBuilder(PredicateBuilder predicateBuilder, Class<T> clazz);

    <T> List<T> loadObjectsUsingPredicateBuilder(PredicateBuilder predicateBuilder, Class<T> clazz, List<OrderBy> orderBy);

    <T> T loadSingleObjectUsingPredicateBuilder(PredicateBuilder predicateBuilder, Class<T> clazz);

    <T, X extends Number> X sumFieldUsingPredicateBuilder(Class<T> rootClass, PredicateBuilder predicateBuilder, Class<X> sumClass, String sumField,
                                                          List<String> groupByFields);

    <T> List<T> loadAllObjectsWithoutRestrictions(Class<T> clazz, String order);

    <T> List<T> loadAllObjectsWithSingleCondition(Class<T> clazz, CustomPredicate customPredicate, String order);


    <T> T loadObjectWithSingleConditionAllowNull(Class<T> pObjectClass, CustomPredicate customPredicate) throws InstantiationException, IllegalAccessException;

    <T> T  loadObjectById(Class<T> clazz, Long pId) throws DataAccessException, IllegalAccessException, InstantiationException;

    <T> T loadObjectUsingRestriction(Class<T> clazz, List<CustomPredicate> predicates) throws IllegalAccessException, InstantiationException;

    <T> T loadObjectUsingRestrictionAllowNull(Class<T> pObjectClass, List<CustomPredicate> predicates) throws IllegalAccessException, InstantiationException;

    <T> List<T> loadPaginatedObjects(Class<T> clazz, List<CustomPredicate> predicates, int pStartRowNum, int pEndRowNum, String pSortOrder, String pSortCriterion );


    Long getTotalPaginatedObjects(Class<?> clazz,List<CustomPredicate> predicates);

    void  storeObject(Object pObject);

    <T> T loadObjectWithSingleCondition(Class<T> pObjectClass, CustomPredicate customPredicate) throws InstantiationException, IllegalAccessException;


    <T> List<T> loadControlEntity(Class<T> clazz);

    Long saveObject(Object object);

    /**
     *
     * @param pFSaveList - List<?>
     */
    void storeObjectBatch(List<?> pFSaveList);

    /**
     *
     * @param pFSaveList - Vector<?>
     */
    void storeVectorObjectBatch(Vector<?> pFSaveList);
    <T> boolean isObjectExisting(Class<T> clazz, PredicateBuilder predicateBuilder);
    void deleteObject(Object object);

    Session getCurrentSession();
    Long loadMaxValueByClassAndLongColName(Class<?> clazz, String pLongColumnOrmName);

    Long loadMaxValueByClassClientIdAndColumn(Class<?> clazz, String pLongColumnOrmName, Long pBizClientId, String pBizClientOrmName);

    <T> HashMap<Long,T> loadObjectsAsMap(Class<T> pObjectClass, List<CustomPredicate> predicates, String pMethodName)throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    <T> HashMap<?,T> loadObjectsAsMapWithStringKey(Class<T> pObjectClass, List<CustomPredicate> predicates, String pMethodName)throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    int getTotalNoOfModelObjectByClass(Class<?> pObjectClass,String pOrmCol ,boolean pDistinct);

   <T> void deleteObjectsWithConditions(Class<T> stepIncreaseBeanClass, List<CustomPredicate> predicates);

    <T> T loadDefaultEntity(Class<T> clazz, List<CustomPredicate> customPredicateList) throws IllegalAccessException, InstantiationException;

    <T> int loadMaxValueByClassAndIntColName(Class<T> pClass, String colName);

    /**
     *
     * @return
     * Use this method with care. Implementation requires a Business Client variable
     * :pBizIdVar. without this bind variable, this method fails.
     */

    Long getTotalPaginatedObjectsByPredicate(Class<?> clazz, List<CustomPredicate> customPredicateList);

    <T> List<T> loadPaginatedObjectsByPredicates(Class<T> pObjectClass, PredicateBuilder predicateBuilder, int pStartRowNum, int pEndRowNum, String pSortOrder, String pSortCriterion);
}
