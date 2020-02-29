package com.cyun.utils.annotation;

import java.lang.annotation.*;

/**
 * Retention可以在您定义Annotation型态时，指示编译器如何对待您的自定义 Annotation，预设上编译器会将Annotation资讯留在class档案中，但不被虚拟机器读取，而仅用于编译器或工具程式运行时提供资讯。
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {
    // 为了直接使用默认@SystemControllerLog("XXX")，所以用value替代description
    String value()  default "";
}