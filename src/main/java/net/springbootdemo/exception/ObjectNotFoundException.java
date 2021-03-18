package net.springbootdemo.exception;

import java.util.function.Supplier;

/**
 * @author val.rudi
 */
public class ObjectNotFoundException extends BaseException {

    public ObjectNotFoundException(String entityName, Object identity) {
        super(entityName + "[" + identity + "] not found", ErrorCode.NOT_FOUND);
    }

    public static Supplier<ObjectNotFoundException> objectNotFoundExSupplier(Class<?> clazz, Object identity) {
        return () -> new ObjectNotFoundException(clazz.getSimpleName(), identity);
    }

    public static ObjectNotFoundException build(Class<?> clazz, Object identity) {
        return new ObjectNotFoundException(clazz.getSimpleName(), identity);
    }
}
