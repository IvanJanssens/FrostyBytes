package com.lina.frostybytes.core_api.shared;

import com.lina.frostybytes.problemdetails.GlobalErrorCode;
import com.lina.frostybytes.utils.StackTraceUtils;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class EntityNotFound extends FrostyBytesException {
    public EntityNotFound(@NotNull UUID entityId, @NotNull Class<?> entityClazz) {
        super("%s#%s: Entity of class %s with id %s can not be found"
                .formatted(
                        StackTraceUtils.getClassName(StackTraceUtils.getPreviousStackTraceElement()),
                        StackTraceUtils.getMethodName(StackTraceUtils.getPreviousStackTraceElement()),
                        entityClazz,
                        entityId
                ), GlobalErrorCode.ENTITY_NOT_FOUND_ERROR);
    }
}
