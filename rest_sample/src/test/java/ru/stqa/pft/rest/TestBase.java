package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {
    private String issueState;

    public boolean isIssueOpened(int issueId) throws IOException {
        String state = getIssueState(issueId);
        if (!state.equals("Closed")) {
            return true;
        }
        return false;
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpened(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId + " was not fixed");
        }
    }

    public Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    public String getIssueState(int id) throws IOException {
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/" + id + ".json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> issue = new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
        issueState = issue.iterator().next().getStateName();
        return issueState;
    }
}