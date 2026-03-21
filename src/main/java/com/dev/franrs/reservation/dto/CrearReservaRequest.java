package com.dev.franrs.reservation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public record CrearReservaRequest(
        @NotBlank String nombreCliente,
        @NotNull LocalDate fecha,
        @NotNull LocalTime hora,
        @NotBlank String servicio) {}
