package com.selcuk.utilities;

import com.selcuk.constants.ProjectConstants;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Holds the data provider for all the test cases in the framework.
 *
 *
 * @version 1.0
 * @since 1.0
 */
public final class DataProviderUtils {

    /**
     * Private constructor to avoid external instantiation
     */
    private DataProviderUtils() {}

    private static List<Map<String, String>> list =	new ArrayList<>();


    /**
     * Acts as a data provider for all the test cases.
     * Parallel=true indicates that each of the iteration will be ran in parallel.
     *
     * @author Amuthan Sakthivel
     * Jan 22, 2021
     * @param m {@link java.lang.reflect.Method} holds the information about the testcases at runtime
     * @return Object[] containing the List. Each index of the list contains HashMap and each of them
     * contains the test data needed to run the iterations.
     *
     *
     * @see com.tmb.tests;
     * @see com.tmb.listeners.AnnotationTransformer
     */
    @DataProvider(parallel=false)
    public static Object[] getData(Method m) {
        String testname = m.getName();

        if(list.isEmpty()) {
            list = ExcelUtils.getTestDetails(ProjectConstants.getIterationDatasheet());
            System.out.println(list);
        }
        List<Map<String, String>> smalllist = new ArrayList<>(list);

        Predicate<Map<String,String>> isTestNameNotMatching = map ->!map.get("testname").equalsIgnoreCase(testname);
        Predicate<Map<String,String>> isExecuteColumnNo = map -> map.get("execute").equalsIgnoreCase("no");

        smalllist.removeIf(isTestNameNotMatching.or(isExecuteColumnNo));
        return smalllist.toArray();

    }

}
