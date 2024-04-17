package ru.otus.jdbc.mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import ru.otus.crm.model.Id;
import ru.otus.jdbc.exception.EntityMapperException;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {

  private final Class<T> clazz;
  private final Constructor<T> constructor;
  private final List<Field> allFields = new ArrayList<>();
  private final List<Field> fieldsWithoutId = new ArrayList<>();
  private Field idField;

  public EntityClassMetaDataImpl(Class<T> clazz) {
    this.clazz = clazz;

    try {
      constructor = clazz.getDeclaredConstructor();
    } catch (NoSuchMethodException e) {
      throw new EntityMapperException("Constructor not found", e);
    }

    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      allFields.add(field);
      if (field.isAnnotationPresent(Id.class)) {
        if (idField != null) {
          throw new EntityMapperException("Must be only one field with annotation @Id");
        }
        idField = field;
      } else {
        fieldsWithoutId.add(field);
      }
    }
    if (idField == null) {
      throw new EntityMapperException("Not found field with annotation @Id");
    }

  }

  @Override
  public String getName() {
    return clazz.getSimpleName().toLowerCase();
  }

  @Override
  public Constructor<T> getConstructor() {
    return constructor;
  }

  @Override
  public Field getIdField() {
    return idField;
  }

  @Override
  public List<Field> getAllFields() {
    return allFields;
  }

  @Override
  public List<Field> getFieldsWithoutId() {
    return fieldsWithoutId;
  }
}
