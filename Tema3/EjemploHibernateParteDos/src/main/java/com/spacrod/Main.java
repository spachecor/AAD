package com.spacrod;

import com.spacrod.ejerciciopartedos.Empresa;
import com.spacrod.ejerciciopartedos.Estudiante;
import com.spacrod.ejerciciopartedos.services.repository.EmpresaRepositoryServiceImpl;
import com.spacrod.ejerciciopartedos.services.repository.EstudianteRepositoryServiceImpl;

public class Main {
    public static void main(String[] args) {
        EstudianteRepositoryServiceImpl estudianteRepositoryService = new EstudianteRepositoryServiceImpl();
        EmpresaRepositoryServiceImpl empresaRepositoryService = new EmpresaRepositoryServiceImpl();
        Empresa adidas = new Empresa();
        adidas.setNombre("Adidas");
        Estudiante manuel = new Estudiante();
        manuel.setNombre("Manuel");
        manuel.setApellidos("Carmona");
        manuel.setEdad(29);
        manuel.setEmpresa(adidas);
        System.out.println(empresaRepositoryService.save(adidas));
        System.out.println(estudianteRepositoryService.save(manuel));
    }
}