package com.voltras.demo.models;

import java.util.UUID;

import lombok.Builder;

@Builder
public record Guest(UUID id, String name, String email) {

}
