package ru.otus.jdbc.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import ru.otus.core.repository.DataTemplate;
import ru.otus.core.repository.DataTemplateException;
import ru.otus.core.repository.executor.DbExecutor;

/**
 * Сохраняет объект в базу, читает объект из базы
 */
public class DataTemplateJdbc<T> implements DataTemplate<T> {

  private final DbExecutor dbExecutor;
  private final EntitySQLMetaData entitySQLMetaData;
  private final EntityClassMetaData<T> entityClassMetaData;

  public DataTemplateJdbc(DbExecutor dbExecutor, EntitySQLMetaData entitySQLMetaData, EntityClassMetaData<T> entityClassMetaData) {
    this.dbExecutor = dbExecutor;
    this.entitySQLMetaData = entitySQLMetaData;
    this.entityClassMetaData = entityClassMetaData;
  }

  @Override
  public Optional<T> findById(Connection connection, long id) {
    return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(), List.of(id), rs -> {
      try {
        if (rs.next()) {
          return buildEntity(rs);
        }
        return null;
      } catch (SQLException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
        throw new DataTemplateException(e);
      }
    });
  }

  private T buildEntity(ResultSet rs) throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException {
    T instance = entityClassMetaData.getConstructor().newInstance();
    List<Field> fields = entityClassMetaData.getAllFields();
    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
      String columnName = rs.getMetaData().getColumnName(i);
      Field field = fields.stream()
          .filter(fld -> fld.getName().equals(columnName))
          .findFirst()
          .orElseThrow(() -> new RuntimeException("No such field"));
      field.setAccessible(true);
      field.set(instance, rs.getObject(i));
    }
    return instance;
  }

  @Override
  public List<T> findAll(Connection connection) {
    return dbExecutor
        .executeSelect(connection, entitySQLMetaData.getSelectAllSql(), Collections.emptyList(), rs -> {
          var entityList = new ArrayList<T>();
          try {
            while (rs.next()) {
              entityList.add(buildEntity(rs));
            }
            return entityList;
          } catch (SQLException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new DataTemplateException(e);
          }
        })
        .orElseThrow(() -> new RuntimeException("Unexpected error"));
  }

  @Override
  public long insert(Connection connection, T object) {
    try {
      return dbExecutor.executeStatement(connection, entitySQLMetaData.getInsertSql(), getValuesFromEntity(object));
    } catch (Exception e) {
      throw new DataTemplateException(e);
    }
  }

  @Override
  public void update(Connection connection, T object) {
    try {
      dbExecutor.executeStatement(connection, entitySQLMetaData.getUpdateSql(), getValuesFromEntity(object));
    } catch (Exception e) {
      throw new DataTemplateException(e);
    }
  }

  private List<Object> getValuesFromEntity(T entity) {
    return entityClassMetaData.getFieldsWithoutId().stream()
        .map(field -> {
          field.setAccessible(true);
          try {
            return field.get(entity);
          } catch (IllegalAccessException e) {
            throw new DataTemplateException(e);
          }
        })
        .toList();
  }
}

