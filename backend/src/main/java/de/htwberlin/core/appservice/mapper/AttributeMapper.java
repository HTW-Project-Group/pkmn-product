package de.htwberlin.core.appservice.mapper;

import static java.util.Optional.ofNullable;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@SuppressWarnings("java:S3011")
@Slf4j
@Component
public class AttributeMapper<T> implements IAttributeMapper<T> {

  @Override
  public T copyAttributes(T source, T target) {
    try {
      Map<String, Field> toFieldNameMap = new HashMap<>();
      for (Field f : source.getClass().getDeclaredFields()) {
        toFieldNameMap.put(f.getName(), f);
      }
      for (Field f : target.getClass().getDeclaredFields()) {
        Field ff = toFieldNameMap.get(f.getName());
        f.setAccessible(true);
        if (ff != null
            && ff.getType().equals(f.getType())
            && attrPresentAndNotDefault(f.get(target))) {
          ff.setAccessible(true);
          ff.set(source, f.get(target));
        }
      }
    } catch (IllegalAccessException e) {
      log.error("Illegal access: " + e.getMessage());
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return ofNullable(source).isPresent() ? source : target;
  }

  private boolean attrPresentAndNotDefault(Object attr) {
    return (ofNullable(attr).isPresent())
        && (!attr.equals(0) && !attr.equals(0.0) && !attr.equals(0L))
        && (!attr.equals(Boolean.FALSE))
        && (!attr.equals(""));
  }
}
