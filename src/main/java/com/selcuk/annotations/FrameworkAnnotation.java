package com.selcuk.annotations;

import com.selcuk.enums.CategoryType;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface FrameworkAnnotation {
    /**
     * Store the authors who created the tests in String[]
     * Manadatory to enter atleast a value
     * @author Amuthan Sakthivel
     * Jan 21, 2021
     */
    public String[] author();

    /**
     * Stores the category in form of Enum Array. Include the possible values in {@link com.tmb.enums.CategoryType}
     * @author Amuthan Sakthivel
     * Jan 21, 2021
     */
    public CategoryType[] category();
}
