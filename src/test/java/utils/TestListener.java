package utils;

import adapters.ProjectAdapter;
import adapters.RunAdapter;
import io.qameta.allure.TmsLink;
import lombok.extern.log4j.Log4j2;
import models.api.Result;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import tests.ApiBaseTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Log4j2
public class TestListener extends ApiBaseTest implements ITestListener {

    RunAdapter runAdapter;
    ProjectAdapter projectAdapter;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info(String.format("======================================== STARTING TEST %s ========================================", iTestResult.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        runAdapter = new RunAdapter();
        projectAdapter = new ProjectAdapter();
        List<ITestNGMethod> methodsWithCustomAnnotation = getListOfMethods(iTestResult);
        int index = methodsWithCustomAnnotation.indexOf(iTestResult.getMethod());
        String caseId = methodsWithCustomAnnotation.get(index).getConstructorOrMethod().getMethod().getAnnotation(TmsLink.class).value();
        Result result = Result.builder().
                statusId(1).
                build();
        int projectTestRailId = projectAdapter.getProjectId("TestRail");
        int runId = runAdapter.getRunId(TESTRAIL_TEST_RUN.getName(), projectTestRailId);
        runAdapter.addResultToTestRun(result, runId, caseId);
        log.info(String.format("======================================== FINISHED TEST %s Duration: %ss ========================================", iTestResult.getName(),
                getExecutionTime(iTestResult)));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        runAdapter = new RunAdapter();
        projectAdapter = new ProjectAdapter();
        List<ITestNGMethod> methodsWithCustomAnnotation = getListOfMethods(iTestResult);
        int index = methodsWithCustomAnnotation.indexOf(iTestResult.getMethod());
        String caseId = methodsWithCustomAnnotation.get(index).getConstructorOrMethod().getMethod().getAnnotation(TmsLink.class).value();
        Result result = Result.builder().
                statusId(5).
                build();
        int projectTestRailId = projectAdapter.getProjectId("TestRail");
        int runId = runAdapter.getRunId(TESTRAIL_TEST_RUN.getName(), projectTestRailId);
        runAdapter.addResultToTestRun(result, runId, caseId);
        log.info(String.format("======================================== FAILED TEST %s Duration: %ss ========================================", iTestResult.getName(),
                getExecutionTime(iTestResult)));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info(String.format("======================================== SKIPPING TEST %s ========================================", iTestResult.getName()));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }

    private List<ITestNGMethod> getListOfMethods(ITestResult iTestResult) {
        return Arrays.stream(iTestResult.getTestContext().getAllTestMethods())
                .filter(
                        iTestNGMethod ->
                                iTestNGMethod
                                        .getConstructorOrMethod()
                                        .getMethod()
                                        .getAnnotation(TmsLink.class)
                                        != null)
                .collect(Collectors.toList());
    }
}