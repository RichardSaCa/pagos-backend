package com.banco.pagos_backend.service;

import com.banco.pagos_backend.model.Transacion;
import com.banco.pagos_backend.model.Usuario;
import com.banco.pagos_backend.repository.TransacionRepository;
import com.banco.pagos_backend.repository.UserRepository;
import com.banco.pagos_backend.service.dto.TransaccionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * Lógica del negocio para los procesos de transacción.
 */
@Service
public class TransacionServiceImp implements TransacionService{

    public final TransacionRepository transacionRepository;

    public final UserRepository userRepository;

    @Autowired
    public TransacionServiceImp(TransacionRepository transacionRepository, UserRepository userRepository){
        this.transacionRepository = transacionRepository;
        this.userRepository = userRepository;
    }

    /**
     * Método para hacer el pago, es necesario el dto con información de la transferencia.
     * @param tranferenciaDto
     * @return
     */
    @Override
    @Transactional
    public Transacion hacerPago(TransaccionDto tranferenciaDto) {
        /**
         * Enoncontrar el usuario con el nombre de usuario
         */
        Usuario usuario = this.userRepository.findById(tranferenciaDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User " + tranferenciaDto.getUsername() + " not " +
                        "found."));
        /**
         * Se crea en memoria la transacción para luego guardarla en la base de datos
         */
        LocalDateTime fechaActual =  LocalDateTime.now(ZoneId.of("America/Bogota"));
        Transacion transacion = new Transacion();
        transacion.setReferencia(tranferenciaDto.getReferencia());
        transacion.setMonto(tranferenciaDto.getMonto());
        transacion.setUser(usuario);
        transacion.setGrantedDate(fechaActual);

        return this.transacionRepository.save(transacion);
    }

    /**
     * Este método permite obtener las transacciones realizadas de acuerdo con el nombre de usuario.
     * @param username
     * @return retorna una lista con las transacciones
     */
    @Override
    public List<Transacion> obtenerTransacciones(String username) {
        return this.transacionRepository.obtenerPorUsername(username);
    }
}
