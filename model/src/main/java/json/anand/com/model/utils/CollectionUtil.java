package json.anand.com.model.utils;

import java.util.Collection;
import java.util.Map;

/**
 * Collection Utility.
 *
 * @author Anand Shinde
 */

public class CollectionUtil {
  private CollectionUtil() {
  }

  /**
   * Checks if collection is empty.
   *
   * @param collection
   * @return
   */
  public static boolean isEmpty(Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }

  /**
   * Checks if collection is not empty.
   *
   * @param collection
   * @return
   */
  public static boolean isNotEmpty(Collection<?> collection) {
    return !isEmpty(collection);
  }

  /**
   * Returns the value associated with key.
   *
   * @param map
   * @param key
   * @return
   */
  @SuppressWarnings("rawtypes")
  public static Object getObject(Map map, Object key) {
    return getObject(map, key, null);
  }

  /**
   * Returns the value associated with key otherwise returns default.
   *
   * @param map
   * @param key
   * @param defaultValue
   * @return
   */
  @SuppressWarnings("rawtypes")
  public static Object getObject(Map map, Object key, Object defaultValue) {
    if (map != null) {
      Object value = map.get(key);

      if (value != null) {
        return value;
      }
    }
    return defaultValue;
  }
}