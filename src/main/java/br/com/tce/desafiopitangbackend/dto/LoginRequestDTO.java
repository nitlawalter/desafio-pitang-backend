package br.com.tce.desafiopitangbackend.dto;

import lombok.Getter;
import lombok.Setter;

public record LoginRequestDTO(String login, String password) {
}
