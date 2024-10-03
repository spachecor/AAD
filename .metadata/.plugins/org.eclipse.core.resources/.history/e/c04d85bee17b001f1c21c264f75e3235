package com.sinctrlaltf4.action;

import java.time.LocalDateTime;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.sinctrlaltf4.action.util.GestionHistorialUtil;
import com.sinctrlaltf4.action.util.LocalDateTimeAdapter;
import com.sinctrlaltf4.model.entity.Juego;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class HistorialAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String accion;
	
	@Override
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
		if(this.getAccion()!=null&&this.getAccion().equals("historial")) {
			return INPUT;
		}else if(this.getAccion()!=null&&this.getAccion().equals("salir")) {
			return NONE;			
		}else if(this.getAccion()!=null&&this.getAccion().equals("obtenerDatos")) {
			List<Juego> juegos = null;
			try{
				juegos = GestionHistorialUtil.obtenerTresUltimosJugadores();
			}catch(Exception e) {
				e.printStackTrace();
			}
			String juegosJson = gson.toJson(juegos);
			response.getWriter().write(juegosJson);
			response.flushBuffer();
			return null;
		}
		return INPUT;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}
	

}
