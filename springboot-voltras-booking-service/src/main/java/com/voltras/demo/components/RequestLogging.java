package com.voltras.demo.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.voltras.demo.utils.JsonUtils;
import com.voltras.voltrasspring.logging.AuditTrail;
import com.voltras.voltrasspring.logging.RequestTrail;
import com.voltras.voltrasspring.logging.ResponseTrail;
import com.voltras.voltrasspring.logging.VoltrasSpringLogging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RequestLogging implements VoltrasSpringLogging {
    @Autowired
    LogHelper logger;

    @Override
    public AuditTrail logRequest(AuditTrail trail) {
        trail.setTrailId(UUID.randomUUID().toString());
        RequestTrail req = trail.getRequest();
        Object body, rawBody = req == null ? null : req.getBody();
        try {
            body = rawBody == null ? null : JsonUtils.parseToString(rawBody);
        } catch (JsonProcessingException e) {
            body = rawBody;
        }
        logger.info("[trail: {}] [REQ: {}]", trail.getTrailId(), body);
        return trail;
    }

    @Override
    public AuditTrail logResponse(AuditTrail trail) {
        ResponseTrail rsp = trail.getResponse();
        Exception ex = rsp == null ? null : rsp.getError();
        if (ex == null) {
            Object body, rawBody = rsp == null ? null : rsp.getBody();
            try {
                body = rawBody == null ? null : JsonUtils.parseToString(rawBody);
            } catch (JsonProcessingException e) {
                body = rawBody;
            }
            logger.info("[trail: {}] [RSP: {}]", trail.getTrailId(), body);
        } else {
            logger.error("[trail: {}] [ERR: {}]", trail.getTrailId(), ex.getMessage(), ex);
        }
        return trail;
    }

}
