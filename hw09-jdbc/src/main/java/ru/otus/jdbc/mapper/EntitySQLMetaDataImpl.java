package ru.otus.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData {
  private static final String SELECT_TEMPLATE = "select %s from %s";
  private static final String SELECT_BY_ID_TEMPLATE = "select %s from %s where %s = ?";
  private static final String INSERT_TEMPLATE = "insert into %s(%s) values (%s)";
  private static final String UPDATE_TEMPLATE = "update %s set(%s) = (%s) where %s = ?";
  private final EntityClassMetaData<T> metaData;

  public EntitySQLMetaDataImpl(EntityClassMetaData<T> metaData) {
    this.metaData = metaData;
  }

  @Override
  public String getSelectAllSql() {
    return SELECT_TEMPLATE.formatted(
        metaData.getAllFields().stream()
            .map(Field::getName)
            .collect(Collectors.joining(",")),
        metaData.getName());
  }

  @Override
  public String getSelectByIdSql() {
    return SELECT_BY_ID_TEMPLATE.formatted(
        metaData.getAllFields().stream()
            .map(Field::getName)
            .collect(Collectors.joining(",")),
        metaData.getName(),
        metaData.getIdField().getName());
  }

  @Override
  public String getInsertSql() {
    return INSERT_TEMPLATE.formatted(
        metaData.getName(),
        metaData.getFieldsWithoutId().stream()
            .map(Field::getName)
            .collect(Collectors.joining(",")),
        StringUtils.repeat("?", ",", metaData.getFieldsWithoutId().size()));
  }

  @Override
  public String getUpdateSql() {
    return UPDATE_TEMPLATE.formatted(
        metaData.getName(),
        metaData.getFieldsWithoutId().stream()
            .map(Field::getName)
            .collect(Collectors.joining(",")),
        StringUtils.repeat("?", ",", metaData.getFieldsWithoutId().size()),
        metaData.getIdField().getName());
  }
}
