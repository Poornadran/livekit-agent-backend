Postman API Documentation: LiveKit Agent Backend

This document outlines all available endpoints, request payloads, and example responses.


Base URL

```
http://localhost:8080/
```

## Agent Endpoints

Create or Update Agent

URL: agent/createOrUpdate
METHOD: PUT
Request Body
{
  "id":1,
  "name": "Support Bot",
  "language": "en-US",
  "voiceModel": "GoogleTTS",
  "llmParameters": "{\"model\":\"gpt-4\", \"temperature\":0.7}"
}

Response body 
{
Agent updated successfully
}


Get All Agents

URL: agent/get-all
METHOD: GET

Response body
[
    {
        "id": 1,
        "name": "Demo Bot",
        "language": "en-US",
        "voiceModel": "GoogleTTS",
        "llmParameters": "{\"modelName\":\"gpt-4\", \"temperature\":0.7}",
        "createdAt": "2025-07-06T22:09:15",
        "updatedAt": "2025-07-06T22:09:15"
    }
]


GET BY ID

URL: agent/get/{agentId}
METHOD: GET

Response body

{
    "id": 1,
    "name": "Demo Bot",
    "language": "en-US",
    "voiceModel": "GoogleTTS",
    "llmParameters": "{\"modelName\":\"gpt-4\", \"temperature\":0.7}",
    "createdAt": "2025-07-06T22:09:15",
    "updatedAt": "2025-07-06T22:09:15"
}


## Call Session Endpoints

Start Call

URL: /calls/start?agentId=1&modelUsed=gpt-4
METHOD: POST

Response Body

{
    "sessionId": 3,
    "roomName": "session-a1e95877-fa61-4525-9e62-6b68027ebe0c",
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEl1dTY5REd3RnM2cVQiLCJpYXQiOjE3NTE4MjAxNjcsImV4cCI6MTc1MTgyMzc2NywidmlkZW8iOnsicm9vbUNyZWF0ZSI6dHJ1ZSwicm9vbSI6InNlc3Npb24tYTFlOTU4NzctZmE2MS00NTI1LTllNjItNmI2ODAyN2ViZTBjIiwicm9vbUpvaW4iOnRydWV9LCJzdWIiOiJhZ2VudC0xIn0.P5zpHwjYejmJG25shsy562Z8B91TGDotCObzJ5nkom8"
}


Get Call Session

URL: calls/get/{callSessionId}
METHOD: GET

Response Body
{
    "id": 2,
    "agentId": 2,
    "startTime": "2025-07-06T17:20:57",
    "endTime": "2025-07-06T18:58:33",
    "status": "ENDED",
    "modelUsed": "gpt-4",
    "dynamicVariables": "{\"customerName\":\"Jhon\",\"language\":\"en-US\",\"issue\":\"Damaged product\"}",
    "createdAt": "2025-07-06T17:20:57",
    "updatedAt": "2025-07-06T18:58:33"
}


Update Dynamic Variables

URL: calls/update-dynamicVariable/{sessionId}
METHOD: PUT

Request Body

{
    "customerName": "Jhon",
    "language": "en-US"
}

response body
{
Variables updated successfully
}


End Call

URL: calls/end/2
METHOD: PUT

response body
{
Call session ended successfully
}

Tool Call Endpoints

Invoke Tool

URL: tools/invoke/{toolName}?callSessionId=1
METHOD: POST

 Response body

{
    "id": 2,
    "callSession": {
        "id": 1,
        "startTime": "2025-07-06T17:09:48",
        "endTime": "2025-07-06T18:58:02",
        "status": "ENDED",
        "modelUsed": "gpt-4",
        "dynamicVariables": "{\"customerName\":\"Jhon\",\"language\":\"en-US\",\"issue\":\"Damaged product\"}",
        "createdAt": "2025-07-06T17:09:48",
        "updatedAt": "2025-07-06T22:19:01"
    },
    "toolName": "account_balance",
    "responseData": "Account balance for Jhon is ₹8,400",
    "timestamp": "2025-07-06T22:23:23.4817983"
}


Get Tool Logs by Session

URL: tools/logs/{sessionId}

Response body
[
    {
        "id": 1,
        "callSessionId": 1,
        "toolName": "account_balance",
        "responseData": "Account balance for Jhon is ₹8,400",
        "timestamp": "2025-07-06T19:29:37"
    },
    {
        "id": 2,
        "callSessionId": 1,
        "toolName": "account_balance",
        "responseData": "Account balance for Jhon is ₹8,400",
        "timestamp": "2025-07-06T22:23:23"
    }
]

Swagger UI

Access Swagger docs:

```
http://localhost:8080/swagger-ui/index.html
```

---

Import to Postman

1. Use this markdown to understand API inputs.
2. For auto-import: export `/v3/api-docs` from your app and import it in Postman.
