package com.selcuk.projectListeners;

import com.selcuk.constants.ProjectConstants;
import com.selcuk.utilities.ExcelUtils;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MethodInterceptor implements IMethodInterceptor {
    /**
     * Intercepts the existing test methods and changes the annotation value at the run time
     * Values are fetched from the excel sheet.
     * User has to choose yes/No in the RunManager sheet.
     * Suppose if there are 3 tests named a,b,c needs to be run, it reads the excel and understand user wants to
     * run only a and c, then it will return the same after performing the comparisons.
     */

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {

        //loginandlogouttest and newTest

        List<Map<String, String>> list = ExcelUtils.getTestDetails(ProjectConstants.getRunmangerDatasheet());
        List<IMethodInstance> result = new ArrayList<>();

        for(int i=0;i<methods.size();i++) {
            for(int j=0;j<list.size();j++) {
                if(methods.get(i).getMethod().getMethodName().equalsIgnoreCase(list.get(j).get("testname")) &&
                        list.get(j).get("execute").equalsIgnoreCase("yes")) {
                    methods.get(i).getMethod().setDescription((list.get(j).get("testdescription")));
                    methods.get(i).getMethod().setInvocationCount(Integer.parseInt(list.get(j).get("count")));
                    methods.get(i).getMethod().setPriority(Integer.parseInt(list.get(j).get("priority")));
                    result.add(methods.get(i));
                }

            }
        }

        return result;
    }
}
