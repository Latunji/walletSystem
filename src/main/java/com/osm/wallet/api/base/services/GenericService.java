package com.osm.wallet.api.base.services;/*
 * Copyright (c) 2020.
 * This code is proprietary to GNL Systems Ltd. All rights reserved.
 */


import com.osm.wallet.api.abstractentities.predicate.CustomPredicate;
import com.osm.wallet.api.abstractentities.predicate.PredicateBuilder;
import com.osm.wallet.api.base.dao.IGenericDao;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;


@Service("genericService")
public class GenericService {


    private final IGenericDao genericDao;

    @Autowired
    public GenericService(final IGenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public <T> int countObjectsUsingPredicateBuilder(PredicateBuilder predicateBuilder, Class<T> clazz) {
        return this.genericDao.countObjectsUsingPredicateBuilder(predicateBuilder, clazz);
    }


    public  <T> List<T> loadAllObjectsUsingRestrictions(final Class<T> pObjectClass, final List<CustomPredicate> predicates, final String order){
        return this.genericDao.loadAllObjectsUsingRestrictions(pObjectClass,predicates,order);
    }

    public  <T> List<T> loadAllObjectsWithoutRestrictions(final Class<T> pObjectClass, final String order){
        return this.genericDao.loadAllObjectsWithoutRestrictions(pObjectClass,order);
    }

    public <T> List<T> loadAllObjectsWithSingleCondition(final Class<T> pObjectClass, final CustomPredicate customPredicate, final String order){
        return this.genericDao.loadAllObjectsWithSingleCondition(pObjectClass,customPredicate,order);
    }

    public  <T> T loadObjectById(final Class<T> clazz, final Long pId) throws DataAccessException, InstantiationException, IllegalAccessException {
        return this.genericDao.loadObjectById(clazz,pId);
    }

    public  <T> T loadObjectUsingRestriction(final Class<T> pObjectClass, final List<CustomPredicate> predicates) throws InstantiationException, IllegalAccessException {
        return this.genericDao.loadObjectUsingRestriction(pObjectClass, predicates);
    }

    public <T> T loadObjectWithSingleCondition(Class<T> pObjectClass, CustomPredicate customPredicate) throws IllegalAccessException, InstantiationException {
        return this.genericDao.loadObjectWithSingleCondition(pObjectClass,customPredicate);
    }

    public <T> T loadObjectWithSingleConditionAllowNull(Class<T> pObjectClass, CustomPredicate customPredicate) throws IllegalAccessException, InstantiationException {
        return this.genericDao.loadObjectWithSingleConditionAllowNull(pObjectClass, customPredicate);
    }

    public <T> T loadObjectUsingRestrictionAllowNull(Class<T> pObjectClass, List<CustomPredicate> predicates) throws InstantiationException, IllegalAccessException {
        return this.genericDao.loadObjectUsingRestrictionAllowNull(pObjectClass, predicates);
    }

    public <T> boolean isObjectExisting(Class<T> pObjectClass, CustomPredicate customPredicate) throws IllegalAccessException, InstantiationException {
        return this.genericDao.loadObjectWithSingleConditionAllowNull(pObjectClass,customPredicate) != null;
    }
    public Long saveOrUpdate(Object object) {
        return this.genericDao.saveObject(object);

    }
    public  <T> List<T> loadPaginatedObjects(final Class<T> pObjectClass, final List<CustomPredicate> predicates, final int pStartRowNum, final int pEndRowNum,
                                            final String pSortOrder, final String pSortCriterion) {
        return this.genericDao.loadPaginatedObjects(pObjectClass,predicates,pStartRowNum,pEndRowNum,pSortOrder,pSortCriterion);
    }

    public Long getTotalPaginatedObjects(final Class<?> clazz,final List<CustomPredicate> predicates){
        return this.genericDao.getTotalPaginatedObjects(clazz, predicates);
    }
    public Long getTotalPaginatedObjectsByPredicate(final Class<?> clazz,final List<CustomPredicate> predicateBuilder){
        return this.genericDao.getTotalPaginatedObjectsByPredicate(clazz, predicateBuilder);
    }
    public void saveObject(Object object){
        this.genericDao.storeObject(object);
    }

    public <T> List<T> getObjectsFromBuilder(PredicateBuilder predicateBuilder, Class<T> clazz) {
        return this.genericDao.loadObjectsUsingPredicateBuilder(predicateBuilder, clazz);
    }

    public <T> T getSingleObjectFromBuilder(PredicateBuilder predicateBuilder, Class<T> clazz) {
        return this.genericDao.loadSingleObjectUsingPredicateBuilder(predicateBuilder, clazz);
    }

    public <T, X extends Number> X sumFieldUsingPredicateBuilder(Class<T> rootClass, PredicateBuilder predicateBuilder,
         Class<X> sumClass, String sumField, List<String> groupByFields) {
        return this.genericDao.sumFieldUsingPredicateBuilder
                (rootClass, predicateBuilder, sumClass, sumField, groupByFields);
    }

    public <T> List<T> loadControlEntity(Class<T> clazz) {
        return this.genericDao.loadControlEntity(clazz);
    }

    public void storeObjectBatch(List<?> pFSaveList) {
        this.genericDao.storeObjectBatch(pFSaveList);
    }
    public void storeVectorObjectBatch(Vector<?> pFSaveList) {
        this.genericDao.storeVectorObjectBatch(pFSaveList);
    }

    public <T> boolean isObjectExisting(Class<T> clazz, PredicateBuilder predicateBuilder) {
        return this.genericDao.isObjectExisting(clazz, predicateBuilder);
    }
    public void deleteObject(Object object) {
        this.genericDao.deleteObject(object);
    }

    public Session getCurrentSession() {
        return this.genericDao.getCurrentSession();
    }

    public Long loadMaxValueByClassAndLongColName(Class<?> clazz, String pLongOrmMemberName){
        return this.genericDao.loadMaxValueByClassAndLongColName(clazz,pLongOrmMemberName );
    }


  public  <T> HashMap<Long, T> loadObjectAsMapWithConditions(final Class<T> pObjectClass, final List<CustomPredicate> predicates, String pMethodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return this.genericDao.loadObjectsAsMap(pObjectClass, predicates,pMethodName);
    }

    public  <T> HashMap<?, T> loadObjectAsMapWithObjectKey(final Class<T> pObjectClass, final List<CustomPredicate> predicates, String pMethodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return this.genericDao.loadObjectsAsMapWithStringKey(pObjectClass, predicates,pMethodName);
    }

    public Long storeObject(Object pObject) {
        return this.genericDao.saveObject(pObject);
    }

    public int getTotalNoOfModelObjectByClass(Class<?> clazz, String pOrmCol,boolean pDistinct){
        return this.genericDao.getTotalNoOfModelObjectByClass(clazz, pOrmCol,pDistinct);
    }



    public <T> T loadDefaultObject(Class<T> clazz, List<CustomPredicate> customPredicateList) throws InstantiationException, IllegalAccessException {
        return this.genericDao.loadDefaultEntity(clazz,customPredicateList);
    }
    public <T> int loadMaxValueByClassAndIntColName(Class<T> pClass, String colName) {
        return this.genericDao.loadMaxValueByClassAndIntColName(pClass,colName);
    }

    public <T> Long loadMaxValueByClassClientIdAndColumn(Class<?> clazz, String pLongColumnOrmName, Long pBizClientId, String pBizClientOrmName){
        return this.genericDao.loadMaxValueByClassClientIdAndColumn(clazz,pLongColumnOrmName,pBizClientId,pBizClientOrmName);
    }

    /**
     *
     * @param
     * @return
     * Use this method with care. Implementation requires a Business Client variable
     * :pBizIdVar. without this bind variable, this method fails.
     */



    public List<?> loadPaginatedObjectsByPredicates(final Class<?> pObjectClass, final PredicateBuilder predicateBuilder, final int pStartRowNum, final int pEndRowNum,
                                                    final String pSortOrder, final String pSortCriterion) {
        return this.genericDao.loadPaginatedObjectsByPredicates(pObjectClass,predicateBuilder,pStartRowNum,pEndRowNum,pSortOrder,pSortCriterion);

    }
}
