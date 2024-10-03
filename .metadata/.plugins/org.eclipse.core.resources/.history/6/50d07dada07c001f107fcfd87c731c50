package com.sinctrlaltf4.action.util;

import java.util.Comparator;
import java.util.List;

import com.sinctrlaltf4.model.entity.Juego;
import com.sinctrlaltf4.model.services.repository.GenericRepositoryServiceImpl;
import com.sinctrlaltf4.model.services.repository.util.JpaUtil;

public class GestionHistorialUtil {
	private static GenericRepositoryServiceImpl<Juego> juegoGenericRepositoryServiceImpl = new GenericRepositoryServiceImpl<>(JpaUtil.getEntityManager(), Juego.class);
	public static List<Juego> obtenerTresUltimosJugadores() {
		List<Juego> juegos = juegoGenericRepositoryServiceImpl.listar();
        juegos.sort(Comparator.comparing(Juego::getHora).reversed());
        return juegos.subList(0, Math.min(3, juegos.size()));
	}
}
