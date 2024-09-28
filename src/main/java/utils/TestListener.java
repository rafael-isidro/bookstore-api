package utils;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestListener implements TestWatcher, BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        Allure.step("Starting test", (step) -> {
            step.name(extensionContext.getRequiredTestMethod().getName());
        });
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Allure.step("Test failed", (step) -> {
            step.name(cause.getMessage());
        });
    }
}
