package com.dev.franrs.reservation.repository;

import com.dev.franrs.reservation.entity.EstadoReserva;
import com.dev.franrs.reservation.entity.Reserva;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    boolean existsByFechaAndHoraAndEstado(LocalDate fecha, LocalTime hora, EstadoReserva estado);
}
