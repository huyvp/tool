package com.tool.service.weekly;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;


@Service
public class GenWeeklyData {
    @Value("${report.jira.token}")
    private String JIRA_TOKEN;

    public String execute(String assignee) {
        try {
            String jql = "project=CRM AND (issuetype=Task OR issuetype=Story OR issuetype = \"One Call\") AND assignee=" + assignee + " AND updated>=startOfWeek(-3d) AND created >= startOfWeek(-3d) AND updated<=endOfWeek() AND status not in (\"To do\", \"pending\") ORDER BY component ASC";
            List<Map<String, Object>> issues = sendToJiraReleaseStory(jql);
            StringBuilder stringBuilder = new StringBuilder();
            if (issues != null) {
                for (Map<String, Object> issue : issues) {
                    Map<String, Object> fields = (Map<String, Object>) issue.get("fields");
                    stringBuilder.append(issue.get("key") + " ").append(fields.get("summary"));
                    Map<String, Object> status = (Map<String, Object>) fields.get("status");
                    String start = (String) fields.get("created");
                    String end = (String) fields.get("updated");
                    String name = (String) status.get("name");
                    if ("Closed".equals(name) || "Resolved".equals(name)) {
                        name = "Done";
                    }
                    if ("In Progress".equals(name)) {
                        end = (String) fields.get("duedate");
                        end = end.substring(5).replace("-", "/");
                    } else {
                        end = end.substring(5, 10).replace("-", "/");
                    }
                    ;
                    stringBuilder.append("(").append(name).append(" ").append(start.substring(5, 10).replace("-", "/")).append(" ~ ").append(end).append(")\n");
                }
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Map<String, Object>> sendToJiraReleaseStory(String jql) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", JIRA_TOKEN);
        headers.add("Referer", "devops.sdsdev.co.kr");
        HttpEntity<?> request = new HttpEntity<>(headers);

        UriComponents uri = UriComponentsBuilder
                .fromUriString("https://devops.sdsdev.co.kr")
                .path("/jira/rest/api/2/search")
                .queryParam("jql",jql)
                .build();
        try {
            ResponseEntity<String> response = restTemplate.exchange(uri.toUri(), HttpMethod.GET, request, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                Map responseBody = new ObjectMapper().readValue(response.getBody(), Map.class);
                return (List<Map<String, Object>>) responseBody.get("issues");
            }
        } catch (RestClientException e) {
            throw new RuntimeException("Send http request JIRA search Failed");
        }
        return null;
    }

}
