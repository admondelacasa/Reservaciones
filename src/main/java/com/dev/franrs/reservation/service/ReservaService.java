package com.dev.franrs.reservation.service;

import com.dev.franrs.reservation.entity.EstadoReserva;
import com.dev.franrs.reservation.entity.Reserva;
import com.dev.franrs.reservation.exception.ReglaNegocioException;
import com.dev.franrs.reservation.repository.ReservaRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Transactional(readOnly = true)
    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    @Transactional
    public Reserva crear(
            String nombreCliente, LocalDate fecha, LocalTime hora, String servicio) {
        if (reservaRepository.existsByFechaAndHoraAndEstado(fecha, hora, EstadoReserva.ACTIVA)) {
            throw new ReglaNegocioException(
                    "Ya existe una reserva activa para la fecha y hora indicadas.",
                    HttpStatus.CONFLICT);
        }
        Reserva reserva = new Reserva();
        reserva.setNombreCliente(nombreCliente);
        reserva.setFecha(fecha);
        reserva.setHora(hora);
        reserva.setServicio(servicio);
        reserva.setEstado(EstadoReserva.ACTIVA);
        return reservaRepository.save(reserva);
    }

    @Transactional
    public void cancelar(Long id) {
        Reserva reserva =
                reservaRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new ReglaNegocioException(
                                                "No existe una reserva con el id indicado.",
                                                HttpStatus.NOT_FOUND));
        if (reserva.getEstado() == EstadoReserva.CANCELADA) {
            throw new ReglaNegocioException(
                    "La reserva ya está cancelada.", HttpStatus.BAD_REQUEST);
        }
        reserva.setEstado(EstadoReserva.CANCELADA);
        reservaRepository.save(reserva);
    }
}
